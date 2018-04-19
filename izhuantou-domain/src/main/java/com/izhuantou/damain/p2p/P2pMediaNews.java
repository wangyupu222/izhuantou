package com.izhuantou.damain.p2p;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import com.izhuantou.damain.BasePojo;

/**
 * 媒体报道
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "p2p_medianews")
public class P2pMediaNews extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1646866048897511707L;

    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 序列号定义名称
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 上传图片
     */
    @Column(name = "uploadImg")
    private String uploadImg;
    /**
     * 上传图片
     */
    @Column(name = "uploadImgOID")
    private String uploadImgOID;

    /**
     * 公告内容
     */
    private String messages;

    /**
     * 父级OID
     */
    @Column(name = "parentOID")
    private String parentOID;

    /**
     * 分支实体
     */
    @Column(name = "branchEntity")
    private String branchEntity;

    /**
     * 数据地址
     */
    @Column(name = "datePath")
    private String datePath;

    private String describe0;
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

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getUploadImg() {
	return uploadImg;
    }

    public void setUploadImg(String uploadImg) {
	this.uploadImg = uploadImg;
    }

    public String getUploadImgOID() {
	return uploadImgOID;
    }

    public void setUploadImgOID(String uploadImgOID) {
	this.uploadImgOID = uploadImgOID;
    }

    public String getMessages() {
	return messages;
    }

    public void setMessages(String messages) {
	this.messages = messages;
    }

    public String getParentOID() {
	return parentOID;
    }

    public void setParentOID(String parentOID) {
	this.parentOID = parentOID;
    }

    public String getBranchEntity() {
	return branchEntity;
    }

    public void setBranchEntity(String branchEntity) {
	this.branchEntity = branchEntity;
    }

    public String getDatePath() {
	return datePath;
    }

    public void setDatePath(String datePath) {
	this.datePath = datePath;
    }

    public String getDescribe0() {
	return describe0;
    }

    public void setDescribe0(String describe0) {
	this.describe0 = describe0;
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
	return "P2pMediaNews [OID=" + OID + ", name=" + name + ", author=" + author + ", uploadImg=" + uploadImg
		+ ", uploadImgOID=" + uploadImgOID + ", messages=" + messages + ", parentOID=" + parentOID
		+ ", branchEntity=" + branchEntity + ", datePath=" + datePath + ", describe0=" + describe0 + ", NO="
		+ NO + ", addUserOID=" + addUserOID + ", updUserOID=" + updUserOID + ", valid=" + valid
		+ ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime + ", refresh=" + refresh
		+ ", version=" + version + "]";
    }

}
