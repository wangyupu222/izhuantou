package com.izhuantou.data.rpc.api;

import java.util.List;

/**
 * service基类
 *
 * @author fucheng
 * @date 2018-01-06
 */

public interface BaseService<T> {

	/**
	 * 根据ID进行查询
	 * 
	 * @param id
	 * @return
	 */
	public T queryByid(String id);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<T> queryAll();

	/**
	 * 根据条件查询总条数
	 * 
	 * @param t
	 * @return
	 */
	public int queryCountByWhere(T t);

	/**
	 * 根据条件查询结果集
	 * 
	 * @param t
	 * @return
	 */
	public List<T> queryListByWhere(T t);

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> queryByPage(Integer page, Integer rows);

	/**
	 * 根据条件查询一条数据
	 * 
	 * @param t
	 */
	public T queryOne(T t);

	/**
	 * 新增数据，忽略空字段的
	 * 
	 * @param t
	 */
	public void save(T t);

	/**
	 * 新增数据，不忽略空字段的
	 * 
	 * @param t
	 */
	public void saveSelective(T t);

	/**
	 * 根据ID更新，忽略空字段
	 * 
	 * @param t
	 */
	public void updateById(T t);

	/**
	 * 根据ID更新，不忽略空字段
	 * 
	 * @param t
	 */
	public void updateBySelective(T t);

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 */
	public void deleteById(Long id);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void deleteByIds(List<Object> ids);

}
