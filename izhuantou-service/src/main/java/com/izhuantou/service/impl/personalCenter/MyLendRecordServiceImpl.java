package com.izhuantou.service.impl.personalCenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.damain.vo.MyLendRecord;
import com.izhuantou.damain.webp2p.WebP2pLoanProductRateInfo;
import com.izhuantou.dao.personalCenter.MyLoanMapper;
import com.izhuantou.dao.user.MemberMemberMapper;
import com.izhuantou.dao.webp2p.WebP2pLoanProductRateInfoMapper;
import com.izhuantou.service.api.personalCenter.MyLendRecordService;

/** 个人中心我的借款借款记录 */
@Service("myLendRecordService")
public class MyLendRecordServiceImpl implements MyLendRecordService {
    @Autowired
    MyLoanMapper myLendRecord;
    @Autowired
    MemberMemberMapper userDao;
    @Autowired
    WebP2pLoanProductRateInfoMapper productRateInfoMapper;

    @Override
    public Map MylendRecord(String memberOID) {
	try {
	    int space = 0;
	    Map<String, Object> map = new HashMap<>();
	    List<MyLendRecord> Lendlist = new ArrayList<>();
	    /** 查询借款记录记录1.普通,2.房抵,3.车抵 */
	    // 查找普通的借款记录
	    Lendlist = myLendRecord.findList(memberOID);
	    for (MyLendRecord myLendRecord : Lendlist) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (myLendRecord.getLoanDay() != null && myLendRecord.getNextDay() != null) {
		    // 时间判断 返回 1,-1,0
		    space = DateUtils.compare_date(sdf.format(myLendRecord.getLoanDay()),
			    sdf.format(myLendRecord.getNextDay()));
		} else {
		    space = 2;
		}

		if (myLendRecord.getLoanDay() != null) {
		    myLendRecord.setLoanSJ(sdf.format(myLendRecord.getLoanDay()));
		} else {
		    myLendRecord.setLoanSJ("");
		}
		if (myLendRecord.getNextDay() != null) {
		    myLendRecord.setNextSJ(sdf.format(myLendRecord.getNextDay()));
		} else {
		    myLendRecord.setNextSJ("");
		}

		myLendRecord.setSpace(space);

		// 获取期数算出结束时间
		if (myLendRecord.getLoanProductRateInfoID() != null) {
		    WebP2pLoanProductRateInfo rateInfo = new WebP2pLoanProductRateInfo();
		    rateInfo = productRateInfoMapper.findByOID(myLendRecord.getLoanProductRateInfoID());
		    if (rateInfo.getRepaymentType().equals("OPI")) {
			myLendRecord.setHkfs("一次性还本付息");
		    } else if (rateInfo.getRepaymentType().equals("EPEI")) {
			myLendRecord.setHkfs("等本等息");
		    } else {
			myLendRecord.setHkfs("按月返息到期还本");
		    }

		    double term = rateInfo.getTerm();
		    if (myLendRecord.getLoanSJ() != null && !"".equals(myLendRecord.getLoanSJ())) {
			String sj = DateUtils.gainPlusAndReduceDay(myLendRecord.getLoanSJ(), (int) term, 0);
			myLendRecord.setEndSJ(sj);
		    }

		}

	    }
	    String xyType = "1";
	    map.put("xyType", xyType);
	    map.put("data", Lendlist);

	    // xyType参数,用于向前台传递贷款产品协议类型 1:普通协议 2:房抵协议 3:车抵协议
	    if (Lendlist.size() == 0) {
		Lendlist = myLendRecord.findFDList(userDao.findUserByOID(memberOID).getName());
		for (MyLendRecord myLendRecord : Lendlist) {
		    myLendRecord.setLoanNumber(myLendRecord.getLoanNumber());
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    if (myLendRecord.getLoanDay() != null && myLendRecord.getNextDay() != null) {
			space = DateUtils.compare_date(sdf.format(myLendRecord.getLoanDay()),
				sdf.format(myLendRecord.getNextDay()));
		    } else {
			space = 2;
		    }

		    if (myLendRecord.getLoanDay() != null) {
			myLendRecord.setLoanSJ(sdf.format(myLendRecord.getLoanDay()));
		    } else {
			myLendRecord.setLoanSJ("");
		    }
		    if (myLendRecord.getNextDay() != null) {
			myLendRecord.setNextSJ(sdf.format(myLendRecord.getNextDay()));
		    } else {
			myLendRecord.setNextSJ("");
		    }
		    myLendRecord.setSpace(space);
		    // 获取期数算出结束时间
		    if (myLendRecord.getLoanProductRateInfoID() != null) {
			WebP2pLoanProductRateInfo rateInfo = new WebP2pLoanProductRateInfo();
			String s = myLendRecord.getLoanProductRateInfoID();
			rateInfo = productRateInfoMapper.findByOID(myLendRecord.getLoanProductRateInfoID());
			if (rateInfo.getRepaymentType().equals("OPI")) {
			    myLendRecord.setHkfs("一次性还本付息");
			} else if (rateInfo.getRepaymentType().equals("EPEI")) {
			    myLendRecord.setHkfs("等本等息");
			} else {
			    myLendRecord.setHkfs("按月返息到期还本");
			}
			double term = rateInfo.getTerm();

			if (myLendRecord.getLoanSJ() != null && !"".equals(myLendRecord.getLoanSJ())) {
			    String sj = DateUtils.gainPlusAndReduceDay(myLendRecord.getLoanSJ(), (int) term, 0);
			    myLendRecord.setEndSJ(sj);
			}

		    }

		}
		xyType = "2";
		map.put("xyType", xyType);
		map.put("data", Lendlist);
	    }

	    if (Lendlist == null || Lendlist.size() == 0) {
		Lendlist = myLendRecord.findCDList(userDao.findUserByOID(memberOID).getName());
		for (MyLendRecord myLendRecord : Lendlist) {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    if (myLendRecord.getLoanDay() != null && myLendRecord.getNextDay() != null) {
			space = DateUtils.compare_date(sdf.format(myLendRecord.getLoanDay()),
				sdf.format(myLendRecord.getNextDay()));
		    } else {
			space = 2;
		    }

		    if (myLendRecord.getLoanDay() != null) {
			myLendRecord.setLoanSJ(sdf.format(myLendRecord.getLoanDay()));
		    } else {
			myLendRecord.setLoanSJ("");
		    }
		    if (myLendRecord.getNextDay() != null) {
			myLendRecord.setNextSJ(sdf.format(myLendRecord.getNextDay()));
		    } else {
			myLendRecord.setNextSJ("");
		    }
		    myLendRecord.setSpace(space);

		    // 获取期数算出结束时间
		    if (myLendRecord.getLoanProductRateInfoID() != null) {
			WebP2pLoanProductRateInfo rateInfo = new WebP2pLoanProductRateInfo();
			String s = myLendRecord.getLoanProductRateInfoID();
			rateInfo = productRateInfoMapper.findByOID(myLendRecord.getLoanProductRateInfoID());
			if (rateInfo.getRepaymentType().equals("OPI")) {
			    myLendRecord.setHkfs("一次性还本付息");
			} else if (rateInfo.getRepaymentType().equals("EPEI")) {
			    myLendRecord.setHkfs("等本等息");
			} else {
			    myLendRecord.setHkfs("按月返息到期还本");
			}
			double term = rateInfo.getTerm();

			if (myLendRecord.getLoanSJ() != null && !"".equals(myLendRecord.getLoanSJ())) {
			    String sj = DateUtils.gainPlusAndReduceDay(myLendRecord.getLoanSJ(), (int) term, 0);
			    myLendRecord.setEndSJ(sj);
			}

		    }
		}
		xyType = "3";
		map.put("xyType", xyType);
		map.put("data", Lendlist);

	    }

	    return map;
	} catch (Exception e) {
	    return null;
	}
    }

}
