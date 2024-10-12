package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.model.CityEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 省市县
 *
 * @author zhengfeng
 * @email zhenfeng@imeik.com
 * @date 2022-03-24 18:14:24
 */
@Mapper
public interface CityDao extends BaseMapper<CityEntity> {

    CityEntity getByAreaCode(String areaCode);

    /**
     * 根据地区号查询出省市编号
     * @param hostCity
     * @return
     */
    CityEntity querySF(String hostCity);


    /**
     * 查询滴滴侧ID
     */
    List<HashMap<String,String>> ddtemp();

}
