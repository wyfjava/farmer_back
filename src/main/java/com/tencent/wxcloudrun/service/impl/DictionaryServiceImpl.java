package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tencent.wxcloudrun.constant.Constant;
import com.tencent.wxcloudrun.dao.DictionaryDao;
import com.tencent.wxcloudrun.dto.*;
import com.tencent.wxcloudrun.enums.ResultEnum;
import com.tencent.wxcloudrun.exception.MyForbiddenException;
import com.tencent.wxcloudrun.model.DictionaryEntity;
import com.tencent.wxcloudrun.service.DictionaryService;
import com.tencent.wxcloudrun.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author zhengfeng
 */
@Service("dictionaryService")
public class DictionaryServiceImpl extends BaseServiceImpl<DictionaryDao, DictionaryEntity> implements DictionaryService {

    @Override
    public PageUtils<DictionaryListDTO> queryPage(Map<String, Object> params) {

        QueryWrapper<DictionaryEntity> qw = new QueryWrapper<DictionaryEntity>();

        qw.eq("deleted", "0");
        qw.eq("parent_id", "0");

        String name = (String) params.get("name");
        if (StringUtils.isNotBlank(name)) {
            qw.like("name", name);
        }
        String sign = (String) params.get("sign");
        if (StringUtils.isNotBlank(sign)) {
            qw.like("sign", sign);
        }

        String app = (String) params.get("app");
        if (StringUtils.isNotBlank(app)) {
            qw.eq("app", app);
        }


        IPage page = baseMapper.selectPage(
                new Query<DictionaryEntity>().getPage(params),
                qw
        );

        List records = page.getRecords();
        List<DictionaryWholeDTO> dtoList = ConvertUtils.sourceToTarget(records, DictionaryWholeDTO.class);

        // 暂时这样写。 后续优化掉。。
        List<DictionaryDTO> tree = this.findTree();
        Map<String, DictionaryDTO> collect = tree.stream().collect(Collectors.toMap(DictionaryDTO::getObjectCode, Function.identity()));
        for (DictionaryWholeDTO dto : dtoList) {
            List<DictionaryOptionsDTO> dictionaryOptionsDTOS = new ArrayList<>();
            List<DictionaryDTO> options = collect.get(dto.getObjectCode()).getOptions();
            for (DictionaryDTO dictDTO : options) {
                DictionaryOptionsDTO dictionaryOptionsDTO = new DictionaryOptionsDTO();
                dictionaryOptionsDTO.setLabel(dictDTO.getName());
                dictionaryOptionsDTO.setValue(dictDTO.getSign());
                dictionaryOptionsDTOS.add(dictionaryOptionsDTO);
            }
            dto.setOptions(dictionaryOptionsDTOS);
        }

        page.setRecords(dtoList);

        return new PageUtils(page);
    }

    @Override
    public List<DictionaryDTO> list(Map<String, Object> params) {
        QueryWrapper<DictionaryEntity> qw = new QueryWrapper<DictionaryEntity>();

        qw.eq("deleted", "0");

        List<DictionaryEntity> entityList = baseMapper.selectList(qw);

        return ConvertUtils.sourceToTarget(entityList, DictionaryDTO.class);
    }


    @Override
    public List<DictionaryDTO> findTree() {
        QueryWrapper<DictionaryEntity> qw =  new QueryWrapper<DictionaryEntity>();
        qw.eq("deleted", "0");
        List<DictionaryEntity> all = this.baseMapper.selectList(qw);
        return this.toTree(all, "0");
    }


    //树形结构
    @Override
    public List<DictionaryDTO> toTree(List<DictionaryEntity> dictionaries, String parentObjectCode) {
        return dictionaries.stream()
                .filter(x -> x.getParentId().equals(parentObjectCode))
                //.sorted(Comparator.comparing(CityDTO::getSort))
                .map(x ->
                        DictionaryDTO.builder()
                                .objectCode(x.getObjectCode())
                                .name(x.getName())
                                .sign(x.getSign())
                                .icon(x.getIcon())
                                .options(toTree(dictionaries, x.getObjectCode()))
                                .build()

                )
                .collect(Collectors.toList());
    }

    /**
     * 根据父级查询所有子集数据
     *
     * @param parent
     * @return
     */
    @Override
    public List<DictionaryListDTO> getSubset(String parent) {
        QueryWrapper<DictionaryEntity> qw = new QueryWrapper<>();

        qw.eq("deleted", "0");
        qw.eq("parent_id", parent);

        List<DictionaryEntity> entityList = baseMapper.selectList(qw);

        return ConvertUtils.sourceToTarget(entityList, DictionaryListDTO.class);
    }

