package com.izhuantou.service.api.managerMenu;

import java.util.List;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.manager.ManagerUser;
import com.izhuantou.damain.manager.UserGroup;
import com.izhuantou.damain.vo.GroupDTO;
import com.izhuantou.service.api.BaseService;

public interface UserGroupService extends BaseService<UserGroup> {
    /**
     * 获取用户分组的所有父节点
     * 
     * @return
     */
    List<UserGroup> getUserGroupParentList();

    /**
     * 根据父节点的oid获取所有子菜单
     * 
     * @param oid
     * @return
     */
    List<UserGroup> getUserGroupList(String oid);

    /**
     * 根据子菜单的oid分页获取下属的用户
     * 
     * @param currentPage
     * @param oid
     * @return
     */
    Pagination<ManagerUser> queryPageListByoid(Integer currentPage, String oid);

    /**
     * 获取组织结构树
     * 
     * @return
     */
    List<GroupDTO> getUserGroupTree();

    /**
     * 添加组织结构树
     * 
     * @param oid
     * @param name
     * @return
     */
    boolean addGroup(String oid, String name);

}
