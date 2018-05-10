package com.izhuantou.dao.personalCenter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.izhuantou.damain.vo.HHThavingDetailsDTO;
import com.izhuantou.damain.vo.MyLentTBZDaoDTO;
import com.izhuantou.damain.vo.MylendDDTDaoDTO;
import com.izhuantou.damain.vo.MylendDaoDTO;
import com.izhuantou.damain.vo.MylendZQZRDTO;

public interface MylendMapper {
    /**
     * 根据memberOID查询用户现在持有中的环环投
     * 
     * @param memberOID
     * @return
     */
    public List<MylendDaoDTO> findHHhavingBymemberOID(@Param("memberOID") String memberOID,
	    @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 获取持有总条数
     * 
     * @param memberOID
     * @return
     */
    public int gethavingRowCount(String memberOID);

    /**
     * 通过产品OID在产品与标的内容映射表上查找，该产品OID对应的内容标的OID。再通过标的OID在借贷关系表中查找对应的真实借贷关系
     * 
     * @param mainOID
     * @return
     */
    public int gainRecordeFromDebitCredit(String mainOID);

    /**
     * 我的出借-》环环投持有中列表详情列表
     * 
     * @param cashPoolOID
     * @return
     */
    public List<HHThavingDetailsDTO> findHHhavingDetails(String cashPoolOID);

    /**
     * 已完成环环投列表
     * 
     * @param memberOID
     * @return
     */
    public List<MylendDaoDTO> findHHTWCBymemberOID(@Param("memberOID") String memberOID,
	    @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 获取总条数
     * 
     * @param memberOID
     * @return
     */
    public int getWCRowCount(String memberOID);

    /**
     * 根据memberOID查询用户现在持有中的头笔赚
     * 
     * @param memberOID
     * @return
     */
    public List<MyLentTBZDaoDTO> findTBZhavingBymemberOID(String memberOID);

    /**
     * 根据memberOID查询用户完成的头笔赚
     * 
     * @param memberOID
     * @return
     */
    public List<MyLentTBZDaoDTO> findTBZWCBymemberOID(String memberOID);

    /**
     * 根据memberOID查询用户持有中的点点投
     * 
     * @param memberOID
     * @return
     */
    public List<MylendDDTDaoDTO> findDDThavingBymemberOID(@Param("memberOID") String memberOID,
	    @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 获取持有点点投的总数
     * 
     * @param memberOID
     * @return
     */
    public int getddtRowCount(String memberOID);

    /**
     * 根据memberOID查询用户完成的点点投
     * 
     * @param memberOID
     * @return
     */
    public List<MylendDDTDaoDTO> findDDTWCBymemberOID(@Param("memberOID") String memberOID,
	    @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 获取已完成点点投的总数
     * 
     * @param memberOID
     * @return
     */
    public int getddtWCRowCount(String memberOID);

    /**
     * 根据memberOID查询用户债转申请列表
     * 
     * @param memberOID
     * @return
     */
    public List<MylendZQZRDTO> findZQZRByPageAndmemberOID(@Param("memberOID") String memberOID,
	    @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 债权转让申请列表总条数
     * 
     * @param memberOID
     * @return
     */
    public int getZQZRRowCount(String memberOID);

    /**
     * 根据memberOID查询用户债转申请列表
     * 
     * @param memberOID
     * @return
     */
    public List<MylendZQZRDTO> findZQZRBymemberOID(String memberOID);

}
