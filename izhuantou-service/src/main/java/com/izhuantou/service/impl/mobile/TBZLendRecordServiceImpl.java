package com.izhuantou.service.impl.mobile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.damain.lend.LendRecord;
import com.izhuantou.damain.mobile.detial.MobileLendRecord;
import com.izhuantou.dao.mobile.TBZLendRecordMapper;
import com.izhuantou.service.api.mobile.TBZLendRecordService;

@Service("TBZLendRecordService")
public class TBZLendRecordServiceImpl implements TBZLendRecordService {

    @Autowired
    private TBZLendRecordMapper lendRecordMapper;

    @Override
    public List<MobileLendRecord> TBZList(String OID) {
	try {
	    List<LendRecord> list = new ArrayList<>();
	    List<MobileLendRecord> resultList = new ArrayList<>();
	    list = lendRecordMapper.findList(OID);
	    for (LendRecord mobileLendRecord : list) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		MobileLendRecord mr = new MobileLendRecord();
		mr.setMoney(mobileLendRecord.getMoney().toString());
		mr.setNameCN(mobileLendRecord.getNameCN());
		String memberPhone = mobileLendRecord.getName();
		memberPhone = memberPhone.replaceAll("(\\d{3})\\d{5}(\\d{3})", "$1*****$2");
		mr.setName(memberPhone);
		mr.setPrincipalMoney(mobileLendRecord.getPrincipalMoney());
		mr.setAddDateTime(formatter.format(mobileLendRecord.getAddDateTime()));
		resultList.add(mr);
	    }

	    return resultList;
	} catch (Exception e) {
	    return null;
	}

    }

}
