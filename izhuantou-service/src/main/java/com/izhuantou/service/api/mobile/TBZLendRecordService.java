package com.izhuantou.service.api.mobile;

import java.util.List;

import com.izhuantou.damain.mobile.detial.MobileLendRecord;

public interface TBZLendRecordService {
    // 头笔赚原标列表
    public List<MobileLendRecord> TBZList(String OID);
}
