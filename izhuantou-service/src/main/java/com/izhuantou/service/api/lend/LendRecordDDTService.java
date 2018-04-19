package com.izhuantou.service.api.lend;

import java.util.List;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.LendRecordDDT;
import com.izhuantou.service.api.BaseService;

public interface LendRecordDDTService extends BaseService<LendRecordDDT> {
    /** 点点投出借记录 */
    public Pagination<LendRecordDDT> findByBusinessOID(Integer page, String OID);

    public List<LendRecordDDT> findProductList(String OID);

}
