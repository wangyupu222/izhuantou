package com.izhuantou.dao.personalCenter;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayCustomerBusiness;

public interface MyCashMapper extends Mapper<PayCustomerBusiness> {
	/**
	 * 根据日期限制条件去查询
	 * @param memberOID
	 * @param startPos
	 * @param pageSize
	 * @param strDate
	 * @param endDate
	 * @return
	 */
    public List<PayCustomerBusiness> findCashDetialByCondition(@Param(value = "memberOID") String memberOID,
	    @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize,
	    @Param(value = "strDate") Date strDate, @Param(value = "endDate") Date endDate);

   /**
    * 查询条数
    * @param memberOID
    * @param strDate
    * @param endDate
    * @return
    */
    public Integer findcountByCondition(@Param(value = "memberOID") String memberOID,
	    @Param(value = "strDate") Date strDate, @Param(value = "endDate") Date endDate);

}
