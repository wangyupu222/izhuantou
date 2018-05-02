package com.izhuantou.dao.webp2p;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.vo.ActivityNewPrivilegesDTO;
import com.izhuantou.damain.webp2p.WebP2pPackageBiddingMainRuning;

/**
 * 在投中团标产品表
 * 
 * @author yangbosen
 * @version 1.0
 */
public interface WebP2pPackageBiddingMainRuningMapper extends Mapper<WebP2pPackageBiddingMainRuning> {

    public WebP2pPackageBiddingMainRuning findByOID(String OID);

    /**
     * 更新团表主表
     * 
     * @param mainRuning
     * @return
     */
    public int updatePackageBiddingMainRuning(WebP2pPackageBiddingMainRuning mainRuning);

    /**
     * 在投中团标产品表
     * 
     * @return
     */
    public List<WebP2pPackageBiddingMainRuning> findAll();

    /**
     * 为新手注册赠送加息券页面查询环环标的的OID和期数
     * 
     */
    public List<ActivityNewPrivilegesDTO> findOIDAndTerm();

}
