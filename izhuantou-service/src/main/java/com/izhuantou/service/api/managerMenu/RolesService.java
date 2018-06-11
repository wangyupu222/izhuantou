package com.izhuantou.service.api.managerMenu;

import java.util.List;

import com.izhuantou.damain.manager.Roles;
import com.izhuantou.damain.vo.RolesAndUserDTO;
import com.izhuantou.damain.vo.RolesDTO;
import com.izhuantou.service.api.BaseService;

public interface RolesService extends BaseService<Roles> {

    /**
     * 根据parentID获取角色
     * 
     * @param parentId
     * @return
     */
    public List<Roles> queryRolesByParentId(String parentId);

    public List<RolesDTO> queryRoleTree();

    /**
     * 根据上一级角色oid获取子角色及拥有该角色的用户
     * 
     * @param oid
     * @return
     */
    public List<RolesAndUserDTO> getRolesAndUser(String oid);

}
