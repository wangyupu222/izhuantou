package com.izhuantou.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.manager.ManagerUser;

public interface ManagerUserMapper extends Mapper<ManagerUser> {

    /**
     * 根据用户oidList 获取用户详情list
     * 
     * @param userOidList
     * @return
     */
    List<ManagerUser> getUserListByOidList(List<String> list);

}
