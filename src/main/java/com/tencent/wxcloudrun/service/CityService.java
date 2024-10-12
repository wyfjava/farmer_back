package com.tencent.wxcloudrun.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.dto.CityDTO;
import com.tencent.wxcloudrun.dto.CityInfoDTO;
import com.tencent.wxcloudrun.dto.CityQueryRequestDTO;
import com.tencent.wxcloudrun.model.CityEntity;
import com.tencent.wxcloudrun.utils.PageUtils;

/**
 * 省市县
 *
 * @author zhengfeng
 * @email zhenfeng@imeik.com
 * @date 2022-03-24 18:14:24
 */
public interface CityService extends IService<CityEntity> {

    PageUtils<CityDTO> queryPage(Map<String, Object> params);

    List<CityDTO> list(Map<String, Object> params);

    /**
     * 用区域编码查询
     */
    CityEntity getByAreaCode(String id);

    /**
     * 逻辑删除，只更新删除标记
     * @param id
     */
    void removeByDelete(String id);

    /**
     * 查询所有的省
     * @return
     */
    List<CityDTO> findAllProvince();

    /**
     * 根据当前节点查询下级节点数据
     * @param parentCode 父节点
     * @return
     */
    List<CityDTO> findAllNode(String parentCode);

    /**
     * 获取所有省市信息
     * @return
     */
    List<CityEntity> findAll();



    /**
     * 查询所有的省
     * @return
     */
    List<CityDTO> findTree();


    //树形结构
    List<CityDTO> toTree(List<CityEntity> regions, String parentAreaCode);

    /**
     * 查询所有的省
     * @return
     */
    List<CityInfoDTO> findCityTree();

    List<CityInfoDTO> toCityTree(List<CityEntity> regions, String parentAreaCode);

    /**
     * 根据numberCode 查询数据
     * @param regionCode
     * @return
     */
    CityEntity getByNumberCode(String regionCode);



    /**
     * langNumber 查询数据
     * @param langNumber
     * @return
     */
    CityEntity getByLongNumber(String langNumber);

    /**
     * 根据地区号查询出省市编号
     * @param hostCity
     * @return
     */
    CityEntity querySF(String hostCity);

    CityEntity getByName(String name);

    List<CityEntity> queryByLevel(String level);

    List<CityDTO> getCityInfo(CityDTO cityDTO);

    /**
     * 根据条件查询列表信息
     */
    List<CityDTO> queryCityInfo(CityQueryRequestDTO requestDTO);

    List<CityDTO> findTreeAll();

}

