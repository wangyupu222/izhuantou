package com.izhuantou.dao.personalCenter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.izhuantou.damain.message.MessageContentBusiness;
import com.izhuantou.damain.vo.JoinSelectAllDTO;
import com.izhuantou.damain.vo.LendMoneyDTO;

public interface PersonalCenterMapper {
    /** 查询所有未读信息 */
    public List<MessageContentBusiness> findAllMessage(@Param(value = "receiveUserOID") String receiveUserOID,
	    @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    /** 查询所有已读信息 */
    public List<MessageContentBusiness> findALLHistoryMessage(@Param(value = "receiveUserOID") String receiveUserOID,
	    @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    /** 查询一条未读信息 */
    public Integer moveMessageContentNoPush(String OID);

    /** 插入一条未读信息到已读信息中 */
    public Integer insertMessage(MessageContentBusiness message);

    /** 查询一条未读信息 */
    public MessageContentBusiness selectOneMessage(String OID);

    /** 查询所有未读条数 */
    public Integer countMessage(String receiveUserOID);

    /** 查询所有已读条数 */
    public Integer countHistoryMessage(String receiveUserOID);

    /**
     * 借款账户列表
     * 
     * @param memberOID
     * @return
     */
    public List<LendMoneyDTO> findLendMoneyBymemberOID(String memberOID);

    /**
     * 查询投资点点投用户相关资金
     * 
     * @param memberOID
     * @param state
     * @return
     */
    public List<JoinSelectAllDTO> gainDDReturnMoneyBymemberOIDANDState(@Param("memberOID") String memberOID,
	    @Param("state") String state);

}
