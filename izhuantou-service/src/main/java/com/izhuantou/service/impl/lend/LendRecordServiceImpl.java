package com.izhuantou.service.impl.lend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.LendRecord;
import com.izhuantou.dao.lend.LendRecordMapper;
import com.izhuantou.dao.lend.LendRecordZZTMapper;
import com.izhuantou.service.api.lend.LendRecordService;

/**
 * HHT出借记录
 * 
 * @author jasen
 *
 */
@Service("lendRecordService")
public class LendRecordServiceImpl implements LendRecordService {
    private static final Logger logger = LoggerFactory.getLogger(LendRecordServiceImpl.class);
    @Autowired
    private LendRecordMapper lendrecordMapper;

    @Autowired
    private LendRecordZZTMapper lendRecordZZTMapper;

    @Override
    public Pagination<LendRecord> findByBusinessOID(Integer page, String OID) {
	Pagination<LendRecord> pageController = new Pagination<>();
	List<LendRecord> lr = new ArrayList<>();
	try {

	    String businessOID = OID;
	    // 测试用
	    pageController.setCurrentPage(page);

	    Integer totalNumber = (int) lendrecordMapper.findCount(businessOID);
	    pageController.setTotalNumber(totalNumber);

	    if (pageController.getCurrentPage() != null) {
		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
		lr = lendrecordMapper.findByBusinessOID(businessOID, StartPos, pageController.getPageSize());
		for (LendRecord lendRecord : lr) {
		    Date dateTime = lendRecord.getAddDateTime();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    lendRecord.setSj(sdf.format(dateTime));
		    String memberPhone = lendRecord.getMobile();
		    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		    lendRecord.setMobile(memberPhone);

		}

	    } else {
		lr = lendrecordMapper.findByBusinessOID(businessOID, 0, 10);
		for (LendRecord lendRecord : lr) {
		    Date dateTime = lendRecord.getAddDateTime();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    lendRecord.setSj(sdf.format(dateTime));
		    String memberPhone = lendRecord.getMobile();
		    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		    lendRecord.setMobile(memberPhone);

		}

	    }
	    pageController.setData(lr);

	    return pageController;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByBusinessOID()", e.getMessage());
	    return null;
	}

    }

    /** 转转投出借记录 */
    @Override
    public Pagination<LendRecord> findByZZTBusinessOID(Integer page, String OID) {

	Pagination<LendRecord> pageController = new Pagination<>();
	List<LendRecord> lr = new ArrayList<>();
	try {

	    String businessOID = OID;
	    // 测试用
	    pageController.setCurrentPage(page);

	    Integer totalNumber = (int) lendRecordZZTMapper.findZZTCount(businessOID);
	    pageController.setTotalNumber(totalNumber);

	    if (pageController.getCurrentPage() != null) {
		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
		lr = lendRecordZZTMapper.findByZZTBusinessOID(businessOID, StartPos, pageController.getPageSize());
		for (LendRecord lendRecord : lr) {
		    Date dateTime = lendRecord.getAddDateTime();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    lendRecord.setSj(sdf.format(dateTime));
		    String memberPhone = lendRecord.getName();
		    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		    lendRecord.setName(memberPhone);

		}

	    } else {
		lr = lendRecordZZTMapper.findByZZTBusinessOID(businessOID, 0, 10);
		for (LendRecord lendRecord : lr) {
		    Date dateTime = lendRecord.getAddDateTime();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    lendRecord.setSj(sdf.format(dateTime));
		    String memberPhone = lendRecord.getName();
		    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		    lendRecord.setName(memberPhone);

		}

	    }
	    pageController.setData(lr);

	    return pageController;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByBusinessOID()", e.getMessage());
	    return null;
	}

    }

    /** 转转头list */
    @Override
    public List<LendRecord> findProductList(String OID) {
	List<LendRecord> lr = new ArrayList<>();
	try {

	    lr = lendRecordZZTMapper.findList(OID);
	    for (LendRecord lendRecord : lr) {
		Date dateTime = lendRecord.getAddDateTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		lendRecord.setSj(sdf.format(dateTime));
		String memberPhone = lendRecord.getName();
		memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		lendRecord.setName(memberPhone);

	    }

	    return lr;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByBusinessOID()", e.getMessage());
	    return null;
	}
    }

    // zc
    @Override
    public Pagination<LendRecord> findByBusinessZCOID(Integer page, String OID) {

	Pagination<LendRecord> pageController = new Pagination<>();
	List<LendRecord> lr = new ArrayList<>();
	try {

	    String businessOID = OID;
	    // 测试用
	    pageController.setCurrentPage(page);

	    Integer totalNumber = (int) lendrecordMapper.findCountZC(businessOID);
	    pageController.setTotalNumber(totalNumber);

	    if (pageController.getCurrentPage() != null) {
		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
		lr = lendrecordMapper.findByBusinessZCOID(businessOID, StartPos, pageController.getPageSize());
		for (LendRecord lendRecord : lr) {
		    Date dateTime = lendRecord.getAddDateTime();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    lendRecord.setSj(sdf.format(dateTime));
		    String memberPhone = lendRecord.getName();
		    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		    lendRecord.setMobile(memberPhone);

		}

	    } else {
		lr = lendrecordMapper.findByBusinessOID(businessOID, 0, 10);
		for (LendRecord lendRecord : lr) {
		    Date dateTime = lendRecord.getAddDateTime();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    lendRecord.setSj(sdf.format(dateTime));
		    String memberPhone = lendRecord.getName();
		    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		    lendRecord.setMobile(memberPhone);

		}

	    }
	    pageController.setData(lr);

	    return pageController;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByBusinessOID()", e.getMessage());
	    return null;
	}

    }

    @Override
    public List<LendRecord> findProductListTBZ(String OID) {
	try {
	    List<LendRecord> lr = new ArrayList<>();
	    lr = lendrecordMapper.findList(OID);
	    for (LendRecord lendRecord : lr) {
		Date dateTime = lendRecord.getAddDateTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		lendRecord.setSj(sdf.format(dateTime));
		String memberPhone = lendRecord.getName();
		memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		lendRecord.setMobile(memberPhone);
	    }
	    return lr;
	} catch (Exception e) {
	    return null;
	}

    }

}
