package com.izhuantou.admin.controller.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.admin.controller.UserController;
import com.izhuantou.admin.ims.annotation.SystemControllerLog;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.ExcelUtil;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.vo.member.CustomerExcelDTO;
import com.izhuantou.damain.vo.member.CustomerFeedbackDTO;
import com.izhuantou.damain.vo.member.CustomerFourNumberDTO;
import com.izhuantou.damain.vo.member.CustomerListDTO;
import com.izhuantou.damain.vo.member.CustomerManagerDTO;
import com.izhuantou.damain.vo.member.CustomerQueryConditionDTO;
import com.izhuantou.damain.vo.member.FeedBackDetailsDTO;
import com.izhuantou.damain.vo.member.TrackDTO;
import com.izhuantou.damain.vo.member.TrackQueryConditionDTO;
import com.izhuantou.damain.vo.member.UserDetailThreeNumberDTO;
import com.izhuantou.damain.vo.member.UserTrackDTO;
import com.izhuantou.service.api.member.CustomerService;

/**
 * 会员管理
 * 
 * @author sweet
 * @date 2018年6月11日
 *
 */
@Controller
@RequestMapping("customer")
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private CustomerService customerService;

	/**
	 * 客户列表四个数展示数据
	 * 
	 * @return
	 */
	@SystemControllerLog(description = "客户列表四个数展示数据")
	@RequestMapping(value = "/fournumber", method = RequestMethod.GET)
	@ResponseBody
	public OpResult fourNumber() {
		CustomerFourNumberDTO list = customerService.fourNumber();
		return OpResult.getSuccessResult(list);
	}

	/**
	 * 客户列表分页显示
	 */
	@SystemControllerLog(description = "客户列表分页显示")
	@RequestMapping(value = "/listcustomer", method = RequestMethod.GET)
	@ResponseBody
	public OpResult listCustomer(@RequestParam(value = "page", defaultValue = "1") Integer currentPage) {
		Pagination<CustomerListDTO> pagination = customerService.listCustomer(currentPage);
		return OpResult.getSuccessResult(pagination);
	}

	/**
	 * 客户列表条件查询
	 */
	@SystemControllerLog(description = "客户列表条件查询")
	@RequestMapping(value = "/conditionlistcustomer", method = RequestMethod.POST)
	@ResponseBody
	public OpResult conditionListCustomer(@RequestParam(value = "page", defaultValue = "1") Integer currentPage,
			CustomerQueryConditionDTO cqcDTO) {
		Pagination<CustomerListDTO> pagination = customerService.conditionListCustomer(currentPage, cqcDTO);
		return OpResult.getSuccessResult(pagination);
	}

	/**
	 * 客户列表的数据导出
	 */
	@SystemControllerLog(description = "客户列表的数据导出")
	@RequestMapping(value = "/exportcustomerlist", method = RequestMethod.POST)
	@ResponseBody
	public OpResult exportCustomerList(CustomerQueryConditionDTO cqcDTO) {
		Long startTime = new Date().getTime();
		List<CustomerListDTO> list = customerService.exportCustomerList(cqcDTO);
		try {
			String fileNamePath = cqcDTO.getPath() + "/客户列表" + DateUtils.getDateFormatter().replace(":", ".") + ".xls";
			String[] titles = { "ID", "手机号", "昵称", "姓名", "性别", "生日", "年龄", "客户类型", "身份证", "银行卡", "邮箱", "风险测评", "邀请人" };
			String[] fieldNames = { "memberAccount", "mobile", "nickName", "realName", "sex", "birthday", "age",
					"userType", "idCard", "bankNumber", "email", "riskAppraisal", "inviter" };
			ExcelUtil.exportExcel(fileNamePath, "客户列表", list, titles, fieldNames);
			Long totalTime = new Date().getTime() - startTime;
			logger.info("客户列表数据导出用时{}",totalTime);
			return OpResult.getSuccessResult(null);
		} catch (Exception e) {
			e.printStackTrace();
			return OpResult.getFailedResult("客户列表数据导出失败");
		}

	}
	/**
	 * 客户经理变更-->获取客户经理各级分类选项列表
	 * @Param customerManagerDTO 客户经理DTO
	 */
	@SystemControllerLog(description = "客户经理变更")
	@RequestMapping(value="/customerManagerChange", method = RequestMethod.POST)
	@ResponseBody
	public OpResult customerManagerChange(CustomerManagerDTO customerManagerDTO){
		if(StringUtil.isNotEmpty(customerManagerDTO.getType())){
			if(!customerManagerDTO.getType().equals("5")){
				List<String> strs = customerService.getCustomerManagerGroup(customerManagerDTO);
				if(strs!=null){
					return OpResult.getSuccessResult(strs);
				}
			}else{
				List<Map<String,Object>> managerList =  customerService.getCustomerManagerList(customerManagerDTO);
				if(managerList!=null){
					return OpResult.getSuccessResult(managerList);
				}
			}
		}
		return OpResult.getFailedResult("查询失败");
	}
	
	/**
	 * 客户详情(行为轨迹)三个数据统计
	 */
	@SystemControllerLog(description = "客户详情(行为轨迹)三个数据统计")
	@RequestMapping(value = "/usertrackthreenumber",method = RequestMethod.POST)
	@ResponseBody
	public OpResult userTrackThreeNumber(String oid){
		UserDetailThreeNumberDTO userDetailThreeNumberDTO =customerService.userTrackThreeNumber(oid);
		if (userDetailThreeNumberDTO != null){
			return OpResult.getSuccessResult(userDetailThreeNumberDTO);
		}else{
			return OpResult.getFailedResult("统计失败");
		}
	}
	
	/**
	 * 客户详情（行为轨迹）
	 */
	@SystemControllerLog(description = "客户详情")
	@RequestMapping(value = "/usertrack", method = RequestMethod.POST)
	@ResponseBody
	public OpResult userTrack( CustomerQueryConditionDTO cqcDTO) {
		List<Map<String,Object>>  userTrackList= customerService.userTrack(cqcDTO);
		return OpResult.getSuccessResult(userTrackList);
	}

	/**
	 * 客服登记分页显示
	 */
	@SystemControllerLog(description = "客服登记分页显示")
	@RequestMapping(value = "/listserviceregister", method = RequestMethod.GET)
	@ResponseBody
	public OpResult listServiceRegister(@RequestParam(value = "page", defaultValue = "1") Integer currentPage) {
		String type = "0";// 显示类型（0：客服登记，1：客户反馈）
		Pagination<CustomerFeedbackDTO> pagination = customerService.listCustomerFeedback(currentPage, type);
		return OpResult.getSuccessResult(pagination);
	}

	/**
	 * 客服登记条件查询
	 */
	@SystemControllerLog(description = "客服登记条件查询")
	@RequestMapping(value = "/conditionlistserviceregister", method = RequestMethod.POST)
	@ResponseBody
	public OpResult conditionListServiceRegister(@RequestParam(value = "page", defaultValue = "1") Integer currentPage,
			CustomerQueryConditionDTO cqcDTO) {
		cqcDTO.setType("0");// 显示类型（0：客服登记，1：客户反馈）
		Pagination<CustomerFeedbackDTO> pagination = customerService.conditionListCustomerFeedback(currentPage, cqcDTO);
		return OpResult.getSuccessResult(pagination);
	}

	/**
	 * 客户反馈分页显示
	 */
	@SystemControllerLog(description = "客户反馈分页显示")
	@RequestMapping(value = "/listcustomerfeedback", method = RequestMethod.GET)
	@ResponseBody
	public OpResult listCustomerFeedback(@RequestParam(value = "page", defaultValue = "1") Integer currentPage) {
		String type = "1";// 显示类型（0：客服登记，1：客户反馈）
		Pagination<CustomerFeedbackDTO> pagination = customerService.listCustomerFeedback(currentPage, type);
		return OpResult.getSuccessResult(pagination);
	}

	/**
	 * 客户反馈条件查询
	 */
	@SystemControllerLog(description = "客户反馈条件查询")
	@RequestMapping(value = "/conditionlistcustomerfeedback", method = RequestMethod.POST)
	@ResponseBody
	public OpResult conditionListCustomerFeedback(@RequestParam(value = "page", defaultValue = "1") Integer currentPage,
			CustomerQueryConditionDTO cqcDTO) {
		cqcDTO.setType("1");// 显示类型（0：客服登记，1：客户反馈）
		Pagination<CustomerFeedbackDTO> pagination = customerService.conditionListCustomerFeedback(currentPage, cqcDTO);
		return OpResult.getSuccessResult(pagination);
	}

	/**
	 * 客户登记添加
	 */
	@SystemControllerLog(description = "客户登记添加")
	@RequestMapping(value = "/savecustomerfeedback", method = RequestMethod.POST)
	@ResponseBody
	public OpResult savecustomerfeedback(@RequestParam(value = "mobile") String mobile,
			@RequestParam(value = "name") String realName, @RequestParam(value = "content") String content) {
		int row = customerService.savecustomerfeedback(mobile, realName, content);
		if (row == 1) {
			return OpResult.getSuccessResult(null);
		} else {
			return OpResult.getFailedResult("添加失败");
		}
	}

	/**
	 * 根据手机号获取客户姓名（ajax）
	 */
	@SystemControllerLog(description = "根据手机号获取客户姓名")
	@RequestMapping(value = "/getnamebymobile", method = RequestMethod.POST)
	@ResponseBody
	public OpResult getNameByMobile(String mobile) {
		Map<String, String> map = customerService.getNameByMobile(mobile);
		return OpResult.getSuccessResult(map);
	}

	/**
	 * 客服登记详情
	 */
	@SystemControllerLog(description = "客服登记详情")
	@RequestMapping(value = "/servicedetails", method = RequestMethod.POST)
	@ResponseBody
	public OpResult serviceDetails(@RequestParam(value = "oid") String oid, @RequestParam(value = "name") String name) {
		List<FeedBackDetailsDTO> list = customerService.serviceDetails(oid, name);
		return OpResult.getSuccessResult(list);
	}

	/**
	 * 反馈详情信息提交
	 */
	@SystemControllerLog(description = "反馈详情信息提交")
	@RequestMapping(value = "/submitfeedback", method = RequestMethod.POST)
	@ResponseBody
	public OpResult submitfeedback(@RequestParam(value = "oid") String oid, CustomerQueryConditionDTO cqcDTO) {
		int row = customerService.submitfeedback(oid, cqcDTO);
		if (row == 1) {
			return OpResult.getSuccessResult(null);
		} else {
			return OpResult.getFailedResult("提交失败");
		}
	}

	/**
	 * 点击详情修改状态为解决中
	 */
	@SystemControllerLog(description = "点击详情修改状态为解决中")
	@RequestMapping(value = "/updatestatus", method = RequestMethod.POST)
	@ResponseBody
	public OpResult updateStatus(String oid) {
		int row = customerService.updateStatus(oid);
		if (row == 1) {
			return OpResult.getSuccessResult(null);
		} else {
			return OpResult.getFailedResult("更改失败");
		}
	}

	/**
	 * 客户反馈详情
	 */
	@SystemControllerLog(description = "客户反馈详情")
	@RequestMapping(value = "/feedbackdetails", method = RequestMethod.POST)
	@ResponseBody
	public OpResult feedBackDetails(@RequestParam(value = "oid") String oid) {
		List<FeedBackDetailsDTO> list = customerService.feedBackDetails(oid);
		return OpResult.getSuccessResult(list);
	}

	/**
	 * 客服登记数据导出
	 */
	@SystemControllerLog(description = "客服登记数据导出")
	@RequestMapping(value = "/exportservicerecord", method = RequestMethod.POST)
	@ResponseBody
	public OpResult exportServiceRecord(CustomerQueryConditionDTO cqcDTO) {
		Long startTime = new Date().getTime();
		List<CustomerExcelDTO> list = customerService.exportServiceRecord(cqcDTO);
		// 导出excel
		try {
			String fileNamePath = cqcDTO.getPath() + "/客服登记" + DateUtils.getDateFormatter().replace(":", ".") + ".xls";
			String[] titles = { "手机号", "姓名", "反馈内容", "最近反馈", "反馈时间", "状态", "详情" };
			String[] fieldNames = { "phone", "name", "feedbackContent", "serviceName", "feedbackDate", "status",
					"chatContent" };
			ExcelUtil.exportExcel(fileNamePath, "客服登记", list, titles, fieldNames);
			Long totalTime = new Date().getTime() - startTime;
			logger.info("客服登记数据导出用时{}",totalTime);
			return OpResult.getSuccessResult(null);
		} catch (Exception e) {
			e.printStackTrace();
			return OpResult.getFailedResult("客服登记数据导出失败");
		}
	}

	/**
	 * 客服反馈数据导出
	 */
	@SystemControllerLog(description = "客服反馈数据导出")
	@RequestMapping(value = "/exportfeedback", method = RequestMethod.POST)
	@ResponseBody
	public OpResult exportFeedBack(CustomerQueryConditionDTO cqcDTO) {
		Long startTime = new Date().getTime();
		List<CustomerExcelDTO> list = customerService.exportFeedBack(cqcDTO);
		// 导出excel
		try {
			String fileNamePath = cqcDTO.getPath() + "/客服登记" + DateUtils.getDateFormatter().replace(":", ".") + ".xls";
			String[] titles = { "手机号", "姓名", "反馈内容", "最近反馈", "反馈时间", "状态", "详情" };
			String[] fieldNames = { "phone", "name", "feedbackContent", "serviceName", "feedbackDate", "status",
					"chatContent" };
			ExcelUtil.exportExcel(fileNamePath, "客户反馈", list, titles, fieldNames);
			Long totalTime = new Date().getTime() - startTime;
			logger.info("客户反馈数据导出用时{}",totalTime);
			return OpResult.getSuccessResult(null);
		} catch (Exception e) {
			e.printStackTrace();
			return OpResult.getFailedResult("客服反馈数据导出失败");
		}
	}

	/**
	 * 客户行为轨迹分页显示
	 */
	@SystemControllerLog(description = "客户行为轨迹分页显示")
	@RequestMapping(value = "/track", method = RequestMethod.GET)
	@ResponseBody
	public OpResult track(@RequestParam(value = "page", defaultValue = "1") Integer currentPage) {
		Pagination<TrackDTO> pagination = customerService.track(currentPage);
		return OpResult.getSuccessResult(pagination);
	}

	/**
	 * 客户行为轨迹条件查询
	 */
	@SystemControllerLog(description = "客户行为轨迹条件查询")
	@RequestMapping(value = "/conditiontrack", method = RequestMethod.POST)
	@ResponseBody
	public OpResult conditionTrack(@RequestParam(value = "page", defaultValue = "1") Integer currentPage,
			TrackQueryConditionDTO trackQueryConditionDTO) {
		Pagination<TrackDTO> pagination = customerService.conditionTrack(trackQueryConditionDTO, currentPage);
		return OpResult.getSuccessResult(pagination);
	}

}
