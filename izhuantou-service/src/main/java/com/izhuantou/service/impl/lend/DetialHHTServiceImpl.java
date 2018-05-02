package com.izhuantou.service.impl.lend;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.DetialHHT;
import com.izhuantou.damain.lend.DisplayHHT;
import com.izhuantou.damain.lend.HHTLendRecord;
import com.izhuantou.damain.lend.HHTMidTable;
import com.izhuantou.damain.pay.PayCustomer;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.lend.DisplayHHTMapper;
import com.izhuantou.dao.lend.HHTLendRecordMapper;
import com.izhuantou.dao.lend.HHTMidTableMapper;
import com.izhuantou.dao.pay.PayCustomerMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.service.api.lend.DetialHHTService;
import com.izhuantou.service.impl.BaseServiceImpl;

/**
 * 环环投详情，出借记录
 * 
 * @author Administrator
 *
 */
@Service("detialHHTService")
public class DetialHHTServiceImpl extends BaseServiceImpl<WebP2pPackageBiddingMainRuning> implements DetialHHTService {
    private static final Logger logger = LoggerFactory.getLogger(AuditInfoServiceImpl.class);
    @Autowired
    private WebP2pPackageBiddingMainRuningMapper packageBiddingMainRuningMapper;

    @Autowired
    private WebP2pProductRateInfoMapper productRateInfoMapper;

    @Autowired
    private HHTMidTableMapper hhtMidTableMapper;

    @Autowired
    private HHTLendRecordMapper hhtLendRecordMapper;

    @Autowired
    private PayCustomerMapper customerMapper;

    @Autowired
    private DisplayHHTMapper hhtMapper;

    @Override
    public DetialHHT findByCondition(String OID, String memberOID) {
	WebP2pPackageBiddingMainRuning packageBiddingMainRuning = new WebP2pPackageBiddingMainRuning();
	WebP2pProductRateInfo productRateInfo = new WebP2pProductRateInfo();
	DetialHHT detialHHT = new DetialHHT();
	PayCustomer customer = new PayCustomer();
	DisplayHHT displayHHT = new DisplayHHT();
	try {
	    packageBiddingMainRuning = packageBiddingMainRuningMapper.findByOID(OID);
	    detialHHT.setXmmc(packageBiddingMainRuning.getPackageName());// 项目名称
	    productRateInfo = productRateInfoMapper.findByOID(packageBiddingMainRuning.getProductRateInfoID());
	    BigDecimal b1 = new BigDecimal(100);
	    BigDecimal nhll = productRateInfo.getYearRate().multiply(b1);// 年化利率
	    // 防止返回前台数据为空页面出错增加初始值复制
	    if (nhll != null) {
		detialHHT.setNhll(nhll.setScale(2, BigDecimal.ROUND_HALF_UP));
	    } else {
		detialHHT.setNhll(new BigDecimal("0.00"));
	    }

	    BigDecimal allnhll = new BigDecimal(0.00);
	    BigDecimal zero = new BigDecimal(0);
	    // 判断加息
	    if (null != packageBiddingMainRuning.getJXother()
		    && ((BigDecimal) packageBiddingMainRuning.getJXother()).compareTo(zero) > 0) {
		detialHHT.setJX("true");
		allnhll = nhll.add((BigDecimal) packageBiddingMainRuning.getJXother());
		detialHHT.setJxother(packageBiddingMainRuning.getJXother());
		if (allnhll != null) {
		    detialHHT.setAllnhll(allnhll.setScale(2, BigDecimal.ROUND_HALF_UP));
		} else {
		    detialHHT.setAllnhll(new BigDecimal("0.00"));
		}

	    } else {
		detialHHT.setJX("false");
		detialHHT.setJxother(new BigDecimal(0.00));
	    }

	    detialHHT.setProductStatus(packageBiddingMainRuning.getProductStatus());// 产品状态
	    detialHHT.setCjqx(productRateInfo.getProductTerm());// 出借期限
	    displayHHT = hhtMapper.findByOID(OID);

	    // 剩余可投
	    detialHHT.setKtje(displayHHT.getSykt());
	    if (memberOID != null) {
		customer = customerMapper.findByMemberOID(memberOID);
		// 可用余额
		if (customer != null) {
		    detialHHT.setUseMoney(customer.getUseMoney());
		} else {
		    // 表示该用户未实名认证
		    detialHHT.setUseMoney(new BigDecimal(0.00));
		}
	    }

	    // 还款方式
	    if (productRateInfo.getRepaymentType().equals("OPI")) {
		detialHHT.setRepaymentType("一次性还本付息");
	    } else if (productRateInfo.getRepaymentType().equals("EPEI")) {
		detialHHT.setRepaymentType("等本等息");
	    } else {
		detialHHT.setRepaymentType("按月返息到期还本");
	    }
	    if (packageBiddingMainRuning.getDbxeAmount() != null) {
		detialHHT.setDbxeAmount(packageBiddingMainRuning.getDbxeAmount());
	    } else {
		detialHHT.setDbxeAmount(new BigDecimal(0.00));
	    }
	    if (packageBiddingMainRuning.getDbxeFlag() != null) {
		detialHHT.setDbxeFlag(new BigDecimal(packageBiddingMainRuning.getDbxeFlag()));
	    } else {
		detialHHT.setDbxeFlag(new BigDecimal(0.00));
	    }

	    List<HHTMidTable> list = hhtMidTableMapper.findByOID(OID);
	    detialHHT.setZbsl(list.size());// 分散于多少标
	    return detialHHT;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByCondition()", e.getMessage());
	    return null;
	}

    }

