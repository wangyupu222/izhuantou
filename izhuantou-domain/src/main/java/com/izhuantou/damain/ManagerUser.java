package com.izhuantou.damain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户实体类
 *
 * @author fucheng
 * @date 2018-01-17
 */
@Table(name = "tb_manager_user")
public class ManagerUser extends BasePojo {
    /** 
    *  
    */
    private static final long serialVersionUID = 1L;
    // 唯一主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String oid;
    // 用户真实姓名
    private String name;
    // 用户登录账号
    private String userName;
    // 用户登录密码
    private String passWard;
    // 用户状态 0 正常 ，1 已删除
    private Integer status;

    public String getOid() {
	return oid;
    }

    public void setOid(String oid) {
	this.oid = oid;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassWard() {
	return passWard;
    }

    public void setPassWard(String passWard) {
	this.passWard = passWard;
    }

    public Integer getStatus() {
	return status;
    }

    public void setStatus(Integer status) {
	this.status = status;
    }

    @Override
    public String toString() {
	return "ManagerUser [oid=" + oid + ", name=" + name + ", userName=" + userName + ", passWard=" + passWard
		+ ", status=" + status + "]";
    }

}
