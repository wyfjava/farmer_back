package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author zhengfeng
 */
public interface BaseService<T> {

    /**
     * 根据id 进行查询
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 根据id 进行更新
     * @param t
     * @return
     */
    int updateById(T t, Long id);

    /**
     * 根据id 进行删除
     * @param id
     * @return
     */
    int removeById(Long id);



    /**
     * 保存
     * @param entity
     * @return
     */
    int save(T entity);

    /**
     * 查询所有有效数据
     * @return
     */
    List<T> selectAll();

    /**
     * 查询数据量
     * @param qw
     * @return
     */
    int selectCount(QueryWrapper<T> qw);

    /**
     * 查询唯一值
     * @param qw
     * @return
     */
    T selectOne(QueryWrapper<T> qw);
    /**
     * 根据自定义字段查询数据量
     * @param customFieId
     * @param code
     * @return
     */
    int selectCountByCustomFieId(String customFieId, String code);

    /**
     * 根据objectList查询所有数据
     * @return
     */
    List<T> selectAllByObjectCodeList(List<String> objectCodeList);


    /**
     * 根据自定义字段查询所有数据
     * @return
     */
    List<T> selectAllBycustomFieList(String customFieId, String code);

    /**
     * 根据自定义字段查询排序所有数据
     * @return
     */
    List<T> selectAllByCustomFieOrderList(String customFieId, String code, boolean isAsc, String... columns);

    /**
     * 根据自定义字段集合查询所有值
     * @return
     */
    List<T> selectAllBycustomFieAllList(String customFieId, List<String> codeList);

    /**
     * 根据 entity 条件，查询全部记录
     * @param wrapper
     * @return
     */
    List<T> selectAllByWrapper(Wrapper<T> wrapper);

    /**
     *根据自定义字段查询所有数据 进行删除
     * @param customFieId   指定字段
     * @param code  指定值
     * @return
     */
    int removeByCustomFie(String customFieId, String code);


    /**
     * 根据条件进行删除
     * @param entity
     * @param uw
     * @return
     */
    int removeByWrapper(T entity, UpdateWrapper<T> uw);
    /**
     * <p>
     * 插入（批量）
     * </p>
     *
     * @param entityList 实体对象集合
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean saveBatch(Collection<T> entityList) {
        return saveBatch(entityList, 1000);
    }

    /**
     * <p>
     * 插入（批量）
     * </p>
     *
     * @param entityList 实体对象集合
     * @param batchSize  插入批次数量
     */
    boolean saveBatch(Collection<T> entityList, int batchSize);

    boolean updateBatch(Collection<T> entityList,int batchSize);
}
