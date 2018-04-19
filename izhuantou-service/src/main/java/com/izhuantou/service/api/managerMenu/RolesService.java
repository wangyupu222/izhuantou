package com.izhuantou.service.api.managerMenu;

import java.util.List;

import com.izhuantou.damain.Roles;
import com.izhuantou.service.api.BaseService;

public interface RolesService extends BaseService<Roles> {

    public List<Roles> queryRolesByParentId(String parentId);

}
