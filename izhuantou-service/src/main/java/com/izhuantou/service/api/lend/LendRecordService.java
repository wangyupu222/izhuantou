package com.izhuantou.service.api.lend;

import java.util.List;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.LendRecord;

public interface LendRecordService {
    /** TBZ原标出借记录 */
    public Pagination<LendRecord> findByBusinessOID(Integer page, String OID);

    /** TBZ债转出借记录 */
    public Pagination<LendRecord> findByBusinessZCOID(Integer page, String OID);

    /** 转转投出借记录 */
    public Pagination<LendRecord> findByZZTBusinessOID(Integer page, String OID);

    // 转转列表
    public List<LendRecord> findProductList(String OID);

    /** 头笔赚列表用于移动 */
    public List<LendRecord> findProductListTBZ(String OID);

}
