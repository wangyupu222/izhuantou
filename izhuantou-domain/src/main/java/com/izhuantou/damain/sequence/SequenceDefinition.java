package com.izhuantou.damain.sequence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 序列号定义
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "sequence_definition")
public class SequenceDefinition extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 8935701861011072622L;

    /**
     * 序列号定义名称
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 序列号定义名称
     */
    private String name;
    /**
     * 序列号中文名称
     */
    @Column(name = "nameCN")
    private String nameCN;
    /**
     * 序列号格式
     */
    private String format;
    /**
     * 序列号值
     */
    private Integer value;

    /**
     * 清零时间
     */
    @Column(name = "clearTime")
    private String clearTime;

    /**
     * 生成时间
     */
    @Column(name = "creatDateTime")
    private Date creatDateTime;
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

    public String getNameCN() {
	return nameCN;
    }

    public void setNameCN(String nameCN) {
	this.nameCN = nameCN;
    }

    public String getFormat() {
	return format;
    }

    public void setFormat(String format) {
	this.format = format;
    }

    public Integer getValue() {
	return value;
    }

    public void setValue(Integer value) {
	this.value = value;
    }

    public String getClearTime() {
	return clearTime;
    }

    public void setClearTime(String clearTime) {
	this.clearTime = clearTime;
    }

    public Date getCreatDateTime() {
	return creatDateTime;
    }

    public void setCreatDateTime(Date creatDateTime) {
	this.creatDateTime = creatDateTime;
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
	return "SequenceDefinition [OID=" + OID + ", name=" + name + ", nameCN=" + nameCN + ", format=" + format
		+ ", value=" + value + ", clearTime=" + clearTime + ", creatDateTime=" + creatDateTime + ", describe0="
		+ describe0 + ", valid=" + valid + ", version=" + version + ", refresh=" + refresh + "]";
    }

}
