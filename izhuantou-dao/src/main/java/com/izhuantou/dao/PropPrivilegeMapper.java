package com.izhuantou.dao;

import java.util.Map;

/**
 * 之前在privilege.properties中的内容
 * @author dear
 *
 */
public interface PropPrivilegeMapper {

	Map<String,String> findPrivilege();
}
