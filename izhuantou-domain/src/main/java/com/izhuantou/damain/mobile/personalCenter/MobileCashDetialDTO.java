package com.izhuantou.damain.mobile.personalCenter;

public class MobileCashDetialDTO {

    /**
     * 类型
     */
    private String lx;
    /**
     * 时间
     */
    private String updDateTime;
    /**
     * 符号
     */
    private String fuhao;
    /**
     * 金额
     */
    private String money;
    /**
     * 添加时间
     */
    private String addDateTime;

    public String getLx() {
	return lx;
    }

    public void setLx(String lx) {
	this.lx = lx;
    }

    public String getUpdDateTime() {
	return updDateTime;
    }

    public void setUpdDateTime(String updDateTime) {
	this.updDateTime = updDateTime;
    }

    public String getFuhao() {
	return fuhao;
    }

    public void setFuhao(String fuhao) {
	this.fuhao = fuhao;
    }

    public String getMoney() {
	return money;
    }

    public void setMoney(String money) {
	this.money = money;
    }

    public String getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(String addDateTime) {
	this.addDateTime = addDateTime;
    }

    @Override
    public String toString() {
	return "MobileCashDetialDTO [lx=" + lx + ", updDateTime=" + updDateTime + ", fuhao=" + fuhao + ", money="
		+ money + ", addDateTime=" + addDateTime + "]";
    }

}
