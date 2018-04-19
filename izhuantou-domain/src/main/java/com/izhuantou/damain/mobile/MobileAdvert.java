package com.izhuantou.damain.mobile;

import com.izhuantou.damain.BasePojo;

/**
 * 移动端广告
 * 
 * @author yangbosen
 *
 */
public class MobileAdvert extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 图片地址
     */
    private String url;
    /**
     * 图片名
     */
    private String pic;
    /**
     * 标题
     */
    private String tittle;
    /**
     * 类型
     */
    private String type;
    /**
     * 状态
     */
    private String status;

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public String getPic() {
	return pic;
    }

    public void setPic(String pic) {
	this.pic = pic;
    }

    public String getTittle() {
	return tittle;
    }

    public void setTittle(String tittle) {
	this.tittle = tittle;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "MobileAdvert [url=" + url + ", pic=" + pic + ", tittle=" + tittle + ", type=" + type + ", status="
		+ status + "]";
    }

}
