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
import com.izhuantou.damain.lend.LendRecordDDT;
import com.izhuantou.dao.lend.LendRecordDDTMapper;
import com.izhuantou.service.api.lend.LendRecordDDTService;
import com.izhuantou.service.impl.BaseServiceImpl;

/**
 * DDT出借记录
 * 
 * @author jasen
 *
 */
@Service("lendRecordDDTService")
public class LendRecordDDTServiceImpl extends BaseServiceImpl<LendRecordDDT> implements LendRecordDDTService {
    private static final Logger logger = LoggerFactory.getLogger(LendRecordDDTServiceImpl.class);
    @Autowired
    private LendRecordDDTMapper lendrecordDDTMapper;

    @Override
    public Pagination<LendRecordDDT> findByBusinessOID(Integer page, String OID) {
	Pagination<LendRecordDDT> pageController = new Pagination<>();
	List<LendRecordDDT> lr = new ArrayList<>();
	try {

	    String businessOID = OID;
	    // 测试用
	    pageController.setCurrentPage(page);

	    Integer totalNumber = (int) lendrecordDDTMapper.findCount(businessOID);
	    pageController.setTotalNumber(totalNumber);

	    if (pageController.getCurrentPage() != null) {
		Integer StartPos = (pageController.getCurrentPage() - 1) * 10;
		lr = lendrecordDDTMapper.findByBusinessOID(businessOID, StartPos, pageController.getPageSize());
		for (LendRecordDDT lendRecordDDT : lr) {
		    Date dateTime = lendRecordDDT.getAddDateTime();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    lendRecordDDT.setSj(sdf.format(dateTime));
		    String memberPhone = lendRecordDDT.getName();
		    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		    lendRecordDDT.setName(memberPhone);

		}

	    } else {
		lr = lendrecordDDTMapper.findByBusinessOID(businessOID, 0, 10);
		for (LendRecordDDT lendRecordDDT : lr) {
		    Date dateTime = lendRecordDDT.getAddDateTime();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		    lendRecordDDT.setSj(sdf.format(dateTime));
		    String memberPhone = lendRecordDDT.getName();
		    memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		    lendRecordDDT.setName(memberPhone);

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
    public List<LendRecordDDT> findProductList(String OID) {

	List<LendRecordDDT> lr = new ArrayList<>();
	try {
	    lr = lendrecordDDTMapper.findList(OID);
	    for (LendRecordDDT lendRecordDDT : lr) {
		Date dateTime = lendRecordDDT.getAddDateTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		lendRecordDDT.setSj(sdf.format(dateTime));
		String memberPhone = lendRecordDDT.getName();
		memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		lendRecordDDT.setName(memberPhone);

	    }

	    return lr;
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("findByBusinessOID()", e.getMessage());
	    return null;
	}

    }

}
