package com.izhuantou.damain.lend;

import com.izhuantou.damain.BasePojo;

/**
 * 风控信息审核
 * 
 * @author yangbosen
 *
 */
public class AuditInfo extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 照片的路径值
     */
    private String photoOID;
    /**
     * 表OID值
     */
    private String mainOID;
    /**
     * 照片名
     */
    private String nameCN;

    public String getPhotoOID() {
	return photoOID;
    }

    public void setPhotoOID(String photoOID) {
	this.photoOID = photoOID;
    }

    public String getMainOID() {
	return mainOID;
    }

    public void setMainOID(String mainOID) {
	this.mainOID = mainOID;
    }

    public String getNameCN() {
	return nameCN;
    }

    public void setNameCN(String nameCN) {
	this.nameCN = nameCN;
    }

    @Override
    public String toString() {
	return "AuditInfo [photoOID=" + photoOID + ", mainOID=" + mainOID + ", nameCN=" + nameCN + "]";
    }

}
