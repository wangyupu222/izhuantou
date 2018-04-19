package com.izhuantou.dao.p2p;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pPageproblems;

/**
 * 帮助中心问题
 * 
 * @author dear
 * @version 1.0
 */
public interface P2pPageproblemsMapper extends Mapper<P2pPageproblems> {
    /**
     * 根据parentOID分页查询问题
     * 
     * @param parentOID
     * @param currentPage
     * @return
     */
    List<P2pPageproblems> findProblems(@Param("parentOID") String parentOID, @Param("startIndex") Integer startIndex,
	    @Param("pageSize") Integer pageSize);

    /**
     * 查询总数条数
     * 
     * @return
     */
    int getRowCount(String parentOID);

}