    /** 环环投出借记录 */
    @Override
    public Pagination<HHTLendRecord> findByCondition(Integer page, String OID) {
	Pagination<HHTLendRecord> pageController = new Pagination<>();
	List<HHTLendRecord> lr = new ArrayList<>();
	try {
	    pageController.setCurrentPage(page);
	    Integer totalNumber = (int) hhtLendRecordMapper.findCount(OID);
	    pageController.setTotalNumber(totalNumber);

	    if (pageController.getCurrentPage() != null) {

		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
		lr = hhtLendRecordMapper.findByOID(OID, StartPos, pageController.getPageSize());
		for (HHTLendRecord hhtLendRecord : lr) {
		    Date dateTime = hhtLendRecord.getAddDateTime();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    // 对时间进行格式化
		    hhtLendRecord.setSj(sdf.format(dateTime));
		    String memberPhone = hhtLendRecord.getName();
		    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		    hhtLendRecord.setName(memberPhone);
		}
	    } else {
		lr = hhtLendRecordMapper.findByOID(OID, 0, 10);
		for (HHTLendRecord hhtLendRecord : lr) {
		    Date dateTime = hhtLendRecord.getAddDateTime();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		    hhtLendRecord.setSj(sdf.format(dateTime));
		    String memberPhone = hhtLendRecord.getName();
		    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		    hhtLendRecord.setName(memberPhone);
		}
	    }
	    pageController.setData(lr);
	    return pageController;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByCondition()", e.getMessage());
	    return null;
	}

    }

    @Override
    public List<HHTLendRecord> findProductList(String OID) {
	List<HHTLendRecord> lr = new ArrayList<>();
	try {
	    lr = hhtLendRecordMapper.findList(OID);
	    for (HHTLendRecord hhtLendRecord : lr) {
		Date dateTime = hhtLendRecord.getAddDateTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		hhtLendRecord.setSj(sdf.format(dateTime));
		String memberPhone = hhtLendRecord.getName();
		memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		hhtLendRecord.setName(memberPhone);
	    }
	    return lr;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByCondition()", e.getMessage());
	    return null;
	}

    }

}
