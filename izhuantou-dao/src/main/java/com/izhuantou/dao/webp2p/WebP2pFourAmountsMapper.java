package com.izhuantou.dao.webp2p;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.webp2p.WebP2pFourAmounts;

/**
 * 首页平台四个数据显示实体类
 * 
 * @author yangbosen
 *
 */
public interface WebP2pFourAmountsMapper extends Mapper<WebP2pFourAmounts> {
    /**
     * 查找首页交易额，获取收益，注册用户数
     * 
     * @return
     */
    public WebP2pFourAmounts findFourNum();

}
