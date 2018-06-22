package com.izhuantou.damain.vo.member;

import java.io.Serializable;

/**
 * 客户查询条件
 * 
 * @author sweet
 * @date 2018年6月11日
 *
 */
public class CustomerQueryConditionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4967410086998609291L;

	/**
	 * 开始时间
	 */
	private String startDate;
	/**
	 * 结束时间
	 */
	private String endDate;
	/**
	 * 关键字
	 * 
	 */
	private String keyWord;
	/**
	 * 关键字类型
	 * 
	 */
	private String keyWordType;
	/**
	 * 时间排序（升序或降序）
	 * 
	 */
	private String dateSort;
	/**
	 * 身份证(1：实名、2：未实名)
	 * 
	 */
	private String idCardStatus;
	/**
	 * 银行卡(1：绑定，2：未绑定)
	 */
	private String bankNumberStatus;
	/**
	 * 拼接OID的字符串
	 * 
	 */
	private String ids;
	/**
	 * excel导出路径
	 * 
	 */
	private String path;
	/**
	 * 客服反馈状态（0：未回复，1：解决中，2：已回复，3：未解决，4：已解决）
	 * 
	 */
	private String status;

	/**
	 * 显示类型（0：客服登记，1：客户反馈）
	 */
	private String type;

	/**
	 * 最近反馈人
	 */
	private String serviceName;
	/**
	 * 回复内容
	 */
	private String feedbackContent;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFeedbackContent() {
		return feedbackContent;
	}

	public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIdCardStatus() {
		return idCardStatus;
	}

	public void setIdCardStatus(String idCardStatus) {
		this.idCardStatus = idCardStatus;
	}

	public String getBankNumberStatus() {
		return bankNumberStatus;
	}

	public void setBankNumberStatus(String bankNumberStatus) {
		this.bankNumberStatus = bankNumberStatus;
	}

	public String getDateSort() {
		return dateSort;
	}

	public void setDateSort(String dateSort) {
		this.dateSort = dateSort;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getKeyWordType() {
		return keyWordType;
	}

	public void setKeyWordType(String keyWordType) {
		this.keyWordType = keyWordType;
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

}
