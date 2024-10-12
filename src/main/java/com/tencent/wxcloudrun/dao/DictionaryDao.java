package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.dto.DictionaryBySignDTO;
import com.tencent.wxcloudrun.model.DictionaryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字段管理
 *
 * @author zhengfeng
 * @email zhenfeng@imeik.com
 * @date 2022-03-24 18:20:43
 */
@Mapper
public interface DictionaryDao extends BaseMapper<DictionaryEntity> {

    List<DictionaryEntity> queryByParentIdAndSign(@Param("parentSign") String parentSign, @Param("subSign") String subSign);

    List<DictionaryBySignDTO> getListBySign(String sign);
    List<DictionaryBySignDTO> getApiListBySign(@Param("sign") String sign,@Param("list") List<String> cateGoryCodes);

}
