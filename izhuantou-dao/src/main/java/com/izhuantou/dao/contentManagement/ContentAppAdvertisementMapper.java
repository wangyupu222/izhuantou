package com.izhuantou.dao.contentManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pPageAppBanner;
import com.izhuantou.damain.p2p.P2pPageBanner;
/**
 * 内容管理-App轮播广告
 * 
 * @author liyang
 * @version 1.0
 */
public interface ContentAppAdvertisementMapper {
    
	/**
     * 查询出所有的app轮播广告
     * @return
     */
    public List<P2pPageAppBanner> findAppAdvertisement(@Param("state") String state,@Param("name") String name);
    
    /**
     * 根据OID查询app轮播广告内容
     * @param OID app轮播广告oid
     * @return
     */
    public P2pPageAppBanner findAppAdvertisementByOID(String OID);
    
    /**
     * 添加一条app轮播广告
     * @param P2pPageAppBanner app轮播广告bean
     * @return 
     */
    public int addAppAdvertisement(P2pPageAppBanner p2pPageAppBanner);
    
    /**
     * 修改一条app轮播广告
     * @param P2pPageAppBanner app轮播广告bean
     * @return 
     */
    public int updateAppAdvertisement(P2pPageAppBanner p2pPageAppBanner);
    
    /**
     * 删除一条app轮播广告
     * @param OID app轮播广告oid
     * @return
     */
    public int deleteAppAdvertisement(String OID);
}
