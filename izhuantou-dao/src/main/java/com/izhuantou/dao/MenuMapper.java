package com.izhuantou.dao;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.manager.Menu;

public interface MenuMapper extends Mapper<Menu> {

    /**
     * 根据oid获取父Id
     * 
     * @param list
     * @return
     */
    List<String> queryParentIdByOid(List<String> list);

    /**
     * 根据oid 获取菜单详情
     * 
     * @param list
     * @return
     */
    List<Menu> queryDetailByOid(List<String> list);

    /**
     * 根据oid获取菜单详情
     * 
     * @param menuOid
     * @return
     */
    Menu queryMenuByOid(String oid);

}
