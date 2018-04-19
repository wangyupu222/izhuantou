package com.izhuantou.dao.lend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.DisplayDDT;

/**
 * 点点投显示列表Dao层
 * 
 * @author yangbosen
 *
 */
public interface DisplayDDTMapper extends Mapper<DisplayDDT> {
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
    public List<DisplayDDT> findByCondition(@Param(value = "sortp") StringBuilder sortp,
	    @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    /**
     * 总页数
     * 
     * @return
     */
    public long getPageCount();

    // 查询出点点投所有数据不进行分页暂时用于移动端
    public List<DisplayDDT> findList();
}
