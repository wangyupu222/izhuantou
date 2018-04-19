package com.izhuantou.service.api.p2p;

import com.izhuantou.damain.webp2p.WebP2pFourAmounts;
import com.izhuantou.service.api.BaseService;

/**
 * control调用的接口展示首页显示四个数据
 * 
 * @author Administrator
 *
 */
public interface FourNumService extends BaseService<WebP2pFourAmounts> {
    /**
     * 
     * @return WEBP2P_fourAmounts实体 里面有需要的首页展示四个数据
     */
    public WebP2pFourAmounts findFourNum();
}
