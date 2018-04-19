package com.izhuantou.service.api.personalCenter;

import java.util.Map;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.vo.MyInvitationDaoDTO;
import com.izhuantou.damain.vo.MyPrivilegeDaoDTO;

/**
 * 个人中心我的特权
 * 
 * @author yangbosen
 *
 */
public interface MyPrivilegeService {
    /**
     * 查看可用红包特权
     * 
     * @param memberOID
     * @param currentPage
     * @return
     */
    public Pagination<MyPrivilegeDaoDTO> findCanUseHBByMemberOID(String memberOID, Integer currentPage);

    /**
     * 不可用已过期红包特权
     * 
     * @param memberOID
     * @param currentPage
     * @return
     */
    public Pagination<MyPrivilegeDaoDTO> findCannotUseHBByMemberOID(String memberOID, Integer currentPage);

    /**
     * 查看已使用
     * 
     * @param memberOID
     * @param currentPage
     * @return
     */
    public Pagination<MyPrivilegeDaoDTO> findUseHBByMemberOID(String memberOID, Integer currentPage);

    /**
     * 查看可用加息券
     * 
     * @param memberOID
     * @param currentPage
     * @return
     */
    public Pagination<MyPrivilegeDaoDTO> findCanUseJxqByMemberOID(String memberOID, Integer currentPage);

    /**
     * 查看已使用加息券
     * 
     * @param memberOID
     * @param currentPage
     * @return
     */
    public Pagination<MyPrivilegeDaoDTO> findUseJxqByMemberOID(String memberOID, Integer currentPage);

    /**
     * 查看不可用加息券
     * 
     * @param memberOID
     * @param currentPage
     * @return
     */
    public Pagination<MyPrivilegeDaoDTO> findCannotUseJxqByMemberOID(String memberOID, Integer currentPage);

    /**
     * 查看邀请记录
     * 
     * @param memberOID
     * @param currentPage
     * @return
     */
    public Pagination<MyInvitationDaoDTO> findInvitationRecord(String memberOID, Integer currentPage);

    /**
     * 体验金
     * 
     * @param memberOID
     * @param currentPage
     * @return
     */
    public Map<String, Object> findExperience(String memberOID);

}
