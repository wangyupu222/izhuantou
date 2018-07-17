package com.izhuantou.dao.user;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.user.MemberMemberAgreement;
import com.izhuantou.damain.vo.LeanYuBiaoDTO;

/**
 * 用户协议
 * 
 * @author dear
 * @version 1.0
 */
public interface MemberMemberAgreementMapper extends Mapper<MemberMemberAgreement> {
    /**
     * 合同类型（1 砖头网注册协议 2 抵押借款协议 3 砖头网点点投服务协议 4 砖头网环环投服务协议 5 砖头网新手标服务协议 6
     * 砖头网信贷借款协议 7 砖头网债权转让协议 8 逾期债权预收购协议 9 砖头网转转投服务协议 ） 借款协议
     * 
     * @param biddingOID
     *            标的OID
     * @param contractType
     * @return
     */
     MemberMemberAgreement gainAgreementJKXYCK(@Param("biddingOID") String biddingOID,
	    @Param("contractType") String contractType);

    /**
     * 根据ckOID和类型查看
     * 
     * @param biddingOID
     * @param contractType
     * @return
     */
     MemberMemberAgreement gainAgreementXYCK2(@Param("ckOID") String ckOID,
	    @Param("contractType") String contractType);

    /**
     * 根据loanNumber协议编号和类型查看
     * 
     * @param biddingOID
     * @param contractType
     * @return
     */
     MemberMemberAgreement gainAgreementJKZX(@Param("loanNumber") String loanNumber,
	    @Param("contractType") String contractType);

    /**
     * 保存一条服务协议
     * 
     * @param agreement
     * @return
     */
     int saveMemberAgreement(MemberMemberAgreement agreement);

    /**
     * 协议总数
     * 
     * @param agreement
     * @return
     */
     int countMemberAgreement(@Param("biddingType") String biddingType,
	    @Param("contractType") String contractType);

    /**
     * 根据用户OID和产品OID查看债权转让协议
     * 
     * @param memberOID
     * @param biddingOID
     * @return
     */
     MemberMemberAgreement findBymemberOIDBiddingOIDForZQZR(@Param("memberOID") String memberOID,
	    @Param("biddingOID") String biddingOID);

    /**
     * 根据协议编号查看原标
     * 
     * @param loanNumber
     * @return
     */
     LeanYuBiaoDTO findByYBInfoByJKXY(String loanNumber);

    /**
     * 查看协议的编号
     * 
     * @param biddingOID
     * @param contractType
     * @return
     */
     String findByAgreementJKXYBHCK(@Param("biddingOID") String biddingOID,
	    @Param("contractType") String contractType);
    
    /**
     * 根据投资人OID查询头笔赚协议
     */
    public MemberMemberAgreement findTBZAgreementByOID(@Param("memberOID") String memberOID,
    		@Param("contractType") String contractType);
}
