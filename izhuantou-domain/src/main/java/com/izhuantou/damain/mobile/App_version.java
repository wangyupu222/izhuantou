package com.izhuantou.damain.mobile;

/**
 * APP版本实体
 * 
 * @author yangbosen
 *
 */
public class App_version {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String version;
    private String date;
    private String status;
    private String hotUpdStatus;

    private String a_version;
    private String a_force;
    private String a_patch;
    private String i_force;

    public String getA_version() {
	return a_version;
    }

    public void setA_version(String a_version) {
	this.a_version = a_version;
    }

    public String getA_force() {
	return a_force;
    }

    public void setA_force(String a_force) {
	this.a_force = a_force;
    }

    public String getA_patch() {
	return a_patch;
    }

    public void setA_patch(String a_patch) {
	this.a_patch = a_patch;
    }

    public String getI_force() {
	return i_force;
    }

    public void setI_force(String i_force) {
	this.i_force = i_force;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getVersion() {
	return version;
    }

    public void setVersion(String version) {
	this.version = version;
    }

    public String getDate() {
	return date;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getHotUpdStatus() {
	return hotUpdStatus;
    }

    public void setHotUpdStatus(String hotUpdStatus) {
	this.hotUpdStatus = hotUpdStatus;
    }

    public static long getSerialversionuid() {
	return serialVersionUID;
    }

    @Override
    public String toString() {
	return "App_version [name=" + name + ", version=" + version + ", date=" + date + ", status=" + status
		+ ", hotUpdStatus=" + hotUpdStatus + ", a_version=" + a_version + ", a_force=" + a_force + ", a_patch="
		+ a_patch + ", i_force=" + i_force + "]";
    }

}
