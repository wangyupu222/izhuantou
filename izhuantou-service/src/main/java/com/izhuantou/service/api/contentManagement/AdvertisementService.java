package com.izhuantou.service.api.contentManagement;

import java.util.List;

import com.izhuantou.damain.p2p.P2pPageBanner;
import com.izhuantou.damain.p2p.P2pPageproblems;
import com.izhuantou.damain.vo.BannerDTO;

public interface AdvertisementService {
	
	/*************************************************Web首页轮播图************************************************************/
	/**
	 * 获取轮播广告信息列表
	 * @return
	 */
	public List<BannerDTO> findAdvertisement(String state,String name);
	
	/**
     * 添加一条轮播广告
     * @param bannerDTO 轮播广告DTOBean
     * @return 
     */
    public String addAdvertisement(BannerDTO bannerDTO);
    
    /**
     * 删除一条轮播广告
     * @param OID 轮播广告oid
     * @return 
     */
    public String deleteAdvertisement(String OID);
    
    /**
     * 修改一条轮播广告
     * @param bannerDTO 轮播广告DTOBean
     * @return 
     */
    public String updateAdvertisement(BannerDTO bannerDTO);
    
    /*************************************************App首页轮播图************************************************************/
    /**
	 * 获取app轮播广告信息列表
	 * @return
	 */
	public List<BannerDTO> findAppAdvertisement(String state,String name);
	
	/**
     * 添加一条app轮播广告
     * @param bannerDTO 轮播广告DTOBean
     * @return 
     */
    public String addAppAdvertisement(BannerDTO bannerDTO);
    
    /**
     * 删除一条app轮播广告
     * @param OID 轮播广告oid
     * @return 
     */
    public String deleteAppAdvertisement(String OID);
    
    /**
     * 修改一条app轮播广告
     * @param bannerDTO 轮播广告DTOBean
     * @return 
     */
    public String updateAppAdvertisement(BannerDTO bannerDTO);
}