    /**
     * 一次性保存字典集
     *
     * @param dictionary
     */
    @Override
    public Result saveWholeAdd(DictionaryWholeAddDTO dictionary) {
        String app = RequestUtil.getRequest().getHeader("app");
        if (StringUtils.isBlank(app)){
            throw new MyForbiddenException("没有获取到app值");
        }
        // 先查询查询sign是否存在(必须是父级，有效数据)， 存在则提示标识不可重复
        QueryWrapper<DictionaryEntity> qw = new QueryWrapper<DictionaryEntity>();
        qw.eq("sign", dictionary.getSign());
        qw.eq("parent_id", "0");
        qw.eq("deleted", "0");
        Integer integer = baseMapper.selectCount(qw);
        if (integer > 0) {
            return Result.error(ResultEnum.DICTIONARY_SING_EXISTENCE.getCode(), ResultEnum.DICTIONARY_SING_EXISTENCE.getMessage());
        }


        // 获取所有子集
        List<DictionaryOptionsDTO> options = dictionary.getOptions();

        DictionaryEntity dictionaryEntity = ConvertUtils.sourceToTarget(dictionary, DictionaryEntity.class);

        // 父级保存
        String dictId = IdWorker.getIdStr();
        dictionaryEntity.setObjectCode(dictId);
        dictionaryEntity.setIcon("");
        dictionaryEntity.setApp(app);
        baseMapper.insert(dictionaryEntity);

        // 子集保存
        for (DictionaryOptionsDTO optionsDTO : options) {
            DictionaryEntity entity = new DictionaryEntity();
            entity.setParentId(dictId);
            entity.setName(optionsDTO.getLabel());
            entity.setSign(optionsDTO.getValue());
            entity.setIcon("");
            entity.setApp(app);
            baseMapper.insert(entity);
        }

        return Result.OK();
    }

    /**
     * 信息,包含子集
     *
     * @param id objectCode
     * @return
     */
    @Override
    public DictionaryWholeDTO getByObjectCodeByWhole(String id) {

        DictionaryEntity byObjectCode = this.getByObjectCode(id);

        QueryWrapper<DictionaryEntity> qw = new QueryWrapper<>();
        qw.eq("parent_id", byObjectCode.getObjectCode());
        qw.eq("deleted", "0");

        List<DictionaryEntity> dictionaryEntities = baseMapper.selectList(qw);

        DictionaryWholeDTO dto = ConvertUtils.sourceToTarget(byObjectCode, DictionaryWholeDTO.class);
        List<DictionaryOptionsDTO> optionsList = new ArrayList<>();

        for (DictionaryEntity entity : dictionaryEntities) {
            DictionaryOptionsDTO dictionaryOptionsDTO = new DictionaryOptionsDTO();
            dictionaryOptionsDTO.setLabel(entity.getName());
            dictionaryOptionsDTO.setValue(entity.getSign());
            optionsList.add(dictionaryOptionsDTO);
        }
        dto.setOptions(optionsList);

        return dto;
    }

    /**
     * 修改，包含子集
     *
     * @param dictionary
     */
    @Override
    public void updateByObjectCodeWhole(DictionaryWholeDTO dictionary) {

        String app = RequestUtil.getRequest().getHeader("app");
        if (StringUtils.isBlank(app)){
            throw new MyForbiddenException("没有获取到app值");
        }
        // 删除子集数据。 插入新数据

        // 更新父级属性
        DictionaryEntity entity = ConvertUtils.sourceToTarget(dictionary, DictionaryEntity.class);
        entity.setApp(app);
        this.updateByObjectCode(entity, entity.getObjectCode());

        // 清除子集属性
        UpdateWrapper uw = new UpdateWrapper();
        uw.eq("parent_id", dictionary.getObjectCode());
        uw.set("deleted", "1");

        this.baseMapper.update(null, uw);

        // 添加新的子集数据
        for (DictionaryOptionsDTO optionsDTO : dictionary.getOptions()) {
            DictionaryEntity dictionaryEntity = new DictionaryEntity();
            dictionaryEntity.setParentId(dictionary.getObjectCode());
            dictionaryEntity.setName(optionsDTO.getLabel());
            dictionaryEntity.setSign(optionsDTO.getValue());
            dictionaryEntity.setIcon("");
            dictionaryEntity.setApp(app);
            baseMapper.insert(dictionaryEntity);
        }


    }

