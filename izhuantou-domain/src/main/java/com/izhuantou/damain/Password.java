package com.izhuantou.damain;

/**
 * 修改密码实体类
 *
 * @author fucheng
 * @date 2018-01-25
 */
public class Password extends BasePojo {
    /** 
    *  
    */
    private static final long serialVersionUID = 1L;

    private String oid;
    private String oldPassWard;
    private String newPassward;

    public String getOid() {
	return oid;
    }

    public void setOid(String oid) {
	this.oid = oid;
    }

    public String getOldPassWard() {
	return oldPassWard;
    }

    public void setOldPassWard(String oldPassWard) {
	this.oldPassWard = oldPassWard;
    }

    public String getNewPassward() {
	return newPassward;
    }

    public void setNewPassward(String newPassward) {
	this.newPassward = newPassward;
    }

    @Override
    public String toString() {
	return "UpdatePassword [oid=" + oid + ", oldPassWard=" + oldPassWard + ", newPassward=" + newPassward + "]";
    }

}
