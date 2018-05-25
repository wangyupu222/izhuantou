package com.izhuantou.service.impl.personalCenter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.pay.PayCashPool;
import com.izhuantou.damain.pay.PayDebitCredit;
import com.izhuantou.damain.pay.PayPrivilege;
import com.izhuantou.damain.pay.PayPrivilegeMemberMapping;
import com.izhuantou.damain.pay.PayPrivilegeProductMapping;
import com.izhuantou.damain.pay.PayReturnPlan;
import com.izhuantou.damain.pay.PayTransferReturn;
import com.izhuantou.damain.vo.HHThavingDetailsDTO;
import com.izhuantou.damain.vo.MyLentTBZDaoDTO;
import com.izhuantou.damain.vo.MylendDDTDaoDTO;
import com.izhuantou.damain.vo.MylendDaoDTO;
import com.izhuantou.damain.vo.MylendResultDTO;
import com.izhuantou.damain.vo.MylendZQWCDaoDTO;
import com.izhuantou.damain.vo.MylendZQZRDTO;
import com.izhuantou.damain.webp2p.WebP2pDebtTransferApply;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.pay.PayCashPoolMapper;
import com.izhuantou.dao.pay.PayDebitCreditMapper;
import com.izhuantou.dao.pay.PayPrivilegeMapper;
import com.izhuantou.dao.pay.PayPrivilegeMemberMappingMapper;
import com.izhuantou.dao.pay.PayPrivilegeProductMappingMapper;
import com.izhuantou.dao.pay.PayReturnPlanMapper;
import com.izhuantou.dao.pay.PayTransferReturnMapper;
import com.izhuantou.dao.personalCenter.MylendMapper;
import com.izhuantou.dao.webp2p.WebP2pDebtTransferApplyMapper;
import com.izhuantou.dao.webp2p.WebP2pNormalBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.service.api.personalCenter.MylendService;

@Service("mylendService")
public class MylendServiceImpl implements MylendService {
	private static final Logger logger = LoggerFactory.getLogger(MylendServiceImpl.class);
	@Autowired
	private MylendMapper mylendMapper;

	@Autowired
	private PayReturnPlanMapper payReturnPlanDao;

	@Autowired
	private PayPrivilegeMapper PAY_PrivilegeMapper;

	@Autowired
	private WebP2pProductRateInfoMapper WEBP2P_ProductRateInfoMapper;

	@Autowired
	private PayCashPoolMapper payCashPoolDao;

	@Autowired
	private PayTransferReturnMapper payTransferReturnMapper;

	@Autowired
	private WebP2pNoviceBiddingRuningMapper WEBP2PNoviceBiddingRuningMapper;

	@Autowired
	private WebP2pNormalBiddingRuningMapper WEBP2PNormalBiddingRuningMapper;

	@Autowired
	private PayPrivilegeProductMappingMapper payPrivilegeProductMappingMapper;

	@Autowired
	private PayPrivilegeMemberMappingMapper payPrivilegeMemberMappingMapper;

	@Autowired
	private PayDebitCreditMapper payDebitCreditMapper;
	@Autowired
	private WebP2pDebtTransferApplyMapper WEBP2PDebtTransferApplyMapper;

	@Autowired
	private WebP2pPackageBiddingMainRuningMapper WEBP2PPackageBiddingMainRuningMapper;

