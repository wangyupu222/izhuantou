package com.izhuantou.dao.personalCenter;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.izhuantou.damain.vo.MyInvitationDaoDTO;
import com.izhuantou.damain.vo.MyPrivilegeDaoDTO;

public interface MyPrivilegeMapper {
    /**
     * 分页查询用户可用特权
     * 
     * @param memberOID
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<MyPrivilegeDaoDTO> findCanUseTQByMemberOIDAndType(@Param("memberOID") String memberOID,
	    @Param("privilegeType") Integer privilegeType, @Param("startIndex") Integer startIndex,
	    @Param("pageSize") Integer pageSize);

    /**
     * 可用特权获取总条数
     * 
     * @param memberOID
     * @param privilegeType
     * @return
     */
    public int getCanUseTQRowCount(@Param("memberOID") String memberOID, @Param("privilegeType") Integer privilegeType);

    /**
     * 不可用特权
     * 
     * @param memberOID
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<MyPrivilegeDaoDTO> findCannotUseTQByMemberOIDAndType(@Param("memberOID") String memberOID,
	    @Param("privilegeType") Integer privilegeType, @Param("startIndex") Integer startIndex,
	    @Param("pageSize") Integer pageSize);

    /**
     * 不可用特权获取总条数
     * 
     * @param memberOID
     * @param privilegeType
     * @return
     */
    public int getCannotUseTQRowCount(@Param("memberOID") String memberOID,
	    @Param("privilegeType") Integer privilegeType);

    /**
     * 已使用失效特权（加息）
     * 
     * @param memberOID
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<MyPrivilegeDaoDTO> findCannotUseTQByMemberOIDIsUse(@Param("memberOID") String memberOID,
	    @Param("privilegeType") Integer privilegeType, @Param("startIndex") Integer startIndex,
	    @Param("pageSize") Integer pageSize);

    /**
     * 已使用失效特权总数
     * 
     * @param memberOID
     * @param privilegeType
     * @return
     */
    public int getCannotUseTQRowCountIsUse(@Param("memberOID") String memberOID,
	    @Param("privilegeType") Integer privilegeType);

    /**
     * 已过期的特权
     * 
     * @param memberOID
     * @param privilegeType
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<MyPrivilegeDaoDTO> findCannotUseTQByMemberOIDIsGQ(@Param("memberOID") String memberOID,
	    @Param("privilegeType") Integer privilegeType, @Param("startIndex") Integer startIndex,
	    @Param("pageSize") Integer pageSize);

    /**
     * 已过期的特权总数
     * 
     * @param memberOID
     * @param privilegeType
     * @return
     */
    public int getCannotUseTQRowCountIsGQ(@Param("memberOID") String memberOID,
	    @Param("privilegeType") Integer privilegeType);

    /**
     * 邀请记录
     * 
     * @param memberOID
     * @param startIndex
     * @param pageSize
     * @return
     */
    public List<MyInvitationDaoDTO> findInvitationRecord(@Param("memberOID") String memberOID,
	    @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    /**
     * 已过期的特权总数
     * 
     * @param memberOID
     * @return
     */
    public int getInvitationRowCount(String memberOID);

}
