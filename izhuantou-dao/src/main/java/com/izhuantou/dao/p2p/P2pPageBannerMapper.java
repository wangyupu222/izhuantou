package com.izhuantou.dao.p2p;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pPageBanner;

/**
 * Banner资源信息表
 * 
 * @author dear
 * @version 1.0
 */
public interface P2pPageBannerMapper extends Mapper<P2pPageBanner> {
    /**
     * 查询出所有的轮播图
     * 
     * @return
     */
    public List<P2pPageBanner> findBunnerPage();

}
