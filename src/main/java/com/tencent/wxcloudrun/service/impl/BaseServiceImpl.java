package com.tencent.wxcloudrun.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.tencent.wxcloudrun.service.BaseService;
import com.baomidou.mybatisplus.core.enums.*;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhengfeng
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> implements BaseService<T> {

    @Autowired
    protected M baseMapper;

    public BaseServiceImpl() {
    }
    /**
     * 根据ObjectCode 进行查询
     * @param id
     * @return
     */
    @Override
    public T getById(Long id) {

        QueryWrapper<T> qw =  new QueryWrapper<T>();
        qw.eq("id", id);
        T t = null;
        try {
            t = baseMapper.selectOne(qw);
        }catch (Exception e){
            e.printStackTrace();
        }

        return t;
    }

    /**
     * 根据id 进行更新
     * @param t
     * @return
     */
    @Override
    public int updateById(T t, Long id){
        QueryWrapper<T> qw =  new QueryWrapper<T>();
        qw.eq("id", id);
        return baseMapper.update(t,qw);
    }

    /**
     * 根据id 进行删除
     * @param id
     * @return
     */
    @Override
    public int removeById(Long id){

        UpdateWrapper uw = new UpdateWrapper();
        uw.eq("id", id);
        uw.set("deleted", "1");
        return baseMapper.update(null, uw);
    }


    /**
     * 保存
     * @param entity
     * @return
     */
    @Override
    public int save(T entity){
        return this.baseMapper.insert(entity);
    }

    /**
     * 查询数据量
     *
     * @param qw
     * @return
     */
    @Override
    public int selectCount(QueryWrapper<T> qw) {
      return baseMapper.selectCount(qw);
    }

    /**
     * 查询唯一值
     *
     * @param qw
     * @return
     */
    @Override
    public T selectOne(QueryWrapper<T> qw) {
        return baseMapper.selectOne(qw);
    }

    /**
     * 查询所有有效数据
     * @return
     */
    @Override
    public List<T> selectAll(){
        QueryWrapper<T> qw =  new QueryWrapper<T>();
        qw.eq("deleted", "0");
        return this.baseMapper.selectList(qw);
    }

    /**
     * 根据自定义字段查询数据量
     *
     * @param customFieId
     * @param code
     * @return
     */
    @Override
    public int selectCountByCustomFieId(String customFieId, String code) {
        QueryWrapper<T> qw =  new QueryWrapper<T>();
        qw.eq("deleted", "0");
        qw.eq(customFieId, code);
        return this.baseMapper.selectCount(qw);
    }

    /**
     * 根据objectList查询所有数据
     * @return
     */
    @Override
    public List<T> selectAllByObjectCodeList(List<String> objectCodeList){
        if(objectCodeList != null && objectCodeList.size() > 0){
            QueryWrapper<T> qw =  new QueryWrapper<T>();
            qw.eq("deleted", "0");
            qw.in("object_code", objectCodeList);
            return this.baseMapper.selectList(qw);
        }else{
            return new ArrayList<T>();
        }
    }

    @Override
    public boolean saveBatch(Collection<T> entityList) {
        return BaseService.super.saveBatch(entityList);
    }

    /**
     * 根据自定义字段查询所有数据
     * @return
     */
    @Override
    public List<T> selectAllBycustomFieList(String customFieId, String code){
        QueryWrapper<T> qw =  new QueryWrapper<T>();
        qw.eq("deleted", "0");
        qw.eq(customFieId, code);
        return this.baseMapper.selectList(qw);
    }

    @Override
    public List<T> selectAllByCustomFieOrderList(String customFieId, String code, boolean isAsc, String... columns){
        QueryWrapper<T> qw =  new QueryWrapper<T>();
        qw.eq("deleted", "0");
        qw.eq(customFieId, code);
        qw.orderBy(true, isAsc, columns);
        return this.baseMapper.selectList(qw);
    }

    /**
     * 根据自定义字段集合查询所有值
     * @return
     */
    @Override
    public List<T> selectAllBycustomFieAllList(String customFieId, List<String> codeList){
        if(codeList != null && codeList.size() > 0) {
            QueryWrapper<T> qw =  new QueryWrapper<T>();
            qw.eq("deleted", "0");
            qw.in(customFieId, codeList);
            return this.baseMapper.selectList(qw);
        }else{
            return new ArrayList<T>();
        }
    }

    /**
     * 根据 entity 条件，查询全部记录
     * @param wrapper
     * @return
     */
    @Override
    public List<T> selectAllByWrapper(Wrapper<T> wrapper) {
        return this.baseMapper.selectList(wrapper);
    }

    /**
     *根据自定义字段查询所有数据 进行删除
     * @param customFieId   指定字段
     * @param code  指定值
     * @return
     */
    @Override
    public int removeByCustomFie(String customFieId, String code){
        UpdateWrapper uw = new UpdateWrapper();
        uw.eq(customFieId, code);
        uw.eq("deleted", "0");
        uw.set("deleted", "1");

        return baseMapper.update(null, uw);
    }

    @Override
    public int removeByWrapper(T entity, UpdateWrapper<T> uw){
        return baseMapper.update(entity, uw);
    }

    /**
     * 批量插入
     *
     * @param entityList
     * @param batchSize
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        String sqlStatement = sqlStatement(SqlMethod.INSERT_ONE);
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int i = 0;
            for (T anEntityList : entityList) {
                batchSqlSession.insert(sqlStatement, anEntityList);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        }
        return true;
    }

    protected Class<T> currentModelClass() {
        return ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    /**
     * 获取SqlStatement
     *
     * @param sqlMethod
     * @return
     */
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }

    /**
     * <p>
     * 批量操作 SqlSession
     * </p>
     */
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(currentModelClass());
    }
    @Override
    public boolean updateBatch(Collection<T> entityList,int batchSize) {
        Assert.notEmpty(entityList, "error: entityList must not be empty", new Object[0]);
        String sqlStatement = this.sqlStatement(SqlMethod.UPDATE_BY_ID);
        SqlSession batchSqlSession = this.sqlSessionBatch();
        Throwable var5 = null;

        try {
            int i = 0;

            for(Iterator var7 = entityList.iterator(); var7.hasNext(); ++i) {
                T anEntityList = (T) var7.next();
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap();
                param.put("et", anEntityList);
                batchSqlSession.update(sqlStatement, param);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }

            batchSqlSession.flushStatements();
            return true;
        } catch (Throwable var17) {
            var5 = var17;
            throw var17;
        } finally {
            if (batchSqlSession != null) {
                if (var5 != null) {
                    try {
                        batchSqlSession.close();
                    } catch (Throwable var16) {
                        var5.addSuppressed(var16);
                    }
                } else {
                    batchSqlSession.close();
                }
            }

        }
    }

}

