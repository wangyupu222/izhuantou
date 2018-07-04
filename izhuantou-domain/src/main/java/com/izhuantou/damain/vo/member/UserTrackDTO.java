package com.izhuantou.damain.vo.member;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 用户详情（行为轨迹）
 * 
 * @author sweet
 * @date 2018年6月13日
 *
 */
public class UserTrackDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2582002395065127351L;
	/**
	 * 操作记录
	 */
	private Map<String, List<String>> operatingRecord;

	public Map<String, List<String>> getOperatingRecord() {
		return operatingRecord;
	}

	public void setOperatingRecord(Map<String, List<String>> operatingRecord) {
		this.operatingRecord = operatingRecord;
	}

}
