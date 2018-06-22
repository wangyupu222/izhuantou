package com.izhuantou.dao.contentManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pPageproblems;
import com.izhuantou.damain.vo.PageproblemsDTO;
/**
 * 帮助中心问题
 * @author liyang
 * @version 1.0
 */
public interface ContentPageProblemMapper extends Mapper<P2pPageproblems> {
	/**
	 * 查询问题总条数
	 * @param parentOID 问题类型oid
	 */
	public int getRowCount(@Param("parentOID") String parentOID);
	
	/**
	 * 按条件查询问题总条数
	 * pageproblemsDTO 问题DTOBean
	 */
	public int getRowCountByTypes(PageproblemsDTO pageproblemsDTO);

	/**
     * 根据parentOID分页查询问题
     * pageproblemsDTO 问题DTOBean
     * @param startIndex 起始条数
     * @param pageSize 每页条数
     * @return
     */
    public List<P2pPageproblems> findProblems(@Param("proDTO") PageproblemsDTO pageproblemsDTO, @Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
    
    /**
     * 添加一条帮助中心问题
     * @param p2pPageproblems 问题bean
     * @return 
     */
    public int addProblem(P2pPageproblems p2pPageproblems);
    
    /**
     * 修改一条帮助中心问题
     * @param p2pPageproblems 问题bean
     * @return 
     */
    public int updateProblem(P2pPageproblems p2pPageproblems);
    
    /**
     * 删除一条帮助中心问题 实际上是修改valid（有效性）值为0
     * @param OID 问题oid
     * @return
     */
    public int deleteProblem(String OID);
    
    /**
     * 根据oid查询一条问题
     * @param OID 问题oid
     * @return
     */
    public P2pPageproblems findProblemById(String OID);
}
