package com.izhuantou.dao.contentManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.p2p.P2pPageBanner;
/**
 * 内容管理-轮播广告
 * 
 * @author liyang
 * @version 1.0
 */
public interface ContentAdvertisementMapper {
    
	/**
     * 查询出所有的轮播广告
     * @return
     */
    public List<P2pPageBanner> findAdvertisement(@Param("state") String state,@Param("name") String name);
    
    /**
     * 根据OID查询轮播广告内容
     * @param OID 轮播广告oid
     * @return
     */
    public P2pPageBanner findAdvertisementByOID(String OID);
    
    /**
     * 添加一条轮播广告
     * @param P2pPageBanner 轮播广告bean
     * @return 
     */
    public int addAdvertisement(P2pPageBanner p2pPageBanner);
    
    /**
     * 修改一条轮播广告
     * @param P2pPageBanner 轮播广告bean
     * @return 
     */
    public int updateAdvertisement(P2pPageBanner p2pPageBanner);
    
    /**
     * 删除一条轮播广告
     * @param OID 轮播广告oid
     * @return
     */
    public int deleteAdvertisement(String OID);
}
