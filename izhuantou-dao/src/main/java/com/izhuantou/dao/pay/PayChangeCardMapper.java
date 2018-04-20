package com.izhuantou.dao.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.pay.PayChangeCard;

/**
 * 银行卡更换
 * 
 * @author dear
 * @version 1.0
 */
public interface PayChangeCardMapper extends Mapper<PayChangeCard> {
    /**
     * 根据用户OID查询用的更换银行卡记录
     * 
     * @param memberOID
     * @return
     */
    public List<PayChangeCard> findChangeCardList(@Param("memberOID") String memberOID,
	    @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 查询条数
     * 
     * @param memberOID
     * @return
     */
    public int getRowCount(String memberOID);

    /**
     * 插入一条换卡记录
     */
    public int saveChangeCardnotes(PayChangeCard change);

    /**
     * 更新记录
     * 
     * @param change
     * @return
     */
    public int updataChangeCardStart(PayChangeCard change);

    /**
     * 根据流水号查看换卡记录
     * 
     * @param requestID
     * @return
     */
    public PayChangeCard findChangeByRequestID(String requestID);

    /**
     * 根据流水号和状态查询
     * 
     * @param requestID
     * @param successStatus
     * @param requestStatus
     * @return
     */
    public List<PayChangeCard> findChangeByRequestAndSuccess(@Param("memberOID") String memberOID,
	    @Param("successStatus") String successStatus, @Param("requestStatus") String requestStatus);

    /**
     * 更新记录
     * 
     * @param change
     * @return
     */
    public int updataChangeCard(PayChangeCard change);

}
