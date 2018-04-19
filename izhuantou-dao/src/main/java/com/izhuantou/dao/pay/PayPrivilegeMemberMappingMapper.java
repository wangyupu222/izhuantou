package com.izhuantou.dao.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayPrivilegeMemberMapping;

/**
 * 特权红包加息与出借人关系映射
 * 
 * @author dear
 * @version 1.0
 */
public interface PayPrivilegeMemberMappingMapper extends Mapper<PayPrivilegeMemberMapping> {

    public List<PayPrivilegeMemberMapping> findByCondition(@Param("tqOID") String tqOID,
	    @Param("memberOID") String memberOID);

    /**
     * 保存用户的优惠券
     * 
     * @param privelege
     * @return
     */
    public int saveUserPrivelege(PayPrivilegeMemberMapping privelege);

    /**
     * 根据OID查询
     * 
     * @param OID
     * @return
     */
    public PayPrivilegeMemberMapping findByOID(String OID);

    /**
     * 更新
     * 
     * @param privelege
     * @return
     */
    public int updatePrivilegeMemberMapping(PayPrivilegeMemberMapping privelege);

}
