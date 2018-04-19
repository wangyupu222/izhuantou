package com.izhuantou.service.impl.lend;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.lend.AuditInfo;
import com.izhuantou.damain.webp2p.WebP2pBiddingExamine;
import com.izhuantou.damain.webp2p.WebP2pCarLoanCenterInfo;
import com.izhuantou.damain.webp2p.WebP2pDebtTransferBiddingPool;
import com.izhuantou.damain.webp2p.WebP2pNormalBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pNoviceBiddingRuning;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainContentRuning;
import com.izhuantou.dao.lend.AuditInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pBiddingExamineMapper;
import com.izhuantou.dao.webp2p.WebP2pCarLoanCenterInfoMapper;
import com.izhuantou.dao.webp2p.WebP2pDebtTransferBiddingPoolMapper;
import com.izhuantou.dao.webp2p.WebP2pNormalBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pNoviceBiddingRuningMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainContentRuningMapper;
import com.izhuantou.service.api.lend.AuditInfoService;
import com.izhuantou.service.impl.BaseServiceImpl;

/**
 * 风控信息审核
 * 
 * @author Administrator
 *
 */
@Service("auditInfoService")
public class AuditInfoServiceImpl extends BaseServiceImpl<AuditInfo> implements AuditInfoService {
    private static final Logger logger = LoggerFactory.getLogger(AuditInfoServiceImpl.class);
    @Autowired
    private AuditInfoMapper auditInfoMapper;

    @Autowired
    private WebP2pNoviceBiddingRuningMapper noviceBiddingRuningMapper;

    @Autowired
    private WebP2pBiddingExamineMapper biddingExamineMapper;

    @Autowired
    private WebP2pPackageBiddingMainContentRuningMapper packageBiddingMainContentRuningMapper;

    @Autowired
    private WebP2pNormalBiddingRuningMapper normalBiddingRuningMapper;

    @Autowired
    private WebP2pDebtTransferBiddingPoolMapper debtTransferBiddingPoolMapper;

    @Autowired
    private WebP2pCarLoanCenterInfoMapper carLoanCenterInfoMapper;

    /** 头笔赚 */
    @Override
    public List<AuditInfo> findByCondition(String OID) {
	WebP2pNoviceBiddingRuning noviceBiddingRuning = new WebP2pNoviceBiddingRuning();
	WebP2pBiddingExamine biddingExamine = new WebP2pBiddingExamine();
	try {
	    noviceBiddingRuning = noviceBiddingRuningMapper.findByOID(OID);
	    biddingExamine = biddingExamineMapper.findByloanNumber(noviceBiddingRuning.getLoanNumber());
	    List<AuditInfo> list = auditInfoMapper.findByMainOID(biddingExamine.getXdmainOID());
	    return list;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByCondition()", e.getMessage());
	    return null;

	}

    }

    /** 环环投 */
    @Override
    public List<AuditInfo> findByConditionHHT(String OID) {
	WebP2pPackageBiddingMainContentRuning packageBiddingMainContentRuning = new WebP2pPackageBiddingMainContentRuning();

	WebP2pBiddingExamine biddingExamine = new WebP2pBiddingExamine();
	try {
	    packageBiddingMainContentRuning = packageBiddingMainContentRuningMapper.findByOID(OID);
	    biddingExamine = biddingExamineMapper.findByloanNumber(packageBiddingMainContentRuning.getLoanNumber());
	    List<AuditInfo> list = auditInfoMapper.findByMainOID(biddingExamine.getXdmainOID());
	    return list;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByCondition()", e.getMessage());
	    return null;

	}
    }

    /** 环环投车抵 */
    @Override
    public List<AuditInfo> findByConditionHHTCD(String OID) {
	WebP2pPackageBiddingMainContentRuning packageBiddingMainContentRuning = new WebP2pPackageBiddingMainContentRuning();

	WebP2pBiddingExamine biddingExamine = new WebP2pBiddingExamine();
	WebP2pCarLoanCenterInfo carLoanCenterInfo = new WebP2pCarLoanCenterInfo();
	try {

	    packageBiddingMainContentRuning = packageBiddingMainContentRuningMapper.findByOID(OID);
	    carLoanCenterInfo = carLoanCenterInfoMapper
		    .findByloanNumber(packageBiddingMainContentRuning.getLoanNumber());

	    List<AuditInfo> list = auditInfoMapper.findByMainOID(carLoanCenterInfo.getOID());
	    return list;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByCondition()", e.getMessage());
	    return null;

	}
    }

    /** 点点投风控审核 */
    @Override
    public List<AuditInfo> findByConditionDDT(String OID) {

	WebP2pNormalBiddingRuning normalBiddingRuning = new WebP2pNormalBiddingRuning();

	try {
	    normalBiddingRuning = normalBiddingRuningMapper.findByOID(OID);

	    String xdmainOID = normalBiddingRuning.getHxLoanCenterInfo();
	    List<AuditInfo> list = auditInfoMapper.findByMainOID(xdmainOID);
	    return list;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByCondition()", e.getMessage());
	    return null;

	}

    }

    /** 转转投风控审核 */
    @Override
    public List<AuditInfo> findByConditionZZT(String OID) {

	WebP2pDebtTransferBiddingPool debtTransferBiddingPool = new WebP2pDebtTransferBiddingPool();
	WebP2pBiddingExamine biddingExamine = new WebP2pBiddingExamine();
	try {
	    debtTransferBiddingPool = debtTransferBiddingPoolMapper.findByOID(OID);
	    biddingExamine = biddingExamineMapper.findByloanNumber(debtTransferBiddingPool.getLoanNumber());
	    String xdmainOID = biddingExamine.getXdmainOID();
	    List<AuditInfo> list = auditInfoMapper.findByMainOID(xdmainOID);
	    return list;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByCondition()", e.getMessage());
	    return null;

	}

    }

}
