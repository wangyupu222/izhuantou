package com.izhuantou.service.api.member;

import java.util.List;
import java.util.Map;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.vo.member.CustomerExcelDTO;
import com.izhuantou.damain.vo.member.CustomerFeedbackDTO;
import com.izhuantou.damain.vo.member.CustomerFourNumberDTO;
import com.izhuantou.damain.vo.member.CustomerListDTO;
import com.izhuantou.damain.vo.member.CustomerQueryConditionDTO;
import com.izhuantou.damain.vo.member.FeedBackDetailsDTO;
import com.izhuantou.damain.vo.member.TrackDTO;
import com.izhuantou.damain.vo.member.TrackQueryConditionDTO;
import com.izhuantou.damain.vo.member.UserDetailThreeNumberDTO;
import com.izhuantou.damain.vo.member.UserTrackDTO;

public interface CustomerService {
	/**
	 * 客户列表四个数展示
	 * 
	 * @return
	 */
	CustomerFourNumberDTO fourNumber();

	/**
	 * 客户列表分页显示
	 * 
	 * @param currentPage
	 * @param cqcDTO
	 * @return
	 */
	Pagination<CustomerListDTO> listCustomer(Integer currentPage);

	/**
	 * 客户列表条件查询
	 * 
	 * @param currentPage
	 * @param cqcDTO
	 * @return
	 */
	Pagination<CustomerListDTO> conditionListCustomer(Integer currentPage, CustomerQueryConditionDTO cqcDTO);

	/**
	 * 客户列表数据导出
	 * 
	 * @param cqcDTO
	 * @return
	 */
	List<CustomerListDTO> exportCustomerList(CustomerQueryConditionDTO cqcDTO);

	/**
	 * 
	 */
	UserTrackDTO userTrack(String oid, CustomerQueryConditionDTO cqcDTO);

	/**
	 * 客服登记分页显示
	 * 
	 * @param currentPage
	 * @return
	 */
	Pagination<CustomerFeedbackDTO> listCustomerFeedback(Integer currentPage,String type);

	/**
	 * 客服登记条件查询
	 * 
	 * @param currentPage
	 * @param cqcDTO
	 * @return
	 */
	Pagination<CustomerFeedbackDTO> conditionListCustomerFeedback(Integer currentPage, CustomerQueryConditionDTO cqcDTO);
	
	/**
	 * 客户登记添加
	 * 
	 * @param mobile
	 * @param realName
	 * @param content
	 * @return
	 */
	int savecustomerfeedback(String mobile, String realName, String content);

	/**
	 * 根据手机号获取客户姓名
	 */
	Map<String, String> getNameByMobile(String mobile);

	/**
	 * 客服登记详情
	 * 
	 * @param oid
	 * @return
	 */
	List<FeedBackDetailsDTO> serviceDetails(String oid,String name);

	/**
	 * 反馈详情信息提交
	 * 
	 * @param feedBackOID
	 * @param content
	 * @param state
	 * @return
	 */
	int submitfeedback(String oid, CustomerQueryConditionDTO cqcDTO);

	/**
	 * 客户反馈详情
	 * 
	 * @param oid
	 * @return
	 */
	List<FeedBackDetailsDTO> feedBackDetails(String oid);
	
	/**
	 * 客服登记数据导出
	 * @param oids
	 * @param cqcDTO
	 * @return
	 */
	List<CustomerExcelDTO> exportServiceRecord(CustomerQueryConditionDTO cqcDTO);
	
	/**
	 * 客服反馈数据导出
	 * @param cqcDTO
	 * @return
	 */
	List<CustomerExcelDTO> exportFeedBack(CustomerQueryConditionDTO cqcDTO);
	
	/**
	 * 点击详情修改状态为解决中
	 * @param oid
	 * @param status
	 * @return
	 */
	int updateStatus(String oid);
	
	/**
	 * 客户行为轨迹分页显示
	 * @param currentPage
	 * @return
	 */
	Pagination<TrackDTO> track(Integer currentPage);
	
	/**
	 * 客户行为轨迹条件查询
	 * @param trackQueryConditionDTO
	 * @param currentPage
	 * @return
	 */
	Pagination<TrackDTO> conditionTrack(TrackQueryConditionDTO trackQueryConditionDTO, Integer currentPage);
	
	/**
	 * 客户详情(行为轨迹)三个数据统计
	 * @param oid
	 * @return
	 */
	UserDetailThreeNumberDTO userTrackThreeNumber(String oid);

	

	

}
