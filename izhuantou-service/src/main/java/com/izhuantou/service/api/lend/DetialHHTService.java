package com.izhuantou.service.api.lend;

import java.util.List;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.DetialHHT;
import com.izhuantou.damain.lend.HHTLendRecord;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;
import com.izhuantou.service.api.BaseService;

public interface DetialHHTService extends BaseService<WebP2pPackageBiddingMainRuning> {

    public DetialHHT findByCondition(String OID, String memberOID);

    /** 环环投出借记录 */
    public Pagination<HHTLendRecord> findByCondition(Integer page, String OID);

    public List<HHTLendRecord> findProductList(String OID);
}
