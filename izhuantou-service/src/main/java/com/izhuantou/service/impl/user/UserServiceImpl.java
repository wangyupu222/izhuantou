package com.izhuantou.service.impl.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.common.utils.UtilsMD5;
import com.izhuantou.damain.code.CodeContent;
import com.izhuantou.damain.p2p.P2pCompanyPer;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.user.MemberBlackList;
import com.izhuantou.damain.user.MemberMember;
import com.izhuantou.damain.vo.JoinSelectAllDTO;
import com.izhuantou.damain.vo.PersonalDTO;
import com.izhuantou.damain.vo.UserDTO;
import com.izhuantou.dao.code.CodeContentMapper;
import com.izhuantou.dao.p2p.P2pCompanyPerMapper;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayReturnPlanMapper;
import com.izhuantou.dao.personalCenter.PersonalCenterMapper;
import com.izhuantou.dao.user.MemberBlackListMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.service.api.user.UserService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<MemberMember> implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final UtilsMD5 md5 = new UtilsMD5();
	@Autowired
	private MemberMemberMapper userDao;
	@Autowired
	private PayCashPoolMapper payCashPoolDao;
	@Autowired
	private PayDebitCreditMapper payDebitcreditDao;
	@Autowired
	private PayReturnPlanMapper payReturnPlanDao;
	@Autowired
	private PersonalCenterMapper personalCenterMapper;
	@Autowired
	private CodeContentMapper codeContentDao;
	@Autowired
	private PayCustomerMapper PayCustomerMapper;
	@Autowired
	private P2pCompanyPerMapper p2pCompanyPerDao;
	@Autowired
	private MemberBlackListMapper blackListMapper;

	/** 登录验证账户密码 */
	@Override
	public Map<String, Object> findUserByName(UserDTO user) {
		try {
			Map<String, Object> resMap = new HashMap<String, Object>();

			if (user != null) {
				if (StringUtil.isNotEmpty(user.getName()) && StringUtil.isNotEmpty(user.getPassword())) {
					String pwd = md5.Md5(user.getPassword());
					MemberMember member = userDao.findUserByName(user.getName());
					if (member != null) {
						MemberBlackList black = blackListMapper.selectByName(member.getName());
						if (black == null) {
							String passw = member.getPassword();
							if (passw.equals(pwd)) {
								String opportunity = member.getOpportunity();
								if(!"5".equals(opportunity)){
									member.setOpportunity("5");
									userDao.updateOpportunity(member);
								}
								logger.info("findUserByName()" + "返回结果为");
								resMap.put("message", "1");
								resMap.put("data", member);
								return resMap;
							} else {
								String opportunity = member.getOpportunity();
								if(!"5".equals(opportunity)){
									Date addTime = member.getUpdDateTime();
									long lOne = addTime.getTime();
									long lTwo = new Date().getTime();
									int space = (int) ((lTwo - lOne) / 1000 / 60 / 60);
									if (space >= 12) {
										opportunity="5";
										member.setOpportunity("5");
										userDao.updateOpportunity(member);
									}
								}
								int oppor = Integer.valueOf(opportunity) - 1;
								member.setOpportunity(String.valueOf(oppor));
								userDao.updateOpportunity(member);
								resMap.put("message", "密码错误，您今日再输错" + oppor + "次，您的账号将锁定无法登陆");
								if (0 == oppor) {
									MemberBlackList newblack = new MemberBlackList();
									newblack.setOID(StringUtil.getUUID());
									newblack.setName(member.getName());
									resMap.put("message", "今日密码输错次数已达上限，账号已锁定，请重置密码");
									blackListMapper.insertMemberBlack(newblack);
								}
							}
						} else {
							// 时间判断
							Date addTime = black.getAddDateTime();
							long lOne = addTime.getTime();
							long lTwo = new Date().getTime();
							int space = (int) ((lTwo - lOne) / 1000 / 60 / 60);
							if (space >= 12) {
								member.setOpportunity("5");
								userDao.updateOpportunity(member);
								blackListMapper.deleteByPrimaryKey(black.getOID());
								String passw = member.getPassword();
								if (passw.equals(pwd)) {
									logger.info("findUserByName()" + "返回结果为");
									resMap.put("message", "1");
									resMap.put("data", member);
									return resMap;
								} else {
									String opportunity = member.getOpportunity();
									int oppor = Integer.valueOf(opportunity) - 1;
									member.setOpportunity(String.valueOf(oppor));
									userDao.updateOpportunity(member);
									resMap.put("message", "密码错误，您今日再输错" + oppor + "次，您的账号将锁定无法登陆");
								}
							}
						}
					}else{
						resMap.put("message", "账户不存在");
					}
				}
			}
			return resMap;
		} catch (Exception e) {
			logger.error("findUserByName()", e.getMessage());
		}
		return null;
	}

	/** 根据用户名修改密码 */
	@Override
	public Integer updatePassword(UserDTO user) {
		try {
			if (StringUtil.isNotEmpty(user.getName()) && StringUtil.isNotEmpty(user.getPassword())) {
				MemberMember member = userDao.findUserByName(user.getName());
				if (member != null) {
					String password = md5.Md5(user.getPassword());
					MemberMember us = new MemberMember();
					us.setName(user.getName());
					us.setPassword(password);
					us.setVersion(member.getVersion());
					int rows = userDao.updatePassword(us);
					if (rows == 1) {
						return rows;
					}
				}
			}
			return null;
		} catch (Exception e) {
			logger.error("registByNameAndPassword()", e.getMessage());
			return null;
		}
	}

	/**
	 * 查询用户密保的问题
	 */
	@Override
	public Map<String, String> findTwoQuestion(UserDTO user) {
		MemberMember member = null;
		try {
			if (StringUtil.isNotEmpty(user.getName())) {
				member = userDao.findUserByName(user.getName());
				if (StringUtil.isNotEmpty(member.getQuestionOne()) && StringUtil.isNotEmpty(member.getQuestionTwo())) {
					Integer questionOneType = Integer.parseInt(member.getQuestionOne());
					Integer questionTwoType = Integer.parseInt(member.getQuestionTwo());
					CodeContent Code1 = codeContentDao.findTwoQuestion(questionOneType);
					CodeContent Code2 = codeContentDao.findTwoQuestion(questionTwoType);
					String questionOne = Code1.getNameCN();
					String questionTwo = Code2.getNameCN();
					Map<String, String> map = new HashMap<String, String>();
					map.put("questionOne", questionOne);
					map.put("questionTwo", questionTwo);
					map.put("answerOne", member.getAnswerOne());
					map.put("answerTwo", member.getAnswerTwo());
					return map;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			logger.error("findTwoQuestionOne(UserDTO user)", e.getMessage());
			return null;
		}
		return null;
	}

	@Override
	public PersonalDTO findIndexNumber(String memberOID) {
		try {
			// 将数据转换成 BigDecimal进行计算的更精确
			BigDecimal cybj = new BigDecimal(0.0);
			BigDecimal yjsy = new BigDecimal(0.0);
			// 用来盛放用户参加环环头和点点头的数据信息 // -----------
			List<JoinSelectAllDTO> hhdtorps = payReturnPlanDao.gainReturnPlanByMemberOIDAndState(memberOID,
					"agentPlan");
			List<JoinSelectAllDTO> dddtorps = personalCenterMapper.gainDDReturnMoneyBymemberOIDANDState(memberOID,
					"plan");

			if (hhdtorps.size() > 0) {
				for (JoinSelectAllDTO payReturnPlan : hhdtorps) {
					yjsy = yjsy.add(payReturnPlan.getInterestMoney());
					cybj = cybj.add(payReturnPlan.getPrincipalMoney());
				}
			}
			if (dddtorps.size() > 0) {
				for (JoinSelectAllDTO JoinSelectAll : dddtorps) {
					yjsy = yjsy.add(JoinSelectAll.getInterestMoney());
					cybj = cybj.add(JoinSelectAll.getPrincipalMoney());
				}
			}
			BigDecimal jxsy = new BigDecimal(0.0);
			BigDecimal tqsyds = new BigDecimal(0.0);
			List<PayCashPool> memberCashPool = payCashPoolDao.gainByMemberOID(memberOID);
			if (memberCashPool != null) {
				for (PayCashPool pcp : memberCashPool) {
					if ("investment".equals(pcp.getState()) && null != pcp.getJXinterest()) {
						jxsy = jxsy.add(pcp.getJXinterest());
					}
					if ("investment".equals(pcp.getState()) && null != pcp.getPrivilegeIncome()) {
						tqsyds = tqsyds.add(pcp.getPrivilegeIncome());
					}
				}
			}
			// 代收本金：cybj，代收利息：yjsy，代收特权：tqsyds
			PersonalDTO personal = new PersonalDTO();
			yjsy = yjsy.add(jxsy);
			personal.setCybj(cybj);
			personal.setYjsy(yjsy);
			personal.setTqsyds(tqsyds);
			return personal;
		} catch (Exception e) {
			logger.error("findIndexNumber(String memberOID)", e.getMessage());
			return null;
		}

	}

	/**
	 * 判断状态返回 根据特权OID和产品OID
	 * 
	 * @param memberOID
	 * @param state
	 * @return
	 */
	public List<Map<String, Object>> gainDDTTQBymemberANDstate(String memberOID, String state) {
		if (StringUtil.isNotEmpty(memberOID)) {
			if (StringUtil.isNotEmpty(state)) {
				if (state.equals("1")) {
					return payDebitcreditDao.gainDDTTQBymemberANDstateOne(memberOID);
				} else if (state.equals("2")) {
					return payDebitcreditDao.gainDDTTQBymemberANDstateTwo(memberOID);
				}
			}
			return null;
		} else {
			return null;
		}

	}

	/**
	 * 判断状态返回对应得数据 特权代收的
	 * 
	 * @param memberOID
	 * @param state
	 * @return
	 */
	public List<Map<String, Object>> gainByMemebrOIDAndTQ(String memberOID, String state) {
		if (StringUtil.isNotEmpty(state)) {
			return payCashPoolDao.gainByMemebrOIDAndTQTwo(memberOID, state);
		} else {
			return payCashPoolDao.gainByMemebrOIDAndTQOne(memberOID);
		}
	}

	/**
	 * 判断用户是否开通资金账户
	 */
	@Override
	public PayCustomer findCustomerByMemberOID(String memberOID) {
		try {
			PayCustomer customer = PayCustomerMapper.findByMemberOID(memberOID);
			return customer;
		} catch (Exception e) {
			logger.error("findIndexNumber(String memberOID)", e.getMessage());
			return null;
		}

	}

	/** 注册验证用户名是否存在 */
	@Override
	public String registUser(UserDTO user) {
		try {
			if (StringUtil.isNotEmpty(user.getName()) && StringUtil.isNotEmpty(user.getPassword())) {
				MemberMember member = userDao.findUserByName(user.getName());
				if (member == null) {
					String OID = StringUtil.getUUID();
					String password = md5.Md5(user.getPassword());
					MemberMember us = new MemberMember();
					us.setName(user.getName());
					us.setMemberAccount("ZT" + user.getName());
					us.setOID(OID);
					us.setPassword(password);
					us.setYewuOID(user.getYqmReg());
					us.setAddUserOID(user.getChannel());
					int rows = userDao.registUser(us);
					return String.valueOf(rows);
				} else {
					return "账号已存在";
				}
			}
			return "账号和密码不能为空";
		} catch (Exception e) {
			logger.error("findUserByName()", e.getMessage());
			return null;
		}
	}

	/**
	 * 获取邀请ren的OID
	 */
	@Override
	public Map<String, String> registUseryqmReg(String yqm_reg) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (StringUtil.isNotEmpty(yqm_reg) && yqm_reg.contains("手机")) {
				yqm_reg = "";
			}
			if (StringUtil.isNotEmpty(yqm_reg)) {
				if (("18606563456").equals(yqm_reg) || ("13105797175").equals(yqm_reg)
						|| ("15023178890").equals(yqm_reg) || ("15070681024").equals(yqm_reg)
						|| ("18798669898").equals(yqm_reg)) {
					map.put("message", "推荐用户不存在");
					map.put("yqm_reg", "0");
					return map;
				} else {
					MemberMember member = userDao.findUserByName(yqm_reg);

					P2pCompanyPer compan = p2pCompanyPerDao.findComperByNumber(yqm_reg);
					if (member == null) {
						if (compan == null) {
							map.put("message", "推荐用户不存在");
							map.put("yqm_reg", "0");
							return map;
						} else {
							yqm_reg = compan.getOID();
							map.put("message", "0");
							map.put("yqm_reg", yqm_reg);
						}
					} else {
						yqm_reg = member.getOID();
						map.put("message", "0");
						map.put("yqm_reg", yqm_reg);
					}
				}
			}
			return map;
		} catch (Exception e) {
			logger.error("findUserByName()", e.getMessage());
			return null;
		}
	}

	@Override
	public String cheakMemberBlack(String name) {
		try {
			if (StringUtil.isNotEmpty(name)) {
				MemberBlackList black = blackListMapper.selectByName(name);
				MemberMember member = userDao.findUserByName(name);
				if (black != null) {
					Date addTime = black.getAddDateTime();
					long lOne = addTime.getTime();
					long lTwo = new Date().getTime();
					int space = (int) ((lTwo - lOne) / 1000 / 60 / 60);
					if (space >= 12) {
						member.setOpportunity("5");
						userDao.updateOpportunity(member);
						blackListMapper.deleteByPrimaryKey(black.getOID());
					}else{
						return "0";
					}
				}
				return "1";
			}
		} catch (Exception e) {
			logger.error("cheakMemberBlack(String name)", e.getMessage());
		}
		return null;
	}
}
