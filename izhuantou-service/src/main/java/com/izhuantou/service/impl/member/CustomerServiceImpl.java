package com.izhuantou.service.impl.member;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.tool.ToolMath;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.manager.CustomerFeedback;
import com.izhuantou.damain.manager.DetailFeedback;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.system.PortalLog;
import com.izhuantou.damain.user.MemberBlackList;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.user.MemberMemberAgreement;
import com.izhuantou.damain.user.MemberScore;
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
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.dao.manager.CustomerFeedbackMapper;
import com.izhuantou.dao.manager.CustomerManagerMapper;
import com.izhuantou.dao.manager.DetailFeedbackMapper;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayCustomerBusinessMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayCustomerTyjMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.system.PortalLogMapper;
import com.izhuantou.dao.user.MemberBlackListMapper;
import com.izhuantou.dao.user.MemberMemberAgreementMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.user.MemberScoreMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.service.api.member.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private MemberMemberMapper memberMemberMapper;
	@Autowired
	private PayCustomerMapper payCustomerMapper;
	@Autowired
	private PayDebitCreditMapper payDebitCreditMapper;
	@Autowired
	private MemberScoreMapper memberScoreMapper;
	@Autowired
	private PayCustomerTyjMapper payCustomerTyjMapper;
	@Autowired
	private WebP2pNoviceBiddingRuningMapper webP2pNoviceBiddingRuningMapper;
	@Autowired
	private PayCashPoolMapper payCashPoolMapper;
	@Autowired
	private PayCustomerBusinessMapper payCustomerBusinessMapper;
	@Autowired
	private MemberBlackListMapper memberBlackListMapper;
	@Autowired
	private WebP2pPackageBiddingMainRuningMapper webP2pPackageBiddingMainRuningMapper;
	@Autowired
	private CustomerFeedbackMapper customerFeedbackMapper;
	@Autowired
	private DetailFeedbackMapper detailFeedbackMapper;
	@Autowired
	private PortalLogMapper portalLogMapper;
	@Autowired
	private CustomerManagerMapper customerManagerMapper;
	@Autowired
	private MemberMemberAgreementMapper memberAgreementDao;
	/**
	 * 客户列表四个数展示
	 */
	@Override
	public CustomerFourNumberDTO fourNumber() {
		CustomerFourNumberDTO cfnDTO = new CustomerFourNumberDTO();
		// 注册客户数
		int registerNumber = memberMemberMapper.selectCount(null);
		cfnDTO.setRegisterNumber(registerNumber);
		// 实名客户数
		int realNameNumber = payCustomerMapper.selectCount(null);
		cfnDTO.setRealNameNumber(realNameNumber);
		// 出借客户数
		int lendNumber = payDebitCreditMapper.countOutMember();
		cfnDTO.setLendNumber(lendNumber);
		// 风险测评客户数
		int riskNumber = memberScoreMapper.countTotal();
		cfnDTO.setRiskNumber(riskNumber);
		return cfnDTO;
	}

	/**
	 * 客户列表分页显示
	 */
	@Override
	public Pagination<CustomerListDTO> listCustomer(Integer currentPage) {
		Pagination<CustomerListDTO> pagination = new Pagination<CustomerListDTO>();
		// 当前页
		pagination.setCurrentPage(currentPage);
		// 每页显示数据条数
		pagination.setPageSize(10);
		// 总数据数
		int totalNumber = memberMemberMapper.selectCount(null);
		pagination.setTotalNumber(totalNumber);
		// 总页数
		if (totalNumber % pagination.getPageSize() == 0) {
			pagination.setTotalPage(totalNumber / pagination.getPageSize());
		} else {
			pagination.setTotalPage((totalNumber / pagination.getPageSize()) + 1);
		}
		// 每页显示的数据
		Integer startIndex = (currentPage-1)*pagination.getPageSize();
		List<CustomerListDTO> customerList = memberMemberMapper.listCustomer(startIndex,
				pagination.getPageSize());
		if (customerList != null && customerList.size() > 0) {
			for (CustomerListDTO clDTO : customerList) {
				String memberOID = clDTO.getOID();
				// 获取用户银行卡号
				PayCustomer pc = payCustomerMapper.findByMemberOID(memberOID);
				if (pc != null) {
					clDTO.setBankNumber(pc.getBankNumber());
				}
				// 根据身份证号获取生日、年龄、性别
				String idCard = clDTO.getIdCard();
				if (StringUtil.isNotEmpty(idCard)) {
					String birthday = idCard.substring(6, 10) + "." + idCard.substring(10, 12) + "."
							+ idCard.substring(12, 14);
					clDTO.setBirthday(birthday);
					Integer sexNumber = Integer.valueOf(idCard.substring(16, 17));
					if ((sexNumber % 2) == 0) {
						clDTO.setSex("女");
					} else {
						clDTO.setSex("男");
					}
					Date birthdayDate = DateUtils.getJustDate(birthday.replace(".", "-"));
					Calendar cal = Calendar.getInstance();
					cal.setTime(birthdayDate);
					int yearBirth = cal.get(Calendar.YEAR);
					cal.setTime(new Date());
					int yearNow = cal.get(Calendar.YEAR);
					int age = yearNow - yearBirth;
					clDTO.setAge(Integer.toString(age));
				}
				// 风险测评
				MemberScore memberScore = memberScoreMapper.selectByMemberOID(memberOID);
				if (memberScore != null) {
					clDTO.setRiskAppraisal(memberScore.getScore());
				} else {
					clDTO.setRiskAppraisal(null);
				}
				// 邀请人
				String yqrOID = clDTO.getYewuOID();
				if (StringUtil.isNotEmpty(yqrOID)) {
					MemberMember yqrMessage = memberMemberMapper.selectByPrimaryKey(yqrOID);
					if (StringUtil.isNotEmpty(yqrMessage.getRealName())) {
						String yqr = yqrMessage.getRealName() + "(" + yqrMessage.getName() + ")";
						clDTO.setInviter(yqr);
						;
					} else {
						String yqr = "未实名(" + yqrMessage.getName() + ")";
						clDTO.setInviter(yqr);
					}
				} else {
					clDTO.setInviter("无邀请人");
				}
				// 判断用户类型
				String userType = judgeUserType(memberOID, pc);
				clDTO.setUserType(userType);
			}
		}
		pagination.setData(customerList);
		return pagination;
	}

	/**
	 * 客户列表条件查询
	 */
	@Override
	public Pagination<CustomerListDTO> conditionListCustomer(Integer currentPage, CustomerQueryConditionDTO cqcDTO) {
		// 判断有无时间排序方式
		if (StringUtil.isEmpty(cqcDTO.getDateSort())) {
			cqcDTO.setDateSort("desc");
		}
		// 关键词去空格
		if (StringUtil.isNotEmpty(cqcDTO.getKeyWord())) {
			cqcDTO.setKeyWord(cqcDTO.getKeyWord().trim());
		}
		// 当前页
		Pagination<CustomerListDTO> pagination = new Pagination<CustomerListDTO>();
		pagination.setCurrentPage(currentPage);
		// 每页显示数据数
		pagination.setPageSize(10);
		// 总数据数
		int totalNumber = memberMemberMapper.conditionCount(cqcDTO);
		pagination.setTotalNumber(totalNumber);
		// 总页数
		if (totalNumber % pagination.getPageSize() == 0) {
			pagination.setTotalPage(totalNumber / pagination.getPageSize());
		} else {
			pagination.setTotalPage((totalNumber / pagination.getPageSize()) + 1);
		}
		// 每页显示数据
		Integer startIndex = (currentPage-1)*pagination.getPageSize();
		List<CustomerListDTO> customerList = memberMemberMapper.conditionList(startIndex,
				pagination.getPageSize(), cqcDTO);
		if (customerList != null && customerList.size() > 0) {
			for (CustomerListDTO clDTO : customerList) {
				String memberOID = clDTO.getOID();
				// 根据身份证号获取生日、年龄、性别
				String idCard = clDTO.getIdCard();
				if (StringUtil.isNotEmpty(idCard)) {
					String birthday = idCard.substring(6, 10) + "." + idCard.substring(10, 12) + "."
							+ idCard.substring(12, 14);
					clDTO.setBirthday(birthday);
					Integer sexNumber = Integer.valueOf(idCard.substring(16, 17));
					if ((sexNumber % 2) == 0) {
						clDTO.setSex("女");
					} else {
						clDTO.setSex("男");
					}
					Date birthdayDate = DateUtils.getJustDate(birthday.replace(".", "-"));
					Calendar cal = Calendar.getInstance();
					cal.setTime(birthdayDate);
					int yearBirth = cal.get(Calendar.YEAR);
					cal.setTime(new Date());
					int yearNow = cal.get(Calendar.YEAR);
					int age = yearNow - yearBirth;
					clDTO.setAge(Integer.toString(age));
				}
				// 风险测评
				MemberScore memberScore = memberScoreMapper.selectByMemberOID(memberOID);
				if (memberScore != null) {
					clDTO.setRiskAppraisal(memberScore.getScore());
				} else {
					clDTO.setRiskAppraisal(null);
				}
				// 邀请人
				String yqrOID = clDTO.getYewuOID();
				if (StringUtil.isNotEmpty(yqrOID)) {
					MemberMember yqrMessage = memberMemberMapper.selectByPrimaryKey(yqrOID);
					if (StringUtil.isNotEmpty(yqrMessage.getRealName())) {
						String yqr = yqrMessage.getRealName() + "(" + yqrMessage.getName() + ")";
						clDTO.setInviter(yqr);
					} else {
						String yqr = "未实名(" + yqrMessage.getName() + ")";
						clDTO.setInviter(yqr);
					}
				} else {
					clDTO.setInviter("无邀请人");
				}
				// 判断客户类型
				PayCustomer pc = payCustomerMapper.findByMemberOID(memberOID);
				String userType = judgeUserType(memberOID, pc);
				clDTO.setUserType(userType);
			}
		}
		pagination.setData(customerList);
		return pagination;
	}

	/**
	 * 客户列表数据导出
	 */
	@Override
	public List<CustomerListDTO> exportCustomerList(CustomerQueryConditionDTO cqcDTO) {
		List<CustomerListDTO> customerList = new ArrayList<>();
		// 判断是否是选中导出
		if (StringUtil.isNotEmpty(cqcDTO.getIds())) {
			// 根据oid获取数据
			String[] oids = cqcDTO.getIds().split(",");
			// 根据oid获取数据集
			customerList = memberMemberMapper.selectByOID(oids);
		} else {
			// 根据条件获取数据
			customerList = memberMemberMapper.listByCondition(cqcDTO);
		}
		if (customerList != null && customerList.size() > 0) {
			for (CustomerListDTO clDTO : customerList) {
				// 如果为null 转换为 空格
				if (StringUtil.isEmpty(clDTO.getMobile())) {
					clDTO.setMobile(" ");
				}
				if (StringUtil.isEmpty(clDTO.getNickName())) {
					clDTO.setNickName(" ");
				}
				if (StringUtil.isEmpty(clDTO.getRealName())) {
					clDTO.setRealName(" ");
				}
				if (StringUtil.isEmpty(clDTO.getEmail())) {
					clDTO.setEmail(" ");
				}
				String memberOID = clDTO.getOID();
				// 获取用户银行卡号
				PayCustomer pc = payCustomerMapper.findByMemberOID(memberOID);
				if (pc != null) {
					clDTO.setBankNumber(pc.getBankNumber());
				}
				// 根据身份证号获取生日、年龄、性别
				String idCard = clDTO.getIdCard();
				if (StringUtil.isNotEmpty(idCard)) {
					String birthday = idCard.substring(6, 10) + "." + idCard.substring(10, 12) + "."
							+ idCard.substring(12, 14);
					clDTO.setBirthday(birthday);
					Integer sexNumber = Integer.valueOf(idCard.substring(16, 17));
					if ((sexNumber % 2) == 0) {
						clDTO.setSex("女");
					} else {
						clDTO.setSex("男");
					}
					Date birthdayDate = DateUtils.getJustDate(birthday.replace(".", "-"));
					Calendar cal = Calendar.getInstance();
					cal.setTime(birthdayDate);
					int yearBirth = cal.get(Calendar.YEAR);
					cal.setTime(new Date());
					int yearNow = cal.get(Calendar.YEAR);
					int age = yearNow - yearBirth;
					clDTO.setAge(Integer.toString(age));
				} else {
					clDTO.setIdCard(" ");
					clDTO.setBirthday(" ");
					clDTO.setSex(" ");
					clDTO.setAge(" ");
					clDTO.setBankNumber(" ");
				}
				// 风险测评
				MemberScore memberScore = memberScoreMapper.selectByMemberOID(memberOID);
				if (memberScore != null) {
					clDTO.setRiskAppraisal(memberScore.getScore());
				} else {
					clDTO.setRiskAppraisal(" ");
				}
				// 邀请人
				String yqrOID = clDTO.getYewuOID();
				if (StringUtil.isNotEmpty(yqrOID)) {
					MemberMember yqrMessage = memberMemberMapper.selectByPrimaryKey(yqrOID);
					if (StringUtil.isNotEmpty(yqrMessage.getRealName())) {
						String yqr = yqrMessage.getRealName() + "(" + yqrMessage.getName() + ")";
						clDTO.setInviter(yqr);
					} else {
						String yqr = "未实名(" + yqrMessage.getName() + ")";
						clDTO.setInviter(yqr);
					}
				} else {
					clDTO.setInviter("无邀请人");
				}
				// 判断客户类型
				String userType = judgeUserType(memberOID, pc);
				clDTO.setUserType(userType);
			}
		}
		return customerList;
	}
	
	/**
	 * 客户经理变更-->获取客户经理各级分类选项列表
	 * @param customerManagerDTO 客户经理DTO
	 */
	@Override
	public List<String> getCustomerManagerGroup(CustomerManagerDTO customerManagerDTO){
		String type = customerManagerDTO.getType();
		List<String> strs = null;
		try{
			if(type.equals("1")){				 													//查询第一列大区类型
				customerManagerDTO.setType("twoDept");
				customerManagerDTO.setTwoDept("");
				customerManagerDTO.setThrDept("");
				customerManagerDTO.setFourDept("");
				customerManagerDTO.setFiveDept("");
				strs = customerManagerMapper.getCustomerManagerGroup(customerManagerDTO);
			}else if(type.equals("2") && StringUtil.isNotEmpty(customerManagerDTO.getTwoDept())){  	//查询第二列分公司
				customerManagerDTO.setType("thrDept");
				customerManagerDTO.setThrDept("");
				customerManagerDTO.setFourDept("");
				customerManagerDTO.setFiveDept("");
				strs = customerManagerMapper.getCustomerManagerGroup(customerManagerDTO);
			}else if(type.equals("3") && StringUtil.isNotEmpty(customerManagerDTO.getTwoDept())  	//查询第三列营业部
					&& StringUtil.isNotEmpty(customerManagerDTO.getThrDept())){
				customerManagerDTO.setType("fourDept");
				customerManagerDTO.setFourDept("");
				customerManagerDTO.setFiveDept("");
				strs = customerManagerMapper.getCustomerManagerGroup(customerManagerDTO);     		//查询第四列团队
			}else if(type.equals("4") && StringUtil.isNotEmpty(customerManagerDTO.getTwoDept()) 
					&& StringUtil.isNotEmpty(customerManagerDTO.getThrDept()) 
					&& StringUtil.isNotEmpty(customerManagerDTO.getFourDept())){
				customerManagerDTO.setType("fiveDept");
				customerManagerDTO.setFiveDept("");
				strs = customerManagerMapper.getCustomerManagerGroup(customerManagerDTO);
			}
			if(strs!=null){
				return strs;
			}
			return null;
		}catch(Exception e){
			logger.error("getCustomerManagerGroup()" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 客户经理变更-->获取客户经理列表
	 * @Param customerManagerDTO 客户经理DTO
	 */
	@Override
	public List<Map<String,Object>> getCustomerManagerList(CustomerManagerDTO customerManagerDTO){
		try{
			if(StringUtil.isNotEmpty(customerManagerDTO.getTwoDept()) && StringUtil.isNotEmpty(customerManagerDTO.getThrDept()) 
				&& StringUtil.isNotEmpty(customerManagerDTO.getFourDept()) && StringUtil.isNotEmpty(customerManagerDTO.getFiveDept())){
				List<Map<String,Object>> managerList = customerManagerMapper.getCustomerManagerList(customerManagerDTO);
				if(managerList != null){
					return managerList;
				}
			}
			return null;
		}catch(Exception e){
			logger.error("getCustomerManagerList()" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 客户详情（行为轨迹）
	 */
	@Override
	public List<Map<String,Object>> userTrack(CustomerQueryConditionDTO cqcDTO) {
		//储蓄内容list
		List<Map<String,Object>> userTrackList = new ArrayList<Map<String,Object>>();
		//用于将结果进行时间排序的list（外层）
		List<Date> dateList = new ArrayList<Date>();
		// 是否实名信息
		PayCustomer payCustomer = payCustomerMapper.findByMemberOID(cqcDTO.getOID());
		if (payCustomer != null) {
			List<UserTrackDTO> usertrackList = webP2pNoviceBiddingRuningMapper.findByMemberOId(cqcDTO);
			// 新手投记录信息
			if (usertrackList != null && usertrackList.size()>0) {
				for(UserTrackDTO usertrack : usertrackList){
					//获取协议内容
					MemberMemberAgreement magrement = memberAgreementDao.findTBZAgreementByOID(cqcDTO.getOID(),"2");
					Date xstsj = usertrack.getAddDateTime();
					//定义一个开关 
					Boolean switchs = false;
					for(Map<String,Object> maps : userTrackList){
						Date times = (Date)maps.get("titleTime");
						if(DateUtils.isSameDay(times, xstsj)){
							List<Map<String,Object>> list = (List)maps.get("data");
							Map<String,Object> xstContentMap = new HashMap<String,Object>();
							xstContentMap.put("contentTime",xstsj);  //用于同一天内容的排序
							xstContentMap.put("productName", usertrack.getProductName());
							xstContentMap.put("amountMoney", usertrack.getMoney().toString());
							xstContentMap.put("productOID", usertrack.getProductOID());
							if(magrement!=null){
								xstContentMap.put("agrementOID", magrement.getOID());
							}
							list.add(xstContentMap);
							maps.remove("data");
							maps.put("data", list);
							switchs = true;
						}
					}
					//没有相同日期时 新增加一组数据
					if(!switchs){
						Map<String,Object> xstMap = new HashMap<String,Object>();
						List<Map<String,Object>> xstList = new ArrayList<Map<String,Object>>();
						Map<String,Object> xstContentMap = new HashMap<String,Object>();
						xstContentMap.put("contentTime",xstsj);  //用于同一天内容的排序（内层）
						xstContentMap.put("productName", usertrack.getProductName());
						xstContentMap.put("amountMoney", usertrack.getMoney().toString());
						xstContentMap.put("productOID", usertrack.getProductOID());
						if(magrement!=null){
							xstContentMap.put("agrementOID", magrement.getOID());
						}
						xstList.add(xstContentMap);
						xstMap.put("titleTime", xstsj);     //用于不同天内容的排序（外层）
						xstMap.put("type", "1");            //1:资金信息
						xstMap.put("data", xstList);
						userTrackList.add(xstMap);
						dateList.add(xstsj);
					}
				}
			}
			// 环环投记录信息
			List<PayCashPool> cashPoolList = payCashPoolMapper.findByMemberOID(cqcDTO);
			if (cashPoolList != null && cashPoolList.size()>0) {
				for (PayCashPool payCashPool : cashPoolList) {
					String tzje = payCashPool.getPrincipalMoney().toString();
					// 获取产品名称
					WebP2pPackageBiddingMainRuning wppbmr = webP2pPackageBiddingMainRuningMapper
							.findByOID(payCashPool.getBusinessOID());
					String productName = wppbmr.getPackageName();
					//获取协议内容
					MemberMemberAgreement magrement = memberAgreementDao.gainAgreementXYCK2(payCashPool.getOID(),"4");
					// 获取时间
					Date hhtsj = payCashPool.getAddDateTime();
					//定义一个开关  
					Boolean switchs = false;
					for(Map<String,Object> maps : userTrackList){
						Date times = (Date)maps.get("titleTime");
						// 判断日期是否相同（年月日）
						if(DateUtils.isSameDay(times, hhtsj)){
							List<Map<String,Object>> list = (List)maps.get("data");
							Map<String,Object> hhtContentMap = new HashMap<String,Object>();
							hhtContentMap.put("contentTime",hhtsj);  //用于同一天内容的排序
							hhtContentMap.put("productName", productName);
							hhtContentMap.put("amountMoney", tzje);
							hhtContentMap.put("productOID", wppbmr.getOID());
							if(magrement!=null){
								hhtContentMap.put("agrementOID", magrement.getOID());
							}
							list.add(hhtContentMap);
							maps.remove("data");
							maps.put("data", list);
							switchs = true;
						}
					}
					//没有相同日期时 新增加一组数据
					if(!switchs){
						Map<String,Object> hhtMap = new HashMap<String,Object>();
						List<Map<String,Object>> hhtList = new ArrayList<Map<String,Object>>();
						Map<String,Object> hhtContentMap = new HashMap<String,Object>();
						hhtContentMap.put("contentTime",hhtsj);  //用于同一天内容的排序（内层）
						hhtContentMap.put("productName", productName);
						hhtContentMap.put("amountMoney", tzje);
						hhtContentMap.put("productOID", wppbmr.getOID());
						if(magrement!=null){
							hhtContentMap.put("agrementOID", magrement.getOID());
						}
						hhtList.add(hhtContentMap);
						hhtMap.put("titleTime", hhtsj);     //用于不同天内容的排序（外层）
						hhtMap.put("type", "1");            //资金信息
						hhtMap.put("data", hhtList);
						userTrackList.add(hhtMap);
						dateList.add(hhtsj);
					}
				}
			}
			// 已实名
			Map<String,Object> smMap = new HashMap<String,Object>();
			List<String> smList = new ArrayList<String>();
			// 实名日期
			Date smsj = payCustomer.getAddDateTime();
			String smTime = smsj.toString().substring(11, 16);
//						String smTime = DateUtils.formatDate(smsj, "");
			String smjl = smTime + " 实名认证成功";
			smList.add(smjl);
			smMap.put("titleTime", smsj);
			smMap.put("type", "0");            //非资金信息
			smMap.put("data", smList);
			userTrackList.add(smMap);
			dateList.add(smsj);
		}
		// 判断是否账号锁定
		MemberMember memberMember = memberMemberMapper.selectByPrimaryKey(cqcDTO.getOID());
		MemberBlackList memberBlackList = memberBlackListMapper.selectByName(memberMember.getName());
		if (memberBlackList != null) {
			// 账号锁定信息
			Map<String,Object> sdMap = new HashMap<String,Object>();
			List<String> sdList = new ArrayList<String>();
			Date sdsj = memberBlackList.getAddDateTime();
			String sdTime = sdsj.toString().substring(11, 16);
//			String sdTime = DateUtils.formatDate(sdsj, "");
			String sdjl = sdTime + " 登录失败次数超限，账号锁定";
			sdList.add(sdjl);
			sdMap.put("titleTime", sdsj);
			sdMap.put("type", "0");            //非资金信息
			sdMap.put("data", sdList);
			userTrackList.add(sdMap);
			dateList.add(sdsj);
		}
		// 未实名，只有注册时间信息
		Map<String,Object> zcMap = new HashMap<String,Object>();
		List<String> zcList = new ArrayList<String>();
		Date zcsj = memberMember.getAddDateTime();
		String zcTime = zcsj.toString().substring(11, 16);
//		String zcTime = DateUtils.formatDate(zcsj, "");
		String zcjl = zcTime + " 注册成功(Web)";
		zcList.add(zcjl);
		zcMap.put("titleTime", zcsj);
		zcMap.put("type", "0");            //非资金信息
		zcMap.put("data", zcList);
		userTrackList.add(zcMap);
		dateList.add(zcsj);
		//将dateList排序(冒泡排序)
		Date tempDate = null;
		for(int i=0;i<dateList.size()-1;i++){
			for(int j=0;j<dateList.size()-1-i;j++){
				//倒序：从大到小
				if(dateList.get(j).before(dateList.get(j+1))){
					tempDate = dateList.get(j);
					dateList.set(j, dateList.get(j+1));
					dateList.set(j+1,tempDate);
				}
				//正序：从小到大
				//if(dateList.get(j).after(dateList.get(j+1)))
			}
		}
		//定义新list 用于保存排序后的数据
		List<Map<String,Object>> NewUserTrackList = new ArrayList<Map<String,Object>>();
		for(Date date : dateList){
			Map<String,Object> map = new HashMap<String,Object>();
			for(Map<String,Object> maps : userTrackList){
				Date times = (Date)maps.get("titleTime");
				if(date.compareTo(times)==0){
					//将显示日期格式转换
					String time = DateUtils.formatJustDate(date.getTime()) + " " + DateUtils.dayForWeek(date);
					map.put("titleTime", time);
					map.put("data", maps.get("data"));
					map.put("type", maps.get("type"));
					NewUserTrackList.add(map);
				}
			}
		}
		//同一天内数据按时间进行排序
		for(Map<String,Object> maps : NewUserTrackList){
			String type = (String)maps.get("type");      
			if(type.equals("1")){			//资金信息进行排序
				List<Map<String,Object>> data = (List)maps.get("data");    //排序前数据
				List<Map<String,Object>> newData = new ArrayList<Map<String,Object>>();  //排序后数据
				List<Date> timeList = new ArrayList<Date>();     //排序时间集合
				for(Map<String,Object> map : data){
					timeList.add((Date)map.get("contentTime"));
				}
				//将timeList排序
				Date tempTime = null;
				for(int i=0;i<timeList.size()-1;i++){
					for(int j=0;j<timeList.size()-1-i;j++){
						//倒序：从大到小
						if(timeList.get(j).before(timeList.get(j+1))){
							tempTime = timeList.get(j);
							timeList.set(j, timeList.get(j+1));
							timeList.set(j+1,tempTime);
						}
					}
				}
				//循环按时间排序生成新数据
				for(Date date : timeList){
					Map<String,Object> newMap = new HashMap<String,Object>();
					Iterator<Map<String,Object>> map = data.iterator();
					while(map.hasNext()){
						Map<String,Object> nextMap = map.next();
						Date times = (Date)nextMap.get("contentTime");
						if(date.compareTo(times)==0){
							String subTime = times.toString().substring(11, 16);
							newMap.put("contentTime",subTime);
							newMap.put("productName", nextMap.get("productName"));
							newMap.put("amountMoney", nextMap.get("amountMoney"));
							newMap.put("agrementOID", nextMap.get("agrementOID"));
							newMap.put("productOID", nextMap.get("productOID"));
					    	map.remove();
					    	newData.add(newMap);
					    }
					}
				}
				maps.remove("data");
				maps.put("data", newData);
			}
		}
		return NewUserTrackList;
	}

	/**
	 * 客服登记分页显示
	 */
	@Override
	public Pagination<CustomerFeedbackDTO> listCustomerFeedback(Integer currentPage, String type) {
		Pagination<CustomerFeedbackDTO> pagination = new Pagination<>();
		// 当前页
		pagination.setCurrentPage(currentPage);
		// 每页显示数据数
		pagination.setPageSize(10);
		// 总数据数
		int totalNumber = customerFeedbackMapper.countServiceRegisterNumber(type);
		pagination.setTotalNumber(totalNumber);
		// 总页数
		if (totalNumber % pagination.getPageSize() == 0) {
			pagination.setTotalPage(totalNumber / pagination.getPageSize());
		} else {
			pagination.setTotalPage((totalNumber / pagination.getPageSize()) + 1);
		}
		// 每页显示数据集
		Integer startIndex = (currentPage-1)*pagination.getPageSize();
		List<CustomerFeedback> list = customerFeedbackMapper.listServiceRegister(startIndex,
				pagination.getPageSize(), type);
		// 遍历集合
		List<CustomerFeedbackDTO> cfDTOList = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (CustomerFeedback customerFeedback : list) {
				CustomerFeedbackDTO cfDTO = new CustomerFeedbackDTO();
				cfDTO.setOid(customerFeedback.getOid());
				cfDTO.setPhone(customerFeedback.getPhone());
				cfDTO.setName(customerFeedback.getName());
				cfDTO.setFeedbackContent(customerFeedback.getFeedbackContent());
				cfDTO.setFeedbackDate(DateUtils.formatDate(customerFeedback.getFeedbackDate(), null));
				// 根据身份证判断性别
				MemberMember findUserByName = memberMemberMapper.findUserByName(customerFeedback.getPhone());
				if (findUserByName != null) {
					String idCard = findUserByName.getIdCard();
					if (StringUtil.isNotEmpty(idCard)) {
						Integer sexNumber = Integer.valueOf(idCard.substring(16, 17));
						if ((sexNumber % 2) == 0) {
							cfDTO.setSex("2");
						} else {
							cfDTO.setSex("1");
						}
					} else {
						// 未实名
						cfDTO.setSex("0");
					}
				}
				// 类型
				String status = "";
				switch (customerFeedback.getStatus()) {
				case 0:
					status = "未回复";
					break;
				case 1:
					status = "解决中";
					break;
				case 2:
					status = "已回复";
					break;
				case 3:
					status = "未解决";
					break;
				case 4:
					status = "已解决";
					break;
				}
				cfDTO.setStatus(status);
				// 最近反馈人
				DetailFeedback detailFeedback = detailFeedbackMapper.findFeedbackPerson(customerFeedback.getOid(),
						customerFeedback.getName());
				if (detailFeedback != null) {
					cfDTO.setServiceName(detailFeedback.getFeedbackPerson());
				} else {
					cfDTO.setServiceName("无");
				}
				cfDTOList.add(cfDTO);
			}
		}
		pagination.setData(cfDTOList);
		return pagination;
	}

	/**
	 * 客服登记条件查询
	 * 
	 * @param currentPage
	 * @param cqcDTO
	 * @return
	 */
	@Override
	public Pagination<CustomerFeedbackDTO> conditionListCustomerFeedback(Integer currentPage,
			CustomerQueryConditionDTO cqcDTO) {
		// 关键字去空格
		if (StringUtil.isNotEmpty(cqcDTO.getKeyWord())) {
			cqcDTO.setKeyWord(cqcDTO.getKeyWord().trim());
		}
		// 判断是否有时间排序
		if (StringUtil.isEmpty(cqcDTO.getDateSort())) {
			cqcDTO.setDateSort("desc");
		}
		// 分页信息
		Pagination<CustomerFeedbackDTO> pagination = new Pagination<>();
		// 当前页
		pagination.setCurrentPage(currentPage);
		// 每页显示条数
		pagination.setPageSize(10);
		// 总数据数
		int totalNumber = customerFeedbackMapper.countByCondition(cqcDTO);
		pagination.setTotalNumber(totalNumber);
		// 总页数
		if (totalNumber % pagination.getPageSize() == 0) {
			pagination.setTotalPage(totalNumber / pagination.getPageSize());
		} else {
			pagination.setTotalPage((totalNumber / pagination.getPageSize()) + 1);
		}
		// 每页显示数据集
		Integer startIndex = (currentPage-1)*pagination.getPageSize();
		List<CustomerFeedback> list = customerFeedbackMapper.selectByCondition(startIndex,
				pagination.getPageSize(), cqcDTO);
		// 遍历集合
		List<CustomerFeedbackDTO> cfDTOList = new ArrayList<>();
		for (CustomerFeedback customerFeedback : list) {
			CustomerFeedbackDTO cfDTO = new CustomerFeedbackDTO();
			cfDTO.setOid(customerFeedback.getOid());
			cfDTO.setPhone(customerFeedback.getPhone());
			cfDTO.setName(customerFeedback.getName());
			cfDTO.setFeedbackContent(customerFeedback.getFeedbackContent());
			cfDTO.setFeedbackDate(DateUtils.formatDate(customerFeedback.getFeedbackDate(), null));
			// 根据身份证判断性别
			MemberMember findUserByName = memberMemberMapper.findUserByName(customerFeedback.getPhone());
			if (findUserByName != null) {
				String idCard = findUserByName.getIdCard();
				if (StringUtil.isNotEmpty(idCard)) {
					Integer sexNumber = Integer.valueOf(idCard.substring(16, 17));
					if ((sexNumber % 2) == 0) {
						cfDTO.setSex("2");
					} else {
						cfDTO.setSex("1");
					}
				} else {
					// 未实名
					cfDTO.setSex("0");
				}
			}
			// 类型
			String status = "";
			switch (customerFeedback.getStatus()) {
			case 0:
				status = "未回复";
				break;
			case 1:
				status = "解决中";
				break;
			case 2:
				status = "已回复";
				break;
			case 3:
				status = "未解决";
				break;
			case 4:
				status = "已解决";
				break;
			}
			cfDTO.setStatus(status);
			// 最近反馈人
			DetailFeedback detailFeedback = detailFeedbackMapper.findFeedbackPerson(customerFeedback.getOid(),
					customerFeedback.getName());
			if (detailFeedback != null) {
				cfDTO.setServiceName(detailFeedback.getFeedbackPerson());
			} else {
				cfDTO.setServiceName("无");
			}
			cfDTOList.add(cfDTO);
		}
		pagination.setData(cfDTOList);
		return pagination;
	}

	/**
	 * 客户登记添加
	 */
	@Override
	public int savecustomerfeedback(String mobile, String realName, String content) {
		// 存入反馈信息表
		CustomerFeedback customerFeedback = new CustomerFeedback();
		customerFeedback.setOid(StringUtil.getUUID());
		customerFeedback.setPhone(mobile);
		customerFeedback.setName(realName);
		customerFeedback.setFeedbackContent(content);
		customerFeedback.setFeedbackDate(new Date());
		customerFeedback.setType(0);
		customerFeedback.setStatus(0);
		customerFeedback.setAddDatetime(new Date());
		int row = customerFeedbackMapper.insertSelective(customerFeedback);
		if (row != 0) {
			// 存入反馈信息详细表
			DetailFeedback detailFeedback = new DetailFeedback();
			detailFeedback.setOid(StringUtil.getUUID());
			detailFeedback.setCustomerFeedbackOid(customerFeedback.getOid());
			detailFeedback.setFeedbackDate(customerFeedback.getFeedbackDate());
			detailFeedback.setFeedbackContent(customerFeedback.getFeedbackContent());
			detailFeedback.setFeedbackPerson(customerFeedback.getName());
			detailFeedbackMapper.insertSelective(detailFeedback);
		}
		return row;
	}

	/**
	 * 根据手机号获取客户姓名
	 */
	@Override
	public Map<String, String> getNameByMobile(String mobile) {
		Map<String, String> map = new HashMap<>();
		String name = "";
		MemberMember memberMember = memberMemberMapper.selectByMobile(mobile);
		if (memberMember != null) {
			name = memberMember.getRealName();
			map.put("realName", name);
		} else {
			name = "未实名";
			map.put("realName", name);
		}
		return map;
	}

	/**
	 * 客服登记详情
	 */
	@Override
	public List<FeedBackDetailsDTO> serviceDetails(String oid, String name) {
		List<FeedBackDetailsDTO> detailsList = new ArrayList<>();
		// 获取客服回复信息
		List<DetailFeedback> list = detailFeedbackMapper.selectByCustomerFeedbackOid(oid, name);
		if (list != null && list.size() > 0) {
			for (DetailFeedback detailFeedback : list) {
				FeedBackDetailsDTO feedBackDetailsDTO = new FeedBackDetailsDTO();
				// 格式化时间
				Date addDateTime = detailFeedback.getFeedbackDate();
				String formatDate = DateUtils.formatDate(addDateTime.getTime());
				String monthAndDay = formatDate.substring(5, 10).replace("-", "月") + "日";
				String timeForDay = DateUtils.timeForDay(addDateTime);
				feedBackDetailsDTO.setDate(monthAndDay + " " + timeForDay);
				feedBackDetailsDTO.setTime(formatDate.substring(11, 16));
				// 反馈内容
				feedBackDetailsDTO.setContent(detailFeedback.getFeedbackContent());
				// 反馈人
				feedBackDetailsDTO.setFeedbackPerson(detailFeedback.getFeedbackPerson());
				detailsList.add(feedBackDetailsDTO);
			}
		}
		return detailsList;
	}

	/**
	 * 反馈详情信息提交
	 * 
	 * @param feedBackOID
	 * @param content
	 * @param state
	 * @return
	 */
	@Override
	public int submitfeedback(String oid, CustomerQueryConditionDTO cqcDTO) {
		// 保存反馈回复内容
		DetailFeedback detailFeedback = new DetailFeedback();
		detailFeedback.setOid(StringUtil.getUUID());
		detailFeedback.setCustomerFeedbackOid(oid);
		detailFeedback.setFeedbackContent(cqcDTO.getFeedbackContent());
		detailFeedback.setFeedbackDate(new Date());
		detailFeedback.setFeedbackPerson(cqcDTO.getServiceName());
		int row = detailFeedbackMapper.insertSelective(detailFeedback);
		if (row != 0) {
			// 修改状态
			CustomerFeedback customerFeedback = new CustomerFeedback();
			customerFeedback.setOid(oid);
			customerFeedback.setUpdDatetime(new Date());
			customerFeedback.setStatus(Integer.valueOf(cqcDTO.getStatus()));
			int updRow = customerFeedbackMapper.updateStatusByOid(customerFeedback);
		}
		return row;
	}

	/**
	 * 客户反馈详情
	 */
	@Override
	public List<FeedBackDetailsDTO> feedBackDetails(String oid) {
		List<FeedBackDetailsDTO> detailsList = new ArrayList<>();
		// 获取会话内容
		List<DetailFeedback> list = detailFeedbackMapper.selectByOid(oid);
		if (list != null && list.size() > 0) {
			for (DetailFeedback detailFeedback : list) {
				FeedBackDetailsDTO feedBackDetailsDTO = new FeedBackDetailsDTO();
				// 格式化时间
				Date addDateTime = detailFeedback.getFeedbackDate();
				String formatDate = DateUtils.formatDate(addDateTime.getTime());
				String monthAndDay = formatDate.substring(5, 10).replace("-", "月") + "日";
				String timeForDay = DateUtils.timeForDay(addDateTime);
				feedBackDetailsDTO.setDate(monthAndDay + " " + timeForDay);
				feedBackDetailsDTO.setTime(formatDate.substring(11, 16));
				// 反馈内容
				feedBackDetailsDTO.setContent(detailFeedback.getFeedbackContent());
				// 反馈人
				feedBackDetailsDTO.setFeedbackPerson(detailFeedback.getFeedbackPerson());
				detailsList.add(feedBackDetailsDTO);
			}
		}
		return detailsList;
	}

	/**
	 * 客服登记数据导出
	 */
	@Override
	public List<CustomerExcelDTO> exportServiceRecord(CustomerQueryConditionDTO cqcDTO) {
		List<CustomerFeedback> list = new ArrayList<>();
		List<CustomerExcelDTO> cxDTOList = new ArrayList<>();
		// 判断是否是选中数据导出
		if (StringUtil.isNotEmpty(cqcDTO.getIds())) {
			// 导出勾选的数据
			String oids = cqcDTO.getIds();
			String[] oidArray = oids.split(",");
			list = customerFeedbackMapper.selectByOids(oidArray);
		} else {
			// 导出符合条件的数据
			// 条件去空格
			if (StringUtil.isNotEmpty(cqcDTO.getKeyWord())) {
				cqcDTO.setKeyWord(cqcDTO.getKeyWord().trim());
			}
			// 判断是否有时间排序
			if (StringUtil.isEmpty(cqcDTO.getDateSort())) {
				cqcDTO.setDateSort("desc");
			}
			// 获取符合条件的数据集
			cqcDTO.setType("0");// 设置要查找的数据的类型（0：客服登记，1：客户反馈）
			list = customerFeedbackMapper.selectAllByCondition(cqcDTO);
		}
		// 导入数据到dto中
		if (list != null && list.size() > 0) {
			for (CustomerFeedback customerFeedback : list) {
				CustomerExcelDTO cxDTO = new CustomerExcelDTO();
				cxDTO.setPhone(customerFeedback.getPhone());
				cxDTO.setName(customerFeedback.getName());
				cxDTO.setFeedbackContent(customerFeedback.getFeedbackContent());
				cxDTO.setFeedbackDate(DateUtils.formatDate(customerFeedback.getFeedbackDate(), null));
				// 类型
				String status = "";
				switch (customerFeedback.getStatus()) {
				case 0:
					status = "未回复";
					break;
				case 1:
					status = "解决中";
					break;
				case 2:
					status = "已回复";
					break;
				case 3:
					status = "未解决";
					break;
				case 4:
					status = "已解决";
					break;
				}
				cxDTO.setStatus(status);
				// 最近反馈人
				DetailFeedback detailFeedback = detailFeedbackMapper.findFeedbackPerson(customerFeedback.getOid(),
						customerFeedback.getName());
				if (detailFeedback != null) {
					cxDTO.setServiceName(detailFeedback.getFeedbackPerson());
				} else {
					cxDTO.setServiceName("无");
				}
				// 获取客服回复内容
				List<DetailFeedback> detailFeedbackList = detailFeedbackMapper
						.selectByCustomerFeedbackOid(customerFeedback.getOid(), customerFeedback.getName());
				if (detailFeedbackList != null && detailFeedbackList.size() > 0) {
					StringBuffer sb = new StringBuffer();
					for (DetailFeedback df : detailFeedbackList) {
						sb.append(df.getFeedbackPerson());
						String formatDate = DateUtils.formatDate(customerFeedback.getFeedbackDate(), null);
						sb.append(formatDate + ":");
						sb.append(df.getFeedbackContent() + ".");
					}
					String feedbackContent = new String(sb);
					cxDTO.setChatContent(feedbackContent);
				} else {
					cxDTO.setChatContent("未回复");
				}
				cxDTOList.add(cxDTO);
			}
		}
		return cxDTOList;
	}

	/**
	 * 客服反馈数据导出
	 */
	@Override
	public List<CustomerExcelDTO> exportFeedBack(CustomerQueryConditionDTO cqcDTO) {
		List<CustomerFeedback> list = new ArrayList<>();
		List<CustomerExcelDTO> cxDTOList = new ArrayList<>();
		// 判断是否是选中数据导出
		if (StringUtil.isNotEmpty(cqcDTO.getIds())) {
			// 导出勾选的数据
			String oids = cqcDTO.getIds();
			String[] oidArray = oids.split(",");
			list = customerFeedbackMapper.selectByOids(oidArray);
		} else {
			// 导出符合条件的数据
			// 条件去空格
			if (StringUtil.isNotEmpty(cqcDTO.getKeyWord())) {
				cqcDTO.setKeyWord(cqcDTO.getKeyWord().trim());
			}
			// 判断是否有时间排序
			if (StringUtil.isEmpty(cqcDTO.getDateSort())) {
				cqcDTO.setDateSort("desc");
			}
			// 获取符合条件的数据集
			cqcDTO.setType("1");// 设置要查找的数据的类型（0：客服登记，1：客户反馈）
			list = customerFeedbackMapper.selectAllByCondition(cqcDTO);
		}
		// 导入数据到dto中
		if (list != null && list.size() > 0) {
			for (CustomerFeedback customerFeedback : list) {
				CustomerExcelDTO cxDTO = new CustomerExcelDTO();
				cxDTO.setPhone(customerFeedback.getPhone());
				cxDTO.setName(customerFeedback.getName());
				cxDTO.setFeedbackContent(customerFeedback.getFeedbackContent());
				cxDTO.setFeedbackDate(DateUtils.formatDate(customerFeedback.getFeedbackDate(), null));
				// 类型
				String status = "";
				switch (customerFeedback.getStatus()) {
				case 0:
					status = "未回复";
					break;
				case 1:
					status = "解决中";
					break;
				case 2:
					status = "已回复";
					break;
				case 3:
					status = "未解决";
					break;
				case 4:
					status = "已解决";
					break;
				}
				cxDTO.setStatus(status);
				// 最近反馈人
				DetailFeedback detailFeedback = detailFeedbackMapper.findFeedbackPerson(customerFeedback.getOid(),
						customerFeedback.getName());
				if (detailFeedback != null) {
					cxDTO.setServiceName(detailFeedback.getFeedbackPerson());
				} else {
					cxDTO.setServiceName("无");
				}
				// 获取会话内容
				List<DetailFeedback> detailFeedbackList = detailFeedbackMapper.selectByOid(customerFeedback.getOid());
				if (detailFeedbackList != null && detailFeedbackList.size() > 0) {
					StringBuffer sb = new StringBuffer();
					for (DetailFeedback df : detailFeedbackList) {
						sb.append(df.getFeedbackPerson());
						String formatDate = DateUtils.formatDate(customerFeedback.getFeedbackDate(), null);
						sb.append(formatDate + ":");
						sb.append(df.getFeedbackContent() + ".");
					}
					String feedbackContent = new String(sb);
					cxDTO.setChatContent(feedbackContent);
				} else {
					cxDTO.setChatContent("未回复");
				}
				cxDTOList.add(cxDTO);
			}
		}
		return cxDTOList;
	}

	/**
	 * 点击详情修改状态为解决中
	 */
	@Override
	public int updateStatus(String oid) {
		CustomerFeedback customerFeedback = new CustomerFeedback();
		customerFeedback.setOid(oid);
		Integer status = 1;
		customerFeedback.setStatus(status);
		int row = customerFeedbackMapper.updateStatus(customerFeedback);
		return row;
	}

	/**
	 * 判断用户类型方法
	 */
	public String judgeUserType(String memberOID, PayCustomer pc) {
		if (pc == null) {
			// 未实名用户
			return "未首投+体验金+无";
		} else {
			// 已实名，判断用户是否使用体验金
			int customerTyjNumber = payCustomerTyjMapper.countByMemberOID(memberOID);
			if (customerTyjNumber == 0) {
				// 未使用体验金,判断是否为首投
				int noviceNumebr = webP2pNoviceBiddingRuningMapper.countByMemberOID(memberOID);
				int cashPoolNumber = payCashPoolMapper.countByMemberOID(memberOID);
				int total = noviceNumebr + cashPoolNumber;
				if (total == 0) {
					// 未首投
					return "未首投+体验金+未投";
				} else if (total == 1) {
					// 首投,判断是否提现
					int txjl = payCustomerBusinessMapper.countByMemberOID(memberOID);
					if (txjl == 0) {
						// 没有提现过
						return "首投+提现+未提现";
					} else {
						// 判断是否全部提现
						int compareTo = pc.getUseMoney().compareTo(new BigDecimal(0));
						if (compareTo == 0) {
							// 全部提现了
							return "首投客+提现+全额";
						} else {
							// 部分提现
							return "首投+提现+部分)";
						}
					}
				} else {
					// 已投多次，判断是否提现
					int txjl = payCustomerBusinessMapper.countByMemberOID(memberOID);
					if (txjl == 0) {
						// 没有提现过
						return "多次投+提现+未)";
					} else {
						// 判断是否全部提现
						int compareTo = pc.getUseMoney().compareTo(new BigDecimal(0));
						if (compareTo == 0) {
							// 全部提现了
							return "多次投+提现+全额";
						} else {
							// 部分提现
							return "多次投+提现+部分";
						}
					}
				}
			} else {
				// 使用了体验金，判断是否为首投
				int noviceNumebr = webP2pNoviceBiddingRuningMapper.countByMemberOID(memberOID);
				int cashPoolNumber = payCashPoolMapper.countByMemberOID(memberOID);
				int total = noviceNumebr + cashPoolNumber;
				if (total == 0) {
					// 未首投
					return "未首投+体验金+已投";
				} else if (total == 1) {
					// 首投,判断是否提现
					int txjl = payCustomerBusinessMapper.countByMemberOID(memberOID);
					if (txjl == 0) {
						// 没有提现过
						return "首投+提现+未";
					} else {
						// 判断是否全部提现
						int compareTo = pc.getUseMoney().compareTo(new BigDecimal(0));
						if (compareTo == 0) {
							// 全部提现了
							return "首投+提现+全额";
						} else {
							// 部分提现
							return "首投+提现+部分";
						}
					}
				} else {
					// 已投多次，判断是否提现
					int txjl = payCustomerBusinessMapper.countByMemberOID(memberOID);
					if (txjl == 0) {
						// 没有提现过
						return "多次投+提现+未";
					} else {
						// 判断是否全部提现
						int compareTo = pc.getUseMoney().compareTo(new BigDecimal(0));
						if (compareTo == 0) {
							// 全部提现了
							return "多次投+提现+全额";
						} else {
							// 部分提现
							return "多次投+提现+部分";
						}
					}
				}
			}
		}
	}

	/**
	 * 客户行为轨迹分页显示
	 */
	@Override
	public Pagination<TrackDTO> track(Integer currentPage) {
		Pagination<TrackDTO> pagination = new Pagination<>();
		// 当前页
		pagination.setCurrentPage(currentPage);
		// 每页显示数据条数
		pagination.setPageSize(10);
		// 总数据数
		int totalNumber = portalLogMapper.selectCount(null);
		pagination.setTotalNumber(totalNumber);
		// 总页数
		if (totalNumber % pagination.getPageSize() == 0) {
			pagination.setTotalPage(totalNumber / pagination.getPageSize());
		} else {
			pagination.setTotalPage((totalNumber / pagination.getPageSize()) + 1);
		}
		// 每页显示数据集
		List<PortalLog> list = portalLogMapper.listByPage((currentPage - 1), pagination.getPageSize());
		List<TrackDTO> trackDTOList = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (PortalLog portalLog : list) {
				TrackDTO trackDTo = new TrackDTO();
				trackDTo.setUsername(portalLog.getUsername());
				trackDTo.setOperationContent(portalLog.getTitle());
				// 计算操作时间距离现在多久
				String timeAgo = timeAgo(portalLog.getOperateDate());
				trackDTo.setTimeAgo(timeAgo);
				trackDTOList.add(trackDTo);
			}
		}
		pagination.setData(trackDTOList);
		return pagination;
	}

	/**
	 * 客户行为轨迹条件查询
	 */
	@Override
	public Pagination<TrackDTO> conditionTrack(TrackQueryConditionDTO trackQueryConditionDTO, Integer currentPage) {
		// 内容去空格
		if (StringUtil.isNotEmpty(trackQueryConditionDTO.getKeyWord())) {
			trackQueryConditionDTO.setKeyWord(trackQueryConditionDTO.getKeyWord().trim());
		}
		// 将时间转换为时间戳
		if (StringUtil.isNotEmpty(trackQueryConditionDTO.getStartDate())) {
			Date startDate = DateUtils.getDate(trackQueryConditionDTO.getStartDate());
			trackQueryConditionDTO.setStartDateTime(startDate.getTime());
			Date endDate = DateUtils.getDate(trackQueryConditionDTO.getEndDate());
			trackQueryConditionDTO.setEndDateTime(endDate.getTime());
		}
		// 拆分字符串
		if (StringUtil.isNotEmpty(trackQueryConditionDTO.getType())) {
			String[] typeArray = trackQueryConditionDTO.getType().split(",");
			trackQueryConditionDTO.setTypeArray(typeArray);
		}
		Pagination<TrackDTO> pagination = new Pagination<>();
		// 当前页
		pagination.setCurrentPage(currentPage);
		// 每页显示数据条数
		pagination.setPageSize(10);
		// 总数据数
		int totalNumber = portalLogMapper.countByCondition(trackQueryConditionDTO);
		pagination.setTotalNumber(totalNumber);
		// 总页数
		if (totalNumber % pagination.getPageSize() == 0) {
			pagination.setTotalPage(totalNumber / pagination.getPageSize());
		} else {
			pagination.setTotalPage((totalNumber / pagination.getPageSize()) + 1);
		}
		// 每页显示数据集
		List<PortalLog> list = portalLogMapper.listByCondition((currentPage - 1), pagination.getPageSize(),
				trackQueryConditionDTO);
		List<TrackDTO> trackDTOList = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (PortalLog portalLog : list) {
				TrackDTO trackDTo = new TrackDTO();
				trackDTo.setUsername(portalLog.getUsername());
				trackDTo.setOperationContent(portalLog.getTitle());
				// 计算操作时间距离现在多久
				String timeAgo = timeAgo(portalLog.getOperateDate());
				trackDTo.setTimeAgo(timeAgo);
				trackDTOList.add(trackDTo);
			}
		}
		pagination.setData(trackDTOList);
		return pagination;
	}

	/**
	 * 计算操作时间距离现在多久
	 * 
	 * @param operateDateTime
	 *            操作时间
	 * @return
	 */
	public String timeAgo(Long operateDateTime) {
		Long differenceTime = new Date().getTime() - operateDateTime;
		long day = differenceTime / (24 * 60 * 60 * 1000);
		long hour = (differenceTime / (60 * 60 * 1000) - day * 24);
		long min = ((differenceTime / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (differenceTime / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		StringBuffer sb = new StringBuffer();
		if (day > 0) {
			sb.append(day + "天");
		}
		if (hour > 0) {
			sb.append(hour + "小时");
		}
		if (min > 0) {
			sb.append(min + "分");
		}
		sb.append(s + "秒 前");
		String timeAgo = new String(sb);
		return timeAgo;
	}

	/**
	 * 客户详情(行为轨迹)三个数据统计
	 */
	@Override
	public UserDetailThreeNumberDTO userTrackThreeNumber(String oid) {
		UserDetailThreeNumberDTO udtnDTO = new UserDetailThreeNumberDTO();
		// 投资总额
		BigDecimal investmentMoney = payCustomerBusinessMapper.countTzMoney(oid);
		if (investmentMoney != null) {
			udtnDTO.setInvestmentMoney(ToolMath.formatNumber(investmentMoney));
		} else {
			udtnDTO.setInvestmentMoney("0");
		}
		// 投资次数
		Integer investmentNumber = payCustomerBusinessMapper.countTzNumber(oid);
		if (investmentNumber != null) {
			udtnDTO.setInvestmentNumber(investmentNumber);
		} else {
			udtnDTO.setInvestmentNumber(0);
		}
		// 投资产品数
		Integer investmentProductNumber = 0;
		int noviceNumer = webP2pNoviceBiddingRuningMapper.countByMemberOID(oid);
		if (noviceNumer != 0) {
			investmentProductNumber += 1;
		}
		int cashPoolNumber = payCashPoolMapper.countByMemberOID(oid);
		if (cashPoolNumber != 0) {
			investmentProductNumber += 1;
		}
		udtnDTO.setInvestmentProductNumber(investmentProductNumber);
		return udtnDTO;
	}

}
