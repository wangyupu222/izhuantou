package com.izhuantou.damain.p2p;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 会员用户信息表
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "p2p_huiyuanuser")
public class P2pHuiYuanUser extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 2912685112451419298L;

    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 客户定义名称
     */
    private String name;

    /**
     * 客户来源
     */
    private String kehulaiyuan;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 身份正好
     */
    private String card;

    /**
     * 平台注册编号
     */
    private String pingtai;
    /**
     * 注册日期
     */

    private String time;
    /**
     * 客户类型
     */

    private String kehutype;
    /**
     * 客户类型
     */

    private String message;
    /**
     * 
     * 客服userOID
     */
    @Column(name = "kefuuserOID")
    private String kefuuserOID;
    /**
     * 序号
     */
    @Column(name = "NO")
    private Integer NO;
    /**
     * 增加用户OID
     */
    @Column(name = "addUserOID")
    private String addUserOID;
    /**
     * 修改用户OID
     */
    @Column(name = "updUserOID")
    private String updUserOID;
    /**
     * 是否有效
     */
    @Column(name = "valid")
    private Boolean valid;

    /**
     * 添加的时间
     */
    @Column(name = "addDateTime")
    private Date addDateTime;
    /**
     * 更新的时间
     */
    @Column(name = "updDateTime")
    private Date updDateTime;
    /**
     * 是否更新
     */

    private Boolean refresh;
    /**
     * 版本号
     */
    private Integer version;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getKehulaiyuan() {
	return kehulaiyuan;
    }

    public void setKehulaiyuan(String kehulaiyuan) {
	this.kehulaiyuan = kehulaiyuan;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getCard() {
	return card;
    }

    public void setCard(String card) {
	this.card = card;
    }

    public String getPingtai() {
	return pingtai;
    }

    public void setPingtai(String pingtai) {
	this.pingtai = pingtai;
    }

    public String getTime() {
	return time;
    }

    public void setTime(String time) {
	this.time = time;
    }

    public String getKehutype() {
	return kehutype;
    }

    public void setKehutype(String kehutype) {
	this.kehutype = kehutype;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getKefuuserOID() {
	return kefuuserOID;
    }

    public void setKefuuserOID(String kefuuserOID) {
	this.kefuuserOID = kefuuserOID;
    }

    public Integer getNO() {
	return NO;
    }

    public void setNO(Integer nO) {
	NO = nO;
    }

    public String getAddUserOID() {
	return addUserOID;
    }

    public void setAddUserOID(String addUserOID) {
	this.addUserOID = addUserOID;
    }

    public String getUpdUserOID() {
	return updUserOID;
    }

    public void setUpdUserOID(String updUserOID) {
	this.updUserOID = updUserOID;
    }

    public Boolean getValid() {
	return valid;
    }

    public void setValid(Boolean valid) {
	this.valid = valid;
    }

    public Date getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
	this.addDateTime = addDateTime;
    }

    public Date getUpdDateTime() {
	return updDateTime;
    }

    public void setUpdDateTime(Date updDateTime) {
	this.updDateTime = updDateTime;
    }

    public Boolean getRefresh() {
	return refresh;
    }

    public void setRefresh(Boolean refresh) {
	this.refresh = refresh;
    }

    public Integer getVersion() {
	return version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @Override
    public String toString() {
	return "P2pHuiYuanUser [OID=" + OID + ", name=" + name + ", kehulaiyuan=" + kehulaiyuan + ", phone=" + phone
		+ ", card=" + card + ", pingtai=" + pingtai + ", time=" + time + ", kehutype=" + kehutype + ", message="
		+ message + ", kefuuserOID=" + kefuuserOID + ", NO=" + NO + ", addUserOID=" + addUserOID
		+ ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime="
		+ updDateTime + ", refresh=" + refresh + ", version=" + version + "]";
    }

}
