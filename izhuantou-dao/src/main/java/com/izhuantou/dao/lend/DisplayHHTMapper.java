package com.izhuantou.dao.lend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.DisplayHHT;

/**
 * 环环投显示列表Dao层
 * 
 * @author yangbosen
 *
 */
public interface DisplayHHTMapper extends Mapper<DisplayHHT> {
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
    public List<DisplayHHT> findByCondition(@Param(value = "sortp") StringBuilder sortp,
	    @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    /**
     * 总页数
     * 
     * @return
     */
    public long getPageCount();

    /** 根据OID查询单条环环投信息 */
    public DisplayHHT findByOID(String OID);

    // 查找整个环环投列表此方法暂时用于移动端口
    public List<DisplayHHT> findList();

}