	/**
	 * 查询用户持有环环头列表
	 */
	@Override
	public Pagination<MylendDaoDTO> findHHhavingBymemberOID(String memberOID, Integer currentPage) {
		try {
			// 持有中的标的
			if (StringUtil.isEmpty(memberOID)) {
				return null;
			}
			long forart = System.currentTimeMillis();
			// 分页信息
			Pagination<MylendDaoDTO> pageController = new Pagination<>();
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			int num = mylendMapper.gethavingRowCount(memberOID);
			pageController.setTotalNumber(num);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();
			// 获取查询记录
			long sstart = System.currentTimeMillis();
			List<MylendDaoDTO> hhtList = mylendMapper.findHHhavingBymemberOID(memberOID, startIndex, pageSize);
			System.err.println("获取查询记录耗时" + (System.currentTimeMillis() - sstart));
			System.err.println(hhtList);

			for (MylendDaoDTO mldto : hhtList) {
				// 出借时间
				Date cjsj = DateUtils.getJustDate(mldto.getCjsjTime());
				String cjsjTime = DateUtils.formatJustDate(cjsj.getTime());
				mldto.setCjsjTime(cjsjTime);
				Date dqsj = DateUtils.getJustDate(mldto.getDqsj());
				String dqsjTime =DateUtils.formatJustDate(dqsj.getTime());
				mldto.setDqsj(dqsjTime);
				if (StringUtil.isNotEmpty(mldto.getCreditType()) && (mldto.getCreditType().equals("OPI"))) {
					// 环环投的全是一次性还本付息的  5/14
					String xghkr = DateUtils.gainPlusAndDay(dqsjTime, 1, 0);
					mldto.setXghkr(xghkr);
				}else{
					int syqs = mldto.getSyqs();
					String	xghkr = DateUtils.gainPlusAndReduceDay(cjsjTime, 1, 0);
					mldto.setXghkr(xghkr);
					mldto.setDqsj(DateUtils.gainPlusAndReduceDay(cjsjTime, syqs, 0));
					
				}
				
				// 判断标的状态
				int c = mldto.getMoney().compareTo((new BigDecimal(0.00)));
				if (c == 0) {
					mldto.setHkzt("正在回款中");
				} else {
					Integer hkzt = Integer.valueOf(mldto.getHkzt());
					if (StringUtil.isNotEmpty(mldto.getHkzt()) && Integer.valueOf(mldto.getHkzt()) > 0) {
						mldto.setHkzt("正在回款中");
					} else {
						mldto.setHkzt("预约中");
					}
				}
				// 特权
				if (StringUtil.isNotEmpty(mldto.getTqOID())) {
					// 查询特权信息
					PayPrivilege tqInfo = PAY_PrivilegeMapper.findByOID(mldto.getTqOID());
					// 特权名字
					String privilegeName = tqInfo.getPrivilegeName();
					// 特权域(红包数额/加息百分位)
					BigDecimal privilegeRange = tqInfo.getPrivilegeRange();
					// 特权期限(天数)
					int privilegeTerm = tqInfo.getPrivilegeTerm();
					// 特殊券判别
					if (privilegeTerm == 0) {

						WebP2pProductRateInfo product = WEBP2P_ProductRateInfoMapper
								.findByOID(mldto.getProductInfoOID());
						privilegeTerm = (int) (product.getProductTerm() * 30);
					}

					// 计算特权收益
					BigDecimal tqsy = new BigDecimal(0);
					tqsy = tqsy.add(mldto.getCjje());
					tqsy = tqsy.multiply(privilegeRange).divide(new BigDecimal("36500"), 8, BigDecimal.ROUND_HALF_EVEN)
							.multiply(new BigDecimal(privilegeTerm)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
					mldto.setPrivilegeName(privilegeName);

					mldto.setPrivilegeRange(privilegeRange);

					mldto.setPrivilegeTerm(privilegeTerm);
					mldto.setTqsy(tqsy);
					mldto.setJudeg(1);
				}
				BigDecimal zero = new BigDecimal(0);
				if (mldto.getJXother() != null && (mldto.getJXother().compareTo(zero) > 0)) {
					// 额外加息利率
					BigDecimal jx = mldto.getJXother();
					// 原产品预收本息
					BigDecimal ysbx = mldto.getYsbx();
					mldto.setJXother(jx);
					ysbx = ysbx.add(mldto.getJXinterest());
					mldto.setYsbx(ysbx);
				} else {
					mldto.setJXother(new BigDecimal(0));
				}
			}
			pageController.setData(hhtList);
			System.err.println("循环耗时" + (System.currentTimeMillis() - forart));
			System.err.println(hhtList);
			return pageController;
		} catch (Exception e) {
			logger.error("findMessage()", e.getMessage());
			return null;
		}
	}

	/**
	 * 环环投持有中和完成后所有详情
	 */
	@Override
	public List<HHThavingDetailsDTO> findHHhavingDetails(String cashPoolOID) {
		try {
			if (StringUtil.isEmpty(cashPoolOID)) {
				return null;
			}
			List<HHThavingDetailsDTO> dto = mylendMapper.findHHhavingDetails(cashPoolOID);
			return dto;
		} catch (Exception e) {
			logger.error("findHHhavingDetails(String cashPoolOID)", e.getMessage());
			return null;
		}

	}

	/**
	 * 环环头完成列表
	 */
	@Override
	public Map<String, Object> findHHTWCBymemberOID(String memberOID, Integer currentPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(memberOID)) {
				return null;
			}
			// 分页信息
			Pagination<MylendResultDTO> pageController = new Pagination<>();
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = this.mylendMapper.getWCRowCount(memberOID);
			pageController.setTotalNumber(rows);
			// 取得总页数，总页数=总记录数/总页数
			Integer totalPage = pageController.getTotalPage();
			// 设置每页显示5条
			pageController.setPageSize(12);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();

			// 已经完成的列表
			List<MylendDaoDTO> hhtList = mylendMapper.findHHTWCBymemberOID(memberOID, startIndex, pageSize);
			// 返回的list
			List<MylendResultDTO> resultList = new ArrayList<MylendResultDTO>();
			for (MylendDaoDTO mldto : hhtList) {
				MylendResultDTO result = new MylendResultDTO();
				result.setCashPoolOID(mldto.getCashPoolOID());
				result.setNhll(mldto.getNhll());
				result.setXmmc(mldto.getXmmc());
				result.setCjje(mldto.getCjje());
				result.setOID(mldto.getOID());
				result.setCjsjTime(mldto.getCjsjTime().substring(0, 10));
				result.setDqsj(mldto.getDqsj().substring(0, 10));
				result.setTimejssj(mldto.getTimejssj().substring(0, 10));
				// 根据businessOID查询环环头的信息
				// 根据businessOID查询环环头的信息
				String businessOID = mldto.getBusinessOID();
				List<PayReturnPlan> prp = payReturnPlanDao.findReturnPlanByMemberOIDAndState(businessOID, "agentPlan");
				if (prp == null || prp.size() == 0) {
					// 出借时间
					Date cjsj = DateUtils.getJustDate(mldto.getCjsjTime());
					String cjsjTime = DateUtils.formatJustDate(cjsj.getTime());
					result.setCjsjTime(cjsjTime);
					// 到期时间
					int zqs = Integer.parseInt(mldto.getZqs());

					String dqsjTiem;
					try {
						dqsjTiem = DateUtils.gainPlusAndReduceDay(cjsjTime, zqs, 0);
						result.setDqsj(dqsjTiem);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 结算时间
					Date jssj = DateUtils.getJustDate(mldto.getTimejssj());
					String jssjTiem = DateUtils.formatJustDate(jssj.getTime());
					result.setTimejssj(jssjTiem);
					result.setZqs(zqs);
				}
				// 替换个人中心利率显示 terry
				PayCashPool payCashPool = payCashPoolDao.findByOID(mldto.getCashPoolOID());
				BigDecimal zero = new BigDecimal(0);
				if (payCashPool.getJXother() != null && (payCashPool.getJXother().compareTo(zero) > 0)) {
					// 额外加息利率
					BigDecimal jx = payCashPool.getJXother();
					// 原产品预收本息
					BigDecimal ysbx = mldto.getYsbx();
					result.setJXother(jx);
					ysbx = ysbx.add(payCashPool.getJXinterest());
					result.setYsbx(ysbx);
				} else {
					result.setJXother(new BigDecimal(0));
				}
				resultList.add(result);
			}
			map.put("resultList", resultList);
			map.put("pageController", pageController);
			return map;
		} catch (Exception e) {
			logger.error("findHHTWCBymemberOID(String memberOID)", e.getMessage());
			return null;
		}

	}

