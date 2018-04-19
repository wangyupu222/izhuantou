package com.izhuantou.dao.lend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.DisplayZZT;

/**
 * 转转投显示列表Dao层
 * 
 * @author yangbosen
 *
 */
public interface DisplayZZTMapper extends Mapper<DisplayZZT> {

    /**
     * 点点投展示
     * 
     * @param sortp
     *            排序字段
     * @param startPos
     *            起始页
     * @param pageSize
     *            页数
     * @return
     */
    public List<DisplayZZT> findByCondition(@Param(value = "sortp") StringBuilder sortp,
	    @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    /**
     * 总页数
     * 
     * @return
     */
    public long getPageCount();

    // 查找转转投列表数据 不进行分页 暂时用于 移动端
    public List<DisplayZZT> findList();
}
