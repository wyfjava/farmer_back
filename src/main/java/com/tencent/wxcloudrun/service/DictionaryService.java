package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tencent.wxcloudrun.dto.*;
import com.tencent.wxcloudrun.model.DictionaryEntity;
import com.tencent.wxcloudrun.utils.PageUtils;
import com.tencent.wxcloudrun.utils.Result;

import java.util.List;
import java.util.Map;

/**
 * 字段管理
 *
 * @author zhengfeng
 * @email zhenfeng@imeik.com
 * @date 2022-03-24 18:20:43
 */
public interface DictionaryService extends BaseService<DictionaryEntity> {

    PageUtils<DictionaryListDTO> queryPage(Map<String, Object> params);

    List<DictionaryDTO> list(Map<String, Object> params);



    /**
     * 查询所有的省
     * @return
     */
    List<DictionaryDTO> findTree();

    //树形结构
    List<DictionaryDTO> toTree(List<DictionaryEntity> dictionary, String parentObjectCode);

    /**
     * 根据父级查询所有子集数据
     * @param parent
     * @return
     */
    List<DictionaryListDTO> getSubset(String parent);

    /**
     * 一次性保存字典集
     * @param dictionary
     */
    Result saveWholeAdd(DictionaryWholeAddDTO dictionary);

    /**
     * 信息,包含子集
     * @param id objectCode
     * @return
     */
    DictionaryWholeDTO getByObjectCodeByWhole(String id);

    /**
     * 修改，包含子集
     * @param dictionary
     */
    void updateByObjectCodeWhole(DictionaryWholeDTO dictionary);

    /**
     * 模糊查询子集数据
     * @param dictionary 父级ID, 名字
     * @return  模糊查询子集数据
     */
    Result<List<DictionaryAddDTO>> parentVague(DictionaryParentVagueDTO dictionary);

    /**
     * 根据标记字段sign查询整个数据结构
     * @param params
     * @return
     */
   List<DictionaryListDTO> getListBySign(Map<String, Object> params);

    /**
     * 根据参数查询字典信息
     * @param dictionary
     */
    DictionaryEntity queryDictionary(DictionaryWholeDTO dictionary);

    /**
     * 根据wrapper查询数据
     * @param dictionaryQW
     * @return
     */
    List<DictionaryEntity> queryByWrapper(QueryWrapper<DictionaryEntity> dictionaryQW);

    List<DictionaryEntity> queryByParentIdAndSign(String parentSign, String subSign);

    List<DictionaryDTO> queryByParentSign(String parentSing);

    void delete(String objectCode);

    List<DictionaryDTO> findTree2Params(Map<String, Object> params);

    /**
     *
     * @param sign
     * @return
     */
    DictionaryEntity getBySign(String sign);

}

