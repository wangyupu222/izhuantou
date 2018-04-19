package com.izhuantou.damain.p2p;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import com.izhuantou.damain.BasePojo;

/**
 * 图片信息
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "file_info")
public class FileInfo extends BasePojo {

    /**
    * 
    */
    private static final long serialVersionUID = -524091276768505580L;

    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 名称
     */
    private String name;

    /**
     * 来源
     */
    private String source;
    /**
     * 物理名
     */
    @Column(name = "physicalName")
    private String physicalName;
    /**
     * 路径
     */
    @Column(name = "relativePath")
    private String relativePath;
    /**
     * 长度
     */
    private Long length;
    /**
     * 内容
     */
    @Column(name = "contentOID")
    private String contentOID;
    /**
     * 上传时间
     */
    @Column(name = "uploadDateTime")
    private Date uploadDateTime;

    /**
     * MD5校验码
     */
    private String md5;
    /**
     * 描述
     */
    private String describe0;
    /**
     * 是否有效
     */
    private Boolean valid;
    /**
     * 版本号
     */
    private Integer version;
    /**
     * 是否更新
     */
    private Boolean refresh;

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

    public String getSource() {
	return source;
    }

    public void setSource(String source) {
	this.source = source;
    }

    public String getPhysicalName() {
	return physicalName;
    }

    public void setPhysicalName(String physicalName) {
	this.physicalName = physicalName;
    }

    public String getRelativePath() {
	return relativePath;
    }

    public void setRelativePath(String relativePath) {
	this.relativePath = relativePath;
    }

    public Long getLength() {
	return length;
    }

    public void setLength(Long length) {
	this.length = length;
    }

    public String getContentOID() {
	return contentOID;
    }

    public void setContentOID(String contentOID) {
	this.contentOID = contentOID;
    }

    public Date getUploadDateTime() {
	return uploadDateTime;
    }

    public void setUploadDateTime(Date uploadDateTime) {
	this.uploadDateTime = uploadDateTime;
    }

    public String getMd5() {
	return md5;
    }

    public void setMd5(String md5) {
	this.md5 = md5;
    }

    public String getDescribe0() {
	return describe0;
    }

    public void setDescribe0(String describe0) {
	this.describe0 = describe0;
    }

    public Boolean getValid() {
	return valid;
    }

    public void setValid(Boolean valid) {
	this.valid = valid;
    }

    public Integer getVersion() {
	return version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    public Boolean getRefresh() {
	return refresh;
    }

    public void setRefresh(Boolean refresh) {
	this.refresh = refresh;
    }

    @Override
    public String toString() {
	return "FileInfo [OID=" + OID + ", name=" + name + ", source=" + source + ", physicalName=" + physicalName
		+ ", relativePath=" + relativePath + ", length=" + length + ", contentOID=" + contentOID
		+ ", uploadDateTime=" + uploadDateTime + ", md5=" + md5 + ", describe0=" + describe0 + ", valid="
		+ valid + ", version=" + version + ", refresh=" + refresh + "]";
    }

}