	/**
	 * 用户持有头笔赚列表
	 */
	@Override
	public List<MylendResultDTO> findTBZHavingBymemberOID(String memberOID) {
		try {
			if (StringUtil.isEmpty(memberOID)) {
				return null;
			}
			List<MylendResultDTO> resultList = new ArrayList<MylendResultDTO>();
			// 查询持有中的头笔赚
			List<MyLentTBZDaoDTO> tbzdto = mylendMapper.findTBZhavingBymemberOID(memberOID);
			if (tbzdto.size() > 0) {
				for (MyLentTBZDaoDTO tbzDto : tbzdto) {
					MylendResultDTO resultDTO = new MylendResultDTO();
					// 判断新标还是债转标的
					if (StringUtil.isNotEmpty(tbzDto.getCond())) {
						// 判断非债转标的满未满标
						if ("1".equals(tbzDto.getHkzt())) {
							resultDTO.setJxsj("");
							resultDTO.setDqsj("");
							resultDTO.setXghkr("");
							resultDTO.setYsbx(new BigDecimal(1));

							resultDTO.setDOID(tbzDto.getdOID());
							resultDTO.setProductStatus(tbzDto.getHkzt());
							resultDTO.setNhll(tbzDto.getNhll());
							resultDTO.setCond(tbzDto.getCond());
							resultDTO.setFullscale(tbzDto.isFullscale());
							// 还款状态
							resultDTO.setHkzt(tbzDto.getHkzt());
							resultDTO.setCjsjTime(tbzDto.getCjsjDate());
							resultDTO.setXmmc(tbzDto.getBiddingName());
							// 绑定oid
							resultDTO.setOID(tbzDto.getBiOID());
							resultDTO.setDqsj(tbzDto.getDqsj());
							resultDTO.setXghkr(tbzDto.getXghkr());
						} else {
							// 满标时，要获取计息时间，根据新手投产品计算对应的预期收益以及下个回款日
							WebP2pNoviceBiddingRuning novice = WEBP2PNoviceBiddingRuningMapper
									.findByOID(tbzDto.getBiOID());
							String jxsj = DateUtils.formatJustDate(novice.getLoanDay().getTime());
							// *******计算下个回款日和到期时间******
							String xghkr = DateUtils.gainPlusAndDay(jxsj, 15, 0);
							String dqsj = xghkr;
							// ********计算预期收益*****************
							BigDecimal cjje = tbzDto.getCjje();
							BigDecimal xsll = (new BigDecimal(0.15).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							BigDecimal ynts = new BigDecimal(365);
							BigDecimal tll = xsll.divide(ynts, 8, BigDecimal.ROUND_HALF_EVEN);
							BigDecimal tlx = cjje.multiply(tll);
							BigDecimal ysbx = cjje
									.add(tlx.multiply(new BigDecimal(15)).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							// *********装填数据********************
							resultDTO.setDOID(tbzDto.getdOID());
							resultDTO.setYsbx(ysbx);
							resultDTO.setProductStatus(tbzDto.getHkzt());
							resultDTO.setNhll(tbzDto.getNhll());
							resultDTO.setXghkr(xghkr);
							resultDTO.setDqsj(dqsj);
							resultDTO.setCond(tbzDto.getCond());
							resultDTO.setCjje(cjje);
							// 还款状态
							resultDTO.setHkzt(tbzDto.getHkzt());
							resultDTO.setCjsjTime(tbzDto.getCjsjDate());
							resultDTO.setFullscale(tbzDto.isFullscale());
							resultDTO.setXmmc(tbzDto.getBiddingName());
							// 绑定oid
							resultDTO.setOID(tbzDto.getBiOID());
							resultDTO.setJxsj(jxsj);
							resultDTO.setDqsj(tbzDto.getDqsj());
							resultDTO.setXghkr(tbzDto.getXghkr());
						}
						resultDTO.setIfzz(0);

					} else {
						// 新手标为债转标时，借贷关系中的addDateTime为计息时间
						String jxsj = tbzDto.getCjsjDate();
						resultDTO.setJxsj(jxsj);
						resultDTO.setDOID(tbzDto.getdOID());
						resultDTO.setProductStatus(tbzDto.getHkzt());
						resultDTO.setNhll(tbzDto.getNhll());
						resultDTO.setCond(tbzDto.getCond());
						resultDTO.setFullscale(tbzDto.isFullscale());
						// 还款状态
						resultDTO.setHkzt(tbzDto.getHkzt());
						resultDTO.setXmmc(tbzDto.getBiddingName());
						resultDTO.setCjje(tbzDto.getCjje());
						resultDTO.setYsbx(tbzDto.getYsbx());
						resultDTO.setOID(tbzDto.getBiOID());
						resultDTO.setCjsjTime(tbzDto.getCjsjDate());
						resultDTO.setDqsj(tbzDto.getDqsj());
						resultDTO.setXghkr(tbzDto.getXghkr());
						// 根据memberOID在transferReturn中查询相应的出借时间
						PayTransferReturn transferReturn = payTransferReturnMapper.gainByOutMemberOID(memberOID);
						if (transferReturn != null) {
							// 出借时间
							String cjsjDate = transferReturn.getAddDateTimeStr();
							// 计算预期收益
							BigDecimal cjje = transferReturn.getMoney();
							BigDecimal xsll = (new BigDecimal(0.15).setScale(2, BigDecimal.ROUND_HALF_EVEN));
							BigDecimal ynts = new BigDecimal(365);
							BigDecimal tll = xsll.divide(ynts, 8, BigDecimal.ROUND_HALF_EVEN);
							BigDecimal tlx = cjje.multiply(tll);
							BigDecimal ysbx = cjje
									.add(tlx.multiply(new BigDecimal(15)).setScale(2, BigDecimal.ROUND_HALF_EVEN));

							// 替换显示时间
							resultDTO.setCjsjTime(cjsjDate);
							
							// 替换出借金额
							resultDTO.setCjje(cjje);
							// 装填预期收益
							resultDTO.setYsbx(ysbx);
							resultDTO.setIfzz(1);
							resultDTO.setOID(transferReturn.getOID());
						}
						
					}
					if ("1".equals(tbzDto.getHkzt())) {
						resultDTO.setHkzt("wmb");
					}
					if ("2".equals(tbzDto.getHkzt())) {
						resultDTO.setHkzt("zzhkz");
					}
					if ("4".equals(tbzDto.getHkzt())) {
						resultDTO.setHkzt("yq");
					}
					resultList.add(resultDTO);
				}
			} else {
				PayTransferReturn tbzdt = payTransferReturnMapper.gainByMemberOIDANDState(memberOID);
				if (tbzdt != null) {
					MylendResultDTO resultDTO = new MylendResultDTO();
					String condi = tbzdt.getBusinessOID();
					// 新手标OID
					resultDTO.setOID(condi);

					WebP2pNoviceBiddingRuning novice = WEBP2PNoviceBiddingRuningMapper.findByOID(condi);
					if (novice != null) {
						String xmmc = novice.getBiddingName();
						resultDTO.setXmmc(xmmc);
						resultDTO.setDOID(novice.getOID());
						resultDTO.setHkzt(novice.getProductStatus());
						if ("1".equals(novice.getProductStatus())) {
							resultDTO.setFullscale(true);
						} else {
							resultDTO.setFullscale(false);
						}
						resultDTO.setCjje(tbzdt.getMoney());
						resultDTO.setNhll(new BigDecimal(15));
						resultDTO.setCjsjTime(tbzdt.getAddDateTimeStr());
						resultDTO.setJxsj("");
						resultDTO.setDqsj("");
						resultDTO.setXghkr("");
						resultDTO.setYsbx(new BigDecimal(0));
						if ("1".equals(novice.getProductStatus())) {
							resultDTO.setHkzt("wmb");
						}
						if ("2".equals(novice.getProductStatus())) {
							resultDTO.setHkzt("zzhkz");
						}
						if ("4".equals(novice.getProductStatus())) {
							resultDTO.setHkzt("yq");
						}

					}
					resultList.add(resultDTO);
				}
			}
			return resultList;
		} catch (Exception e) {
			logger.error("findTBZHavingBymemberOID(String memberOID)", e.getMessage());
			return null;
		}

	}

	/**
	 * 头笔赚已完成
	 */
	@Override
	public List<MylendResultDTO> findTBZWCBymemberOID(String memberOID) {
		try {
			List<MylendResultDTO> resultList = new ArrayList<MylendResultDTO>();
			// 查询持有中的头笔赚
			List<MyLentTBZDaoDTO> tbzdto = mylendMapper.findTBZWCBymemberOID(memberOID);
			if (tbzdto.size() > 0) {
				for (MyLentTBZDaoDTO tbzDto : tbzdto) {
					MylendResultDTO resultDTO = new MylendResultDTO();
					resultDTO.setDOID(tbzDto.getdOID());
					resultDTO.setYsbx(tbzDto.getYsbx());
					resultDTO.setProductStatus(tbzDto.getHkzt());
					resultDTO.setNhll(tbzDto.getNhll());
					resultDTO.setXghkr(tbzDto.getXghkr());
					resultDTO.setCond(tbzDto.getCond());
					resultDTO.setCjje(tbzDto.getCjje());
					resultDTO.setCharge(tbzDto.getCharge());
					// 还款状态
					resultDTO.setHkzt(tbzDto.getHkzt());
					resultDTO.setCjsjTime(tbzDto.getCjsjDate().substring(0, 10));
					resultDTO.setXmmc(tbzDto.getBiddingName());
					resultDTO.setDqsj(tbzDto.getDqsj().substring(0, 10));
					resultDTO.setTimejssj(tbzDto.getJssj().substring(0, 10));
					// 绑定oid
					resultDTO.setOID(tbzDto.getBiOID());
					WebP2pNoviceBiddingRuning novice = WEBP2PNoviceBiddingRuningMapper.findByOID(tbzDto.getBiOID());

					String doid = novice.getDebitCreditOID();
					// 判断 是不是新手债转 terry
					if (StringUtil.isNotEmpty(doid)) {
						PayTransferReturn tbzdt = payTransferReturnMapper
								.gainTransferReturnByDebitCreditNewOIDAndState(tbzDto.getdOID(), "finish");
						if (tbzdt != null) {
							resultDTO.setDOID(tbzdt.getOID());
						}
						// doid 不为null 表示为债转标,查用户出借时间,通过标的id和用户id --terry
						PayTransferReturn tbzdt2 = payTransferReturnMapper
								.gainTransferReturnByDebitCreditOIDAndOutMemberOID(doid, memberOID);

						if (tbzdt2 != null) {
							resultDTO.setDOID(tbzdt2.getOID());
						}

					} else {
						String jxsj = tbzDto.getLoanDay().substring(0, 10);
						String dqsj = DateUtils.gainPlusAndDay(jxsj, 15, 0);
						resultDTO.setDqsj(dqsj);
					}

					resultList.add(resultDTO);
				}

			}
			return resultList;
		} catch (Exception e) {
			logger.error("findTBZWCBymemberOID(String memberOID)", e.getMessage());
			return null;
		}
	}

	/**
	 * 点点投持有列表
	 */
	@Override
	public Map<String, Object> findDDTBymemberOID(String memberOID, Integer currentPage) {
		List<MylendResultDTO> resultList = new ArrayList<MylendResultDTO>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (StringUtil.isNotEmpty(memberOID)) {
				// 分页信息
				Pagination<MylendResultDTO> pageController = new Pagination<>();
				if (currentPage != null) {
					pageController.setCurrentPage(currentPage);
				}
				/** 获取总数 */
				int rows = this.mylendMapper.getddtRowCount(memberOID);
				pageController.setTotalNumber(rows);
				// 取得总页数，总页数=总记录数/总页数
				Integer totalPage = pageController.getTotalPage();
				// 设置每页显示5条
				pageController.setPageSize(12);
				// 起始位置
				Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
				// 每页条数
				Integer pageSize = pageController.getPageSize();
				// 点点投持有列表
				List<MylendDDTDaoDTO> ddtdtoList = mylendMapper.findDDThavingBymemberOID(memberOID, startIndex,
						pageSize);

				for (MylendDDTDaoDTO ddtdto : ddtdtoList) {
					MylendResultDTO resultDTO = new MylendResultDTO();
					resultDTO.setDOID(ddtdto.getDOID());
					resultDTO.setFullscale(ddtdto.isFullscale());
					String cjsjTime = ddtdto.getCjsjTime().substring(0, 10);
					resultDTO.setCjsjTime(cjsjTime);
					resultDTO.setYsbx(ddtdto.getYsbx());
					resultDTO.setNhll(ddtdto.getNhll());
					resultDTO.setXghkr(ddtdto.getXghkr());
					resultDTO.setCjje(ddtdto.getCjje());
					resultDTO.setHkzt(ddtdto.getHkzt());
					resultDTO.setCreditType(ddtdto.getCreditType());
					resultDTO.setXmmc(ddtdto.getXmmc());
					resultDTO.setOID(ddtdto.getBiOID());
					resultDTO.setJxsj(ddtdto.getJxsj().substring(0, 10));
					resultDTO.setTqOID(ddtdto.getTqOID());
					resultDTO.setProductType(ddtdto.getProductType());
					resultDTO.setZqs(ddtdto.getZqs());
					// businessRowNO=1 syqs=3 没有返回这俩字段
					String xghkr = "";
					String dqsj = "";
					if (StringUtil.isNotEmpty(ddtdto.getXghkr())) {
						xghkr = ddtdto.getXghkr().substring(0, 10);
						dqsj = ddtdto.getDqsj().substring(0, 10);
					}
					resultDTO.setCjsjTime(cjsjTime);
					resultDTO.setXghkr(xghkr);
					resultDTO.setDqsj(dqsj);
					if (ddtdto.getHkzt().equals("4")) {
						resultDTO.setHkzt("yq");
					} else {
						resultDTO.setHkzt("zzhkz");
					}

					// 特权
					if (StringUtil.isNotEmpty(ddtdto.getTqOID())) {

						WebP2pNormalBiddingRuning normal = WEBP2PNormalBiddingRuningMapper.findByOID(ddtdto.getBiOID());
						// 特权
						List<PayPrivilegeProductMapping> tqTemp = payPrivilegeProductMappingMapper
								.findByOID(normal.getProductRateInfoID());
						if (tqTemp.size() > 0) {
							for (PayPrivilegeProductMapping product : tqTemp) {
								String tqOID = ddtdto.getTqOID();
								if (tqOID.equals(product.getPrivilegeOID())) {
									PayPrivilege tqInfo = PAY_PrivilegeMapper.findByOID(tqOID);
									String privilegeName = tqInfo.getPrivilegeName();
									BigDecimal lowAmount = tqInfo.getLowAmount();
									BigDecimal privilegeRange = tqInfo.getPrivilegeRange();
									int privilegeTerm = tqInfo.getPrivilegeTerm();
									String privilegeType = tqInfo.getPrivilegeType();
									int rule = 0;
									String ruleIntroduce = "";
									if (tqInfo.getRule() != null) {
										rule = (int) tqInfo.getRule();
									}
									if (StringUtil.isNotEmpty(tqInfo.getRuleIntroduce())) {
										ruleIntroduce = tqInfo.getRuleIntroduce();
									}
									List<PayPrivilegeMemberMapping> pmm = payPrivilegeMemberMappingMapper
											.findByCondition(tqOID, memberOID);
									if (pmm.size() == 0) {
										resultDTO.setJudeg(0);
									} else {
										for (PayPrivilegeMemberMapping ite : pmm) {
											BigDecimal tqsy = new BigDecimal(0);
											Date enddate = DateUtils.getDate(ite.getEndDate().getTime());
											String jsDate = DateUtils.formatDate(enddate, "");
											Date startdate = DateUtils.getDate(ite.getStartDate().getTime());
											String ksDate = DateUtils.formatDate(enddate, "");
											tqsy = tqsy.add(ddtdto.getCjje());
											BigDecimal privilegeRange_new = privilegeRange.divide(new BigDecimal(100));
											// 特权收益
											tqsy = tqsy.multiply(privilegeRange_new)
													.divide(new BigDecimal("365"), 8, BigDecimal.ROUND_HALF_EVEN)
													.multiply(new BigDecimal(privilegeTerm))
													.setScale(2, BigDecimal.ROUND_HALF_EVEN);
											resultDTO.setTqsy(tqsy);
											resultDTO.setPrivilegeName(privilegeName);
											resultDTO.setPrivilegeTerm(privilegeTerm);
											resultDTO.setPrivilegeRange(privilegeRange);
											resultDTO.setPrivilegeType(privilegeType);
											resultDTO.setLowAmount(lowAmount);
											resultDTO.setRule(rule);
											resultDTO.setRuleIntroduce(ruleIntroduce);
											resultDTO.setJudeg(1);
										}
									}
								}
							}
						}
					}
					resultList.add(resultDTO);
				}
				map.put("ddtList", resultList);
				map.put("pageController", pageController);
			}
			return map;
		} catch (Exception e) {
			logger.error("findTBZWCBymemberOID(String memberOID)", e.getMessage());
			return null;
		}
	}

	/**
	 * 点点投完成列表
	 */
	@Override
	public Map<String, Object> findDDTWCBymemberOID(String memberOID, Integer currentPage) {
		try {
			if (StringUtil.isEmpty(memberOID)) {
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 分页信息
			Pagination<MylendResultDTO> pageController = new Pagination<>();
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = this.mylendMapper.getddtWCRowCount(memberOID);
			pageController.setTotalNumber(rows);
			// 取得总页数，总页数=总记录数/总页数
			Integer totalPage = pageController.getTotalPage();
			// 设置每页显示5条
			pageController.setPageSize(12);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();
			List<MylendDDTDaoDTO> ddtWclist = mylendMapper.findDDTWCBymemberOID(memberOID, startIndex, pageSize);
			if (ddtWclist.size() > 0) {
				for (MylendDDTDaoDTO ddtwc : ddtWclist) {
					int tatalTerm = ddtwc.getTatalTerm();
					PayDebitCredit debitCredit = payDebitCreditMapper.findByDebitCreditOID(ddtwc.getDOID());

					// dqsj
					Timestamp s3 = new Timestamp(debitCredit.getUpdDateTime().getTime());
					String dqsj1 = s3.toString();
					String dqsj = dqsj1.substring(0, 10);
					ddtwc.setCjsjTime(ddtwc.getCjsjTime().substring(0, 10));
					ddtwc.setDqsj(dqsj);
				}
			}
			map.put("ddtWclist", ddtWclist);
			map.put("pageController", pageController);
			return map;
		} catch (Exception e) {
			logger.error("findTBZWCBymemberOID(String memberOID)", e.getMessage());
			return null;
		}
	}

	/**
	 * 债权转让可转让列表
	 * 
	 * @param memberOID
	 * @param currentPage
	 * @return
	 */
	@Override
	public Map<String, Object> findZQKZBymemberOID(String memberOID, Integer currentPage) {
		try {
			if (StringUtil.isEmpty(memberOID)) {
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 分页信息
			Pagination<MylendZQZRDTO> pageController = new Pagination<>();
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = this.mylendMapper.getZQZRRowCount(memberOID);
			pageController.setTotalNumber(rows);
			// 取得总页数，总页数=总记录数/总页数
			Integer totalPage = pageController.getTotalPage();
			// 设置每页显示5条
			pageController.setPageSize(12);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();

			List<MylendZQZRDTO> zqzqResult = mylendMapper.findZQZRByPageAndmemberOID(memberOID, startIndex, pageSize);
			if (zqzqResult.size() > 0) {
				for (MylendZQZRDTO zqzq : zqzqResult) {
					zqzq.setCjsjDate(zqzq.getCjsjDate().substring(0, 10));
					zqzq.setCjsjTime(zqzq.getCjsjTime().substring(0, 10));
					zqzq.setDqsj(zqzq.getDqsj().substring(0, 10));

				}
			}
			map.put("pageController", pageController);
			map.put("zqzqResult", zqzqResult);
			return map;
		} catch (Exception e) {
			logger.error("findZQZRBymemberOID(String memberOID, Integer currentPage)", e.getMessage());
			return null;
		}
	}

	/**
	 * 债权转让已完成列表findByZZNew
	 */
	@Override
	public Map<String, Object> findZQWCBymemberOID(String memberOID, Integer currentPage) {
		try {
			if (StringUtil.isEmpty(memberOID)) {
				return null;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			// 分页信息
			Pagination<MylendZQZRDTO> pageController = new Pagination<>();
			if (currentPage != null) {
				pageController.setCurrentPage(currentPage);
			}
			/** 获取总数 */
			int rows = WEBP2PDebtTransferApplyMapper.getZQJLRowCount(memberOID);
			pageController.setTotalNumber(rows);
			// 取得总页数，总页数=总记录数/总页数
			Integer totalPage = pageController.getTotalPage();
			// 设置每页显示5条
			pageController.setPageSize(5);
			// 起始位置
			Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
			// 每页条数
			Integer pageSize = pageController.getPageSize();

			List<MylendZQWCDaoDTO> zqzqWCResult = WEBP2PDebtTransferApplyMapper.findByMemberAndstatus(memberOID,
					startIndex, pageSize);
			if (zqzqWCResult.size() > 0) {
				for (MylendZQWCDaoDTO zqzq : zqzqWCResult) {
					zqzq.setCjsj(zqzq.getCjsj().substring(0, 10));
					zqzq.setZrsj(zqzq.getZrsj().substring(0, 10));
				}
			}

			map.put("pageController", pageController);
			map.put("zqzqResult", zqzqWCResult);
			return map;
		} catch (Exception e) {
			logger.error("findZQWCBymemberOID(String memberOID, Integer currentPage)", e.getMessage());
			return null;
		}
	}

	/**
	 * 点击申请获得申请的信息展示
	 */
	@Override
	public MylendZQZRDTO findZQSQBymemberOIDAndPaydebitOID(String memberOID, String PaydebitOID, String businessOID) {
		try {
			List<MylendZQZRDTO> zqzqList = mylendMapper.findZQZRBymemberOID(memberOID);
			MylendZQZRDTO zqzqResult = new MylendZQZRDTO();
			if (zqzqList.size() > 0) {
				for (MylendZQZRDTO zqzq : zqzqList) {
					if (zqzq.getPaydebitOID().equals(PaydebitOID) && zqzq.getBusinessOID().equals(businessOID)) {
						zqzq.setCjsjDate(zqzq.getCjsjDate().substring(0, 10));
						zqzq.setCjsjTime(zqzq.getCjsjTime().substring(0, 10));
						zqzq.setDqsj(zqzq.getDqsj().substring(0, 10));
						zqzqResult = zqzq;
					}
				}
			}
			if (zqzqResult != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(new Date());
				// 审核通过日期减去出借日期获得持有期数
				int qs = DateUtils.gainMonthNum(zqzqResult.getCjsjDate(), date);
				// 出借日期加上 持有期数后的日期
				String qsrq = DateUtils.gainPlusAndReduceDay(zqzqResult.getCjsjDate(), qs, 0);
				// 出借日期加期数后的日期与审核通过的日期进行比较
				int compare = DateUtils.compare_date2(qsrq, date);

				// 审核通过日期减去出借日期加期数后日期，得到零天数
				int n = DateUtils.daysBetween(qsrq, date);
				// 如果出借日期加期后的日期大于审核通过日期，对期数减1，重新计算出借日期加期数日期和天数
				if (compare == 1) {
					qs = qs - 1;
					qsrq = DateUtils.gainPlusAndReduceDay(zqzqResult.getCjsjDate(), qs, 0);
					n = DateUtils.daysBetween(qsrq, date);
				}
				// 年利率(期)
				BigDecimal nll = zqzqResult.getNhll();
				nll = nll.divide(new BigDecimal(1200), 8, BigDecimal.ROUND_HALF_EVEN);
				// 日利率(天)
				BigDecimal tll = zqzqResult.getNhll();
				tll = tll.divide(new BigDecimal(36500), 8, BigDecimal.ROUND_HALF_EVEN);
				// 本金
				BigDecimal bj = zqzqResult.getCjje();
				// 期数利息
				BigDecimal lxq = new BigDecimal(0);
				lxq = lxq.add(nll.multiply(new BigDecimal(qs)).multiply(bj));
				// 天数利息
				BigDecimal lxt = new BigDecimal(0);
				if (n > 0) {
					lxt = lxt.add(tll.multiply(new BigDecimal(n)).multiply(bj));
				}

				WebP2pPackageBiddingMainRuning mr = WEBP2PPackageBiddingMainRuningMapper
						.findByOID(zqzqResult.getBusinessOID());
				WebP2pProductRateInfo productInfo = WEBP2P_ProductRateInfoMapper.findByOID(mr.getProductRateInfoID());
				BigDecimal shlv = (BigDecimal) productInfo.getHhtXStqsh();
				// 赎回手续费
				BigDecimal shsxf = bj.multiply(shlv);
				int zqs = (int) zqzqResult.getProductTerm();

				// 债转本息
				BigDecimal zzbx = new BigDecimal(0.00);
				zzbx = zzbx.add(bj.add(lxt).add(lxq).subtract(shsxf));
				// 债转利息
				BigDecimal zzlx = new BigDecimal(0.00);
				zzlx = zzlx.add(lxt.add(lxq));
				// 剩余期数
				int syqs = zqs - qs;
				// 预收本息
				BigDecimal jg = zqzqResult.getYsbx();
				// 对应转让价格
				zqzqResult.setZrsybx(zzbx.setScale(2, BigDecimal.ROUND_HALF_UP));
				// 转让本金
				zqzqResult.setZrsybj(bj);
				// 出借总期数
				zqzqResult.setTatalTerm(zqs);
				// 转让的垫付利息
				zqzqResult.setZrsylx(zzlx.setScale(2, BigDecimal.ROUND_HALF_UP));
				// 转让手续费
				zqzqResult.setZrsxf(shsxf.setScale(2, BigDecimal.ROUND_HALF_UP));
				// 剩余期数
				zqzqResult.setXssyqs(syqs);
				// 对应查到的预收本息
				zqzqResult.setJg(jg.setScale(2, BigDecimal.ROUND_HALF_UP));
				// 赎回利率
				zqzqResult.setShlv(shlv.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_EVEN));
			}
			return zqzqResult;
		} catch (Exception e) {
			logger.error("findZQSQBymemberOIDAndPaydebitOID(String memberOID, String PaydebitOID,String businessOID)",
					e.getMessage());
			return null;
		}
	}

	/**
	 * 确认债权转让申请
	 */
	@Override
	public String ZQZRBoxsave(MylendZQZRDTO mylendZQZRDTO) {
		if (mylendZQZRDTO != null) {
			List<WebP2pDebtTransferApply> deocsize = WEBP2PDebtTransferApplyMapper
					.findBydebitCreditOID(mylendZQZRDTO.getPaydebitOID());
			if (deocsize.size() > 0) {
				return "该笔已申请过了，请不要重复申请";
			} else {
				WebP2pDebtTransferApply transfer = new WebP2pDebtTransferApply();
				transfer.setOID(StringUtil.getUUID());
				transfer.setDebitCreditOID(mylendZQZRDTO.getPaydebitOID());
				transfer.setBusinessOID(mylendZQZRDTO.getBusinessOID());
				transfer.setMemberOID(mylendZQZRDTO.getMemberOID());
				transfer.setXmmc(mylendZQZRDTO.getXmmc());
				transfer.setCjje(mylendZQZRDTO.getCjje());
				transfer.setNhll(mylendZQZRDTO.getNhll());
				transfer.setCjsj(mylendZQZRDTO.getCjsjDate());
				transfer.setSyqs(mylendZQZRDTO.getXssyqs());
				transfer.setZqs(mylendZQZRDTO.getTatalTerm());
				transfer.setBdjg(mylendZQZRDTO.getZrsybx());
				transfer.setYsbx(mylendZQZRDTO.getJg());
				transfer.setYsbj(mylendZQZRDTO.getZrsybj());
				transfer.setYslx(mylendZQZRDTO.getZrsylx());
				transfer.setApplyTime(new Date());
				transfer.setIsAgree("0");
				transfer.setSxf(mylendZQZRDTO.getZrsxf() + "");
				transfer.setProducttype(mylendZQZRDTO.getBiddingType());
				// int
				// rows=WEBP2PDebtTransferApplyMapper.saveDebtTransferApply(transfer);
				int rows = WEBP2PDebtTransferApplyMapper.saveDebtTransferApply(transfer);
				if (rows == 1) {
					return rows + "";
				}
			}
		}
		return null;
	}

}
