package com.izhuantou.damain.vo.member;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 行为轨迹查询DTO
 * 
 * @author sweet
 * @date 2018年6月21日
 *
 */
public class TrackQueryConditionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7224884708641026665L;

	/**
	 * 开始时间
	 */
	private String startDate;
	/**
	 * 结束时间
	 */
	private String endDate;
	/**
	 * 开始时间（时间戳）
	 */
	private Long startDateTime;
	/**
	 * 结束时间（时间戳）
	 */
	private Long endDateTime;
	/**
	 * 关键字
	 */
	private String keyWord;
	/**
	 * 日志类型(多个类型拼接成字符串以','分割)
	 */
	private String type;
	/**
	 * 日志类型（数组形式）
	 */
	private String[] typeArray;

	public String[] getTypeArray() {
		return typeArray;
	}

	public void setTypeArray(String[] typeArray) {
		this.typeArray = typeArray;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Long startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Long getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Long endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
