package com.izhuantou.dao.user;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.user.MemberMemberAgreement;

/**
 * 协议模板
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
    public MemberMemberAgreement gainAgreementJKXYCK(@Param("biddingOID") String biddingOID,
	    @Param("contractType") String contractType);

    /**
     * 根据ckOID和类型查看
     * 
     * @param biddingOID
     * @param contractType
     * @return
     */
    public MemberMemberAgreement gainAgreementXYCK2(@Param("ckOID") String ckOID,
	    @Param("contractType") String contractType);

    /**
     * 根据loanNumber协议编号和类型查看
     * 
     * @param biddingOID
     * @param contractType
     * @return
     */
    public MemberMemberAgreement gainAgreementJKZX(@Param("loanNumber") String loanNumber,
	    @Param("contractType") String contractType);

    /**
     * 保存一条服务协议
     * 
     * @param agreement
     * @return
     */
    public int saveMemberAgreement(MemberMemberAgreement agreement);

}
