package com.izhuantou.damain.mobile.detial;

import com.izhuantou.damain.BasePojo;

/**
 * 风控审核实体
 * 
 * @author yangbosen
 *
 */
public class PhotoInfo extends BasePojo {

    /**
     * 风控照片实体
     */
    private static final long serialVersionUID = 1L;
    /**
     * 照片ID
     */
    private String photoOID;
    /**
     * 照片信息
     */
    private String psnr;

    public String getPhotoOID() {
	return photoOID;
    }

    public void setPhotoOID(String photoOID) {
	this.photoOID = photoOID;
    }

    public String getPsnr() {
	return psnr;
    }

    public void setPsnr(String psnr) {
	this.psnr = psnr;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "PhotoInfo [photoOID=" + photoOID + ", psnr=" + psnr + "]";
    }

}
