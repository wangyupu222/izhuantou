package com.izhuantou.dao.mobile;

import java.util.List;

import com.izhuantou.damain.lend.LendRecord;

public interface TBZLendRecordMapper {
    // 原标头笔赚
    public List<LendRecord> findList(String businessOID);
}
