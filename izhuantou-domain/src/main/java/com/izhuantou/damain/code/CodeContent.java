package com.izhuantou.damain.code;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 编码内容
 * 
 * @author dear
 * @version 1.0
 * 
 */
@Table(name = "code_content")
public class CodeContent extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -72833436647142446L;
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 编码ID
     */
    @Column(name = "ID")
    private String ID;
    /**
     * 编码名称
     */
    @Column(name = "nameCN")
    private String nameCN;
    /**
     * 编码定义名称
     */
    @Column(name = "definitionName")
    private String definitionName;
    /**
     * 父类编码ID
     */
    @Column(name = "parentCodeID")
    private String parentCodeID;
    /**
     * 编码排序
     */
    @Column(name = "NO")
    private Integer NO;

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

    public String getID() {
	return ID;
    }

    public void setID(String iD) {
	ID = iD;
    }

    public String getNameCN() {
	return nameCN;
    }

    public void setNameCN(String nameCN) {
	this.nameCN = nameCN;
    }

    public String getDefinitionName() {
	return definitionName;
    }

    public void setDefinitionName(String definitionName) {
	this.definitionName = definitionName;
    }

    public String getParentCodeID() {
	return parentCodeID;
    }

    public void setParentCodeID(String parentCodeID) {
	this.parentCodeID = parentCodeID;
    }

    public Integer getNO() {
	return NO;
    }

    public void setNO(Integer nO) {
	NO = nO;
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
	return "CodeContent [OID=" + OID + ", ID=" + ID + ", nameCN=" + nameCN + ", definitionName=" + definitionName
		+ ", parentCodeID=" + parentCodeID + ", NO=" + NO + ", describe0=" + describe0 + ", valid=" + valid
		+ ", version=" + version + ", refresh=" + refresh + "]";
    }

}
