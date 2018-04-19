package com.izhuantou.damain.lend;

import com.izhuantou.damain.BasePojo;

/**
 * 体验金判断实体 yangbosen
 */
public class TyPro extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 体验过多少人
    private Integer count;
    // 是否体验过
    private String runTrue;
    // 是否出借过其他产品
    private Integer yfs;

    public Integer getCount() {
	return count;
    }

    public void setCount(Integer count) {
	this.count = count;
    }

    public String getRunTrue() {
	return runTrue;
    }

    public void setRunTrue(String runTrue) {
	this.runTrue = runTrue;
    }

    public Integer getYfs() {
	return yfs;
    }

    public void setYfs(Integer yfs) {
	this.yfs = yfs;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "TyPro [count=" + count + ", runTrue=" + runTrue + ", yfs=" + yfs + "]";
    }

}
