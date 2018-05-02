package com.izhuantou.service.api.personalCenter;

import java.util.List;
import java.util.Map;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.message.MessageContentBusiness;
import com.izhuantou.damain.vo.LendMoneyDTO;
import com.izhuantou.damain.vo.UserDTO;

/**
 * 个人消息接口
 * 
 * @author yangbosen
 *
 */
public interface PersonalCenterService {

    /**
     * 查询未读消息
     * 
     * @param page
     * @param memberOID
     * @return
     */
    public Pagination<MessageContentBusiness> findMessage(Integer page, String memberOID);

    /**
     * 查询已读消息
     * 
     * @param page
     * @param memberOID
     * @return
     */
    public Pagination<MessageContentBusiness> findHistoryMessage(Integer page, String memberOID);

    /**
     * 根据memberOID找到消息总条数和未读条数
     * 
     * @param memberOID
     * @return
     */
    public Map<String, Integer> findMessageNum(String memberOID);

    /**
     * 删除未读信息添加到已读信息中
     * 
     * @param OID
     */
    public void insertMessage(String OID);

    /**
     * 查询个人中心 中心首页的信息
     * 
     * @param memberOID
     */
    public Map<String, Object> userCenterIndex(String memberOID);

    /**
     * 查询借款账户
     * 
     * @param memberOID
     * @return
     */
    public List<LendMoneyDTO> findLendMoneyBymemberOID(String memberOID);

    /**
     * 查看用户的安全信息认证
     * 
     * @param Usermobile
     * @return
     */
    public Map<String, Object> SecurityCenter(String Usermobile);

    /**
     * 根据原来密码修改密码
     * 
     * @param memberOID
     * @param oldPwd
     * @param newPwd
     * @return
     */
    public String updatePasswordByOld(UserDTO user);

    /**
     * 添加或修改密保
     * 
     * @param user
     * @return
     */
    public String insertTwoQuestion(UserDTO user);

}
