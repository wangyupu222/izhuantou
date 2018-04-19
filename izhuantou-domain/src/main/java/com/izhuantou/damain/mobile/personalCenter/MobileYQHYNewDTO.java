package com.izhuantou.damain.mobile.personalCenter;

public class MobileYQHYNewDTO {

    /**
     * 邀请人数
     */
    private String yqNum;
    /**
     * 手机号
     */
    private String name;
    /**
     * 姓名
     */
    private String nameCN;
    /**
     * 注册时间
     */
    private String zcsj;
    /**
     * 出借时间
     */
    private String cjsj;
    /**
     * 出借金额
     */
    private String money;

    public String getYqNum() {
	return yqNum;
    }

    public void setYqNum(String yqNum) {
	this.yqNum = yqNum;
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

    public String getZcsj() {
	return zcsj;
    }

    public void setZcsj(String zcsj) {
	this.zcsj = zcsj;
    }

    public String getCjsj() {
	return cjsj;
    }

    public void setCjsj(String cjsj) {
	this.cjsj = cjsj;
    }

    public String getMoney() {
	return money;
    }

    public void setMoney(String money) {
	this.money = money;
    }

    @Override
    public String toString() {
	return "MobileYQHYNewDTO [yqNum=" + yqNum + ", name=" + name + ", nameCN=" + nameCN + ", zcsj=" + zcsj
		+ ", cjsj=" + cjsj + ", money=" + money + "]";
    }

}
