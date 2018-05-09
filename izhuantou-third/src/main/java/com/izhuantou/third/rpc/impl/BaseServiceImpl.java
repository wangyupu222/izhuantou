package com.izhuantou.third.rpc.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.izhuantou.damain.BasePojo;
import com.izhuantou.service.api.BaseService;

/**
 * Service基类实现类
 *
 * @author fucheng
 * @date 2018-01-06
 */

public class BaseServiceImpl<T extends BasePojo> implements BaseService<T> {

    @Autowired
    private Mapper<T> mapper; // 范型注入
    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public BaseServiceImpl() {
	Type type = this.getClass().getGenericSuperclass();
	ParameterizedType ptype = (ParameterizedType) type;
	Type[] types = ptype.getActualTypeArguments();
	this.clazz = (Class<T>) types[0];
    }

    @Override
    public T queryByid(String id) {

	return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> queryAll() {

	return this.mapper.select(null);
    }

    @Override
    public int queryCountByWhere(T t) {

	return this.mapper.selectCount(t);
    }

    @Override
    public List<T> queryListByWhere(T t) {

	return this.mapper.select(t);
    }

    @Override
    public List<T> queryByPage(Integer page, Integer rows) {
	// 设置分页参数
	PageHelper.startPage(page, rows);
	return this.mapper.select(null);
    }

    @Override
    public T queryOne(T t) {
	return this.mapper.selectOne(t);
    }

    @Override
    public void save(T t) {
	this.mapper.insert(t);

    }

    @Override
    public void saveSelective(T t) {
	this.mapper.insertSelective(t);

    }

    @Override
    public void updateById(T t) {
	this.mapper.selectByPrimaryKey(t);

    }

    @Override
    public void updateBySelective(T t) {
	this.mapper.updateByPrimaryKeySelective(t);

    }

    @Override
    public void deleteById(Long id) {
	this.mapper.deleteByPrimaryKey(id);

    }

    @Override
    public void deleteByIds(List<Object> ids) {
	Example example = new Example(this.clazz);
	Criteria criteria = example.createCriteria();
	criteria.andIn("id", ids);
	this.mapper.deleteByExample(example);

    }

}
