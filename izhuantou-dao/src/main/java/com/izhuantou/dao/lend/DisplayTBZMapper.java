package com.izhuantou.dao.lend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.DisplayTBZ;

/**
 * 头笔赚显示列表Dao层
 * 
 * @author yangbosen
 *
 */
public interface DisplayTBZMapper extends Mapper<DisplayTBZ> {

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
    public List<DisplayTBZ> findByCondition(@Param(value = "sortp") StringBuilder strsortword,
	    @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    /**
     * 总页数
     * 
     * @return
     */
    public long getPageCount();

    // 查询头笔赚列表不做分页
    public List<DisplayTBZ> findList();

}