    /**
     * 模糊查询子集数据
     *
     * @param dictionary 父级ID, 名字
     * @return 模糊查询子集数据
     */
    @Override
    public Result<List<DictionaryAddDTO>> parentVague(DictionaryParentVagueDTO dictionary) {
        QueryWrapper<DictionaryEntity> qw = new QueryWrapper<DictionaryEntity>();

        qw.eq("deleted", "0");
        qw.eq("parent_id", dictionary.getParentId());

        qw.like("name", dictionary.getName());
        List<DictionaryEntity> dictionaryEntities = baseMapper.selectList(qw);

        List<DictionaryAddDTO> dictionaryList = ConvertUtils.sourceToTarget(dictionaryEntities, DictionaryAddDTO.class);

        Result result = new Result();
        result.setData(dictionaryList);

        return result;

    }

    /**
     * 根据标记字段sign查询整个数据结构
     *
     * @param params
     * @return
     */
    @Override
    public List<DictionaryListDTO> getListBySign(Map<String, Object> params) {
        String sign = (String) params.get("sign");
        String orderFlag = (String) params.get("orderFlag");
        List<DictionaryBySignDTO> DictionaryBySignDTOs = baseMapper.getListBySign(sign);
        if (CollectionUtils.isEmpty(DictionaryBySignDTOs)) {
            return new ArrayList();
        }
        //一级分类
        Map<String, DictionaryListDTO> firMap = new HashMap();
        List<DictionaryListDTO> firList = new ArrayList();
        for (DictionaryBySignDTO signDTO : DictionaryBySignDTOs) {
            DictionaryListDTO tmp = firMap.get(signDTO.getObjectCode1());
            if (tmp == null) {
                tmp = new DictionaryListDTO();
                tmp.setObjectCode(signDTO.getObjectCode1());
                tmp.setParentId(signDTO.getParentId());
                tmp.setSign(signDTO.getSign1());
                if(Constant.STR_ONE.equals(orderFlag)){
                    tmp.setIntSign(Integer.valueOf(signDTO.getSign1()));
                }
                tmp.setName(signDTO.getName1());
                firMap.put(signDTO.getObjectCode1(), tmp);
                firList.add(tmp);
            }
        }
        if(CollectionUtils.isEmpty(firList)){
            return new ArrayList();
        }
        //二级分类
        Collections.sort(firList, Comparator.comparing(DictionaryListDTO::getIntSign));
        Map<String, List<DictionaryBySignDTO>> thirMap = DictionaryBySignDTOs.stream().filter(a -> a.getObjectCode2() != null).collect(Collectors.groupingBy(DictionaryBySignDTO::getObjectCode2));
        if (thirMap.isEmpty()) {
            return firList;
        }
        List<DictionaryListDTO> secCategoryList = new ArrayList(thirMap.keySet().size());
        for (Map.Entry<String, List<DictionaryBySignDTO>> map : thirMap.entrySet()) {
            String secCode = map.getKey();
            DictionaryBySignDTO signDTO = map.getValue().get(0);
            if (signDTO != null) {
                DictionaryListDTO dictionaryListDTO = new DictionaryListDTO();
                dictionaryListDTO.setObjectCode(secCode);
                dictionaryListDTO.setSign(signDTO.getSign2());
                if(Constant.STR_ONE.equals(orderFlag)){
                    dictionaryListDTO.setIntSign(Integer.valueOf(signDTO.getSign2()));
                }
                dictionaryListDTO.setName(signDTO.getName2());
                dictionaryListDTO.setParentId(signDTO.getObjectCode1());
                secCategoryList.add(dictionaryListDTO);
            }
        }

        //三级分类
        if (CollectionUtils.isEmpty(secCategoryList)) {
            return firList;
        }
        Collections.sort(secCategoryList, Comparator.comparing(DictionaryListDTO::getIntSign));
        for (DictionaryListDTO secCate : secCategoryList) {
            List<DictionaryBySignDTO> list = thirMap.get(secCate.getObjectCode());
            if (CollectionUtils.isEmpty(list)) {
                continue;
            }
            List<DictionaryListDTO> thirList = new ArrayList();
            for (DictionaryBySignDTO thirCate : list) {
                if (StringUtils.isBlank(thirCate.getObjectCode3())) {
                    continue;
                }
                DictionaryListDTO dto = new DictionaryListDTO();
                dto.setObjectCode(thirCate.getObjectCode3());
                dto.setSign(thirCate.getSign3());
                if(Constant.STR_ONE.equals(orderFlag)){
                    dto.setIntSign(Integer.valueOf(thirCate.getSign3()));
                }
                dto.setParentId(secCate.getObjectCode());
                dto.setName(thirCate.getName3());
                thirList.add(dto);
            }
            Collections.sort(thirList, Comparator.comparing(DictionaryListDTO::getIntSign));
            secCate.setOptions(thirList);
        }


        Map<String, List<DictionaryListDTO>> secCateMap = secCategoryList.stream().filter(a -> a.getParentId() != null).collect(Collectors.groupingBy(DictionaryListDTO::getParentId));
        for (DictionaryListDTO firCate : firList) {
            List<DictionaryListDTO> list = secCateMap.get(firCate.getObjectCode());
            if (CollectionUtils.isEmpty(list)) {
                continue;
            }
            firCate.setOptions(list);
        }
        return firList;
    }

