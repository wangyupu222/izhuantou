package com.izhuantou.service.impl.mobile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.mobile.ActivityBiao;
import com.izhuantou.damain.mobile.ActivityModel;
import com.izhuantou.damain.pay.PayCashPoolOperation;
import com.izhuantou.damain.webp2p.WebP2pProductRateInfo;
import com.izhuantou.dao.mobile.ActvictyBiaoMapper;
import com.izhuantou.dao.pay.PayCashPoolOperationMapper;
import com.izhuantou.dao.webp2p.WebP2pProductRateInfoMapper;
import com.izhuantou.service.api.mobile.ActivityBiaoService;

@Service("activityBiaoService")
public class ActivityBiaoServiceImpl implements ActivityBiaoService {
    @Autowired
    private ActvictyBiaoMapper actvictyBiaoMapper;
    @Autowired
    private WebP2pProductRateInfoMapper productRateInfoMapper;
    @Autowired
    private PayCashPoolOperationMapper cashPoolOperationMapper;

    @Override
    public List<ActivityModel> findActBiao() {
	List<ActivityBiao> listBiao = new ArrayList<>();
	WebP2pProductRateInfo ProductRateInfo = new WebP2pProductRateInfo();
	List<PayCashPoolOperation> cashPoolOperation = new ArrayList<>();
	List<ActivityModel> model = new ArrayList<>();
	try {
	    listBiao = actvictyBiaoMapper.findActBiao();
	    for (ActivityBiao activityBiao : listBiao) {

		ProductRateInfo = productRateInfoMapper.findByOID(activityBiao.getProductRateInfoID());

		cashPoolOperation = cashPoolOperationMapper.findByBusinessOID(activityBiao.getOID());
		// 已投人数
		int r = cashPoolOperation.size();

		BigDecimal b2 = new BigDecimal(100);
		ActivityModel re = new ActivityModel();
		re.setProductStatus(activityBiao.getProductStatus());
		re.setOID(activityBiao.getOID());
		re.setXmmc(activityBiao.getPackageName());
		BigDecimal yearRate = ProductRateInfo.getYearRate();

		re.setNhll(yearRate.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP));

		Double productTerm = (Double) ProductRateInfo.getProductTerm();
		int cjqx = productTerm.intValue();
		re.setCjqx(cjqx);

		if (ProductRateInfo.getRepaymentType().equals("OPI")) {
		    re.setHkfs("一次性还本付息");
		} else if (ProductRateInfo.getRepaymentType().equals("EPEI")) {
		    re.setHkfs("等本等息");
		} else {
		    re.setHkfs("按月返息到期还本");
		}

		re.setXmze(activityBiao.getApplyAmount());
		re.setYtrs(r);
		re.setQtje(activityBiao.getStartBidAmount());
		re.setKtje(activityBiao.getSykt());

		model.add(re);

	    }
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return null;
    }

}
