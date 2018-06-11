package com.izhuantou.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.manager.ManagerUserToRoles;

public interface ManagerUserToRolesMapper extends Mapper<ManagerUserToRoles> {

    /**
     * 获取该oid的所有用户
     * 
     * @param oid
     * @return
     */
    List<String> getUserOidByRolesOid(String rolesOid);

}