    @Override
    public DictionaryEntity queryDictionary(DictionaryWholeDTO dictionary) {
        if (dictionary != null) {
            String sign = dictionary.getSign();
            if (StringUtils.isNotBlank(sign)) {
                QueryWrapper<DictionaryEntity> qw = new QueryWrapper();
                qw.eq("deleted", "0");
                qw.eq("sign", sign);
                return baseMapper.selectOne(qw);
            }
        }
        return null;
    }

    @Override
    public List<DictionaryEntity> queryByWrapper(QueryWrapper<DictionaryEntity> dictionaryQW) {
        return baseMapper.selectList(dictionaryQW);
    }

    @Override
    public List<DictionaryEntity> queryByParentIdAndSign(String parentSign, String subSign) {
        return baseMapper.queryByParentIdAndSign(parentSign, subSign);
    }


    @Override
    public List<DictionaryDTO> queryByParentSign(String parentSing) {
        QueryWrapper<DictionaryEntity> qw = new QueryWrapper<>();
        qw.eq("sign",parentSing);
        qw.eq("deleted","0");
        List<DictionaryEntity> dictionaryEntityList = baseMapper.selectList(qw);
        if (CollectionUtils.isEmpty(dictionaryEntityList)){
            return new ArrayList<>();
        }
        QueryWrapper<DictionaryEntity> qw2 = new QueryWrapper<>();
        qw2.eq("parent_id",dictionaryEntityList.get(0).getObjectCode());
        qw2.eq("deleted","0");
        List<DictionaryEntity> entityList = baseMapper.selectList(qw2);
        List<DictionaryDTO> dtoList = new ArrayList<>();
        for (DictionaryEntity entity:entityList){
            DictionaryDTO dictionaryDTO = DictionaryDTO.builder()
                    .icon(entity.getIcon())
                    .parentId(entity.getParentId())
                    .level(Integer.valueOf(entity.getLevel()))
                    .name(entity.getName())
                    .sign(entity.getSign())
                    .objectCode(entity.getObjectCode())
                    .status(entity.getStatus())
                    .build();
            dtoList.add(dictionaryDTO);
        }
        return dtoList;
    }

    @Override
    public void delete(String objectCode) {
        String app = RequestUtil.getRequest().getHeader("app");
        if (StringUtils.isBlank(app)){
            throw new MyForbiddenException("没有获取到app值");
        }
        UpdateWrapper uw = new UpdateWrapper();
        uw.eq("object_code", objectCode);
        uw.eq("app",app);
        uw.set("deleted", "1");
        baseMapper.update(null, uw);
    }

    @Override
    public List<DictionaryDTO> findTree2Params(Map<String, Object> params) {
        QueryWrapper<DictionaryEntity> qw =  new QueryWrapper<DictionaryEntity>();
        qw.eq("deleted", "0");

        String app = (String) params.get("app");
        if (StringUtils.isNotBlank(app)) {
            qw.eq("app", app);
        }

        List<DictionaryEntity> all = this.baseMapper.selectList(qw);
        return this.toTree(all, "0");
    }

    @Override
    public DictionaryEntity getBySign(String sign) {
        QueryWrapper<DictionaryEntity> qw = new QueryWrapper<>();
        qw.eq("deleted","0");
        qw.eq("sign",sign);
        qw.eq("parent_id","0");
        qw.last(" limit 1");
        DictionaryEntity dictionaryEntity = baseMapper.selectOne(qw);
        return dictionaryEntity;
    }
}
