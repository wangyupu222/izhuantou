package com.izhuantou.damain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.izhuantou.damain.BasePojo;

/**
 * 后台系统操作记录日志实体
 *
 * @author fucheng
 * @date 2018-01-18
 */
@Document(collection = "ManagerOperateLog")
@CompoundIndexes({ @CompoundIndex(name = "index_unique", def = "{'name': 1, 'created': 1}", unique = true) })
public class ManagerOperateLog extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @Indexed
    // 操作名称
    private String name;
    // 用户名称
    private String managerName;
    // 访问IP
    private String ipAddress;
    // 操作数据详情
    private String details;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getManagerName() {
	return managerName;
    }

    public void setManagerName(String managerName) {
	this.managerName = managerName;
    }

    public String getIpAddress() {
	return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
	this.ipAddress = ipAddress;
    }

    public String getDetails() {
	return details;
    }

    public void setDetails(String details) {
	this.details = details;
    }

    @Override
    public String toString() {
	return "ManagerOperateLog [id=" + id + ", name=" + name + ", managerName=" + managerName + ", ipAddress="
		+ ipAddress + ", details=" + details + "]";
    }

}
