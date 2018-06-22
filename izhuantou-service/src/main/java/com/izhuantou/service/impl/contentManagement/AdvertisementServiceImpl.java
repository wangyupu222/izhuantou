package com.izhuantou.service.impl.contentManagement;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.p2p.FileInfo;
import com.izhuantou.damain.p2p.P2pPageAppBanner;
import com.izhuantou.damain.p2p.P2pPageBanner;
import com.izhuantou.damain.vo.BannerDTO;
import com.izhuantou.dao.contentManagement.ContentAdvertisementMapper;
import com.izhuantou.dao.contentManagement.ContentAppAdvertisementMapper;
import com.izhuantou.dao.p2p.FileInfoMapper;
import com.izhuantou.service.api.contentManagement.AdvertisementService;
import com.izhuantou.service.impl.BaseServiceImpl;
@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {
	private static final Logger logger = LoggerFactory.getLogger(AdvertisementServiceImpl.class);
	@Autowired
	private ContentAdvertisementMapper contentAdvertisementDao;
	@Autowired
	private ContentAppAdvertisementMapper contentAppAdvertisementDao;
	@Autowired
    private FileInfoMapper fileInfoDao;
	
	/*************************************************Web首页轮播图************************************************************/
	
	/**
	 * 获取轮播广告信息列表
	 * @return 
	 */
	@Override
	public List<BannerDTO> findAdvertisement(String state,String name) {
		List<BannerDTO> filepage = new ArrayList<BannerDTO>();
		try {
		    List<P2pPageBanner> p2ppage = contentAdvertisementDao.findAdvertisement(state,name);
		    if (p2ppage != null && p2ppage.size()>0) {
				for (P2pPageBanner bann : p2ppage) {
				    BannerDTO pa = new BannerDTO();
				    //FileInfo file = fileInfoDao.findPageByOID(bann.getPicimgOID()); 	// 图片的id
					pa.setJumpurl(bann.getJumpurl());           						// 广告跳转地址
					pa.setName(bann.getName());         	  			  				// 广告标题
					pa.setOID(bann.getOID());         		    						// 广告oid
					pa.setPicimgOID(bann.getPicimgOID());    							// 图片oid
					if(bann.getStartTime()!=null){
						pa.setStartTime(DateUtils.formatDate(bann.getStartTime(),""));	// 投放开始时间
					}
					if(bann.getEndTime()!=null){
						pa.setEndTime(DateUtils.formatDate(bann.getEndTime(),""));		// 投放结束时间
					}
					filepage.add(pa);
				}
				return filepage;
			    }
		    return null;
		} catch (Exception e) {
		    logger.error("findAdvertisement()" + e.getMessage());
		    return null;
		}
	}
	
	/**
     * 添加一条轮播广告
     * @param bannerDTO 轮播广告DTOBean
     * @return 
     */
	@Override
	public String addAdvertisement(BannerDTO bannerDTO) {
		try{
			//必要参数验证非空
			if(StringUtil.isNotEmpty(bannerDTO.getName()) && StringUtil.isNotEmpty(bannerDTO.getJumpurl())
					&& StringUtil.isNotEmpty(bannerDTO.getPicimgOID()) && StringUtil.isNotEmpty(bannerDTO.getParentOID())){
				P2pPageBanner p2pPageBanner = new P2pPageBanner();
				p2pPageBanner.setName(bannerDTO.getName());                                     //广告标题
				p2pPageBanner.setJumpurl(bannerDTO.getJumpurl());								//广告跳转地址
				p2pPageBanner.setPicimgOID(bannerDTO.getPicimgOID());							//图片oid
				p2pPageBanner.setParentOID(bannerDTO.getParentOID());							//类型oid
				p2pPageBanner.setStartTime(DateUtils.getDate(bannerDTO.getStartTime(), ""));    //投放开始时间
				p2pPageBanner.setEndTime(DateUtils.getDate(bannerDTO.getEndTime(), ""));		//投放结束时间
				String OID = StringUtil.getUUID();
				p2pPageBanner.setOID(OID);         												//id
				p2pPageBanner.setValid(true);      												//是否有效
				p2pPageBanner.setState(1); 														//状态：投放中
				p2pPageBanner.setRefresh(true);    												//是否更新
				p2pPageBanner.setVersion(0);       												//版本号
				Integer num = contentAdvertisementDao.addAdvertisement(p2pPageBanner);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("addAdvertisement()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 删除一条轮播广告
     * @param OID 轮播广告oid
     * @return
     */
	@Override
	public String deleteAdvertisement(String OID) {
		try{
			if(StringUtil.isNotEmpty(OID)){
				Integer num = contentAdvertisementDao.deleteAdvertisement(OID);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("deleteAdvertisement()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 修改一条轮播广告
     * @param bannerDTO 轮播广告DTObean
     * @return 
     */
	@Override
	public String updateAdvertisement(BannerDTO bannerDTO) {
		try{
			if(StringUtil.isNotEmpty(bannerDTO.getOID())&& StringUtil.isNotEmpty(bannerDTO.getParentOID())){
				//根据id查询原数据
				P2pPageBanner p2pPageBanner = contentAdvertisementDao.findAdvertisementByOID(bannerDTO.getOID());
				if(p2pPageBanner != null){
					//更改数据赋值
					if(bannerDTO.getName() != null){ 
						p2pPageBanner.setName(bannerDTO.getName());  									//标题名称
					}
					if(bannerDTO.getJumpurl() != null){
						p2pPageBanner.setJumpurl(bannerDTO.getJumpurl()); 								//跳转地址
					}
					if(bannerDTO.getPicimgOID() != null){
						p2pPageBanner.setPicimgOID(bannerDTO.getPicimgOID());  							//图片oid
					}
					if(bannerDTO.getParentOID() != null){
						p2pPageBanner.setParentOID(bannerDTO.getParentOID());  							//父级OID
					}
					if(bannerDTO.getState() != null){
						p2pPageBanner.setState(bannerDTO.getState());  									//投放状态
					}
					if(bannerDTO.getStartTime() != null){
						p2pPageBanner.setStartTime(DateUtils.getDate(bannerDTO.getStartTime(), "")); 	//投放开始时间
					}
					if(bannerDTO.getEndTime() != null){
						p2pPageBanner.setEndTime(DateUtils.getDate(bannerDTO.getEndTime(),""));      	//投放结束时间
					}
					//更新表
					Integer num = contentAdvertisementDao.updateAdvertisement(p2pPageBanner);
					return num.toString();
				}
			}
			return null;
		}catch(Exception e){
			logger.error("updateAdvertisement()", e.getMessage());
			return null;
		}
	}
	
	/*************************************************App首页轮播图************************************************************/
	
	/**
	 * 获取App轮播广告信息列表
	 * @return 
	 */
	@Override
	public List<BannerDTO> findAppAdvertisement(String state,String name) {
		List<BannerDTO> filepage = new ArrayList<BannerDTO>();
		try {
		    List<P2pPageAppBanner> p2ppage = contentAppAdvertisementDao.findAppAdvertisement(state,name);
		    if (p2ppage != null && p2ppage.size()>0) {
				for (P2pPageAppBanner appbann : p2ppage) {
				    BannerDTO pa = new BannerDTO();
				    //FileInfo file = fileInfoDao.findPageByOID(bann.getPicimgOID()); 		// 图片的id
					pa.setAppurl(appbann.getAppurl());           							// 广告跳转地址
					pa.setName(appbann.getName());         	  			  					// 广告标题
					pa.setOID(appbann.getOID());         		    						// 广告oid
					pa.setPicimgOID(appbann.getPicimgOID());    							// 图片oid
					if(appbann.getStartTime()!=null){
						pa.setStartTime(DateUtils.formatDate(appbann.getStartTime(),""));	// 投放开始时间
					}
					if(appbann.getEndTime()!=null){
						pa.setEndTime(DateUtils.formatDate(appbann.getEndTime(),""));		// 投放结束时间
					}
					filepage.add(pa);
				}
				return filepage;
			}
		    return null;
		} catch (Exception e) {
		    logger.error("findAppAdvertisement()" + e.getMessage());
		    return null;
		}
	}
	
	/**
     * 添加一条App轮播广告
     * @param bannerDTO App轮播广告DTOBean
     * @return 
     */
	@Override
	public String addAppAdvertisement(BannerDTO bannerDTO) {
		try{
			//必要参数验证非空
			if(StringUtil.isNotEmpty(bannerDTO.getName()) && StringUtil.isNotEmpty(bannerDTO.getAppurl())
					&& StringUtil.isNotEmpty(bannerDTO.getPicimgOID()) && StringUtil.isNotEmpty(bannerDTO.getParentOID())){
				P2pPageAppBanner p2pPageAppBanner = new P2pPageAppBanner();
				p2pPageAppBanner.setName(bannerDTO.getName());                                      //广告标题
				p2pPageAppBanner.setAppurl(bannerDTO.getAppurl());									//广告跳转地址
				p2pPageAppBanner.setPicimgOID(bannerDTO.getPicimgOID());							//图片oid
				p2pPageAppBanner.setParentOID(bannerDTO.getParentOID());							//类型oid
				p2pPageAppBanner.setStartTime(DateUtils.getDate(bannerDTO.getStartTime(), ""));     //投放开始时间
				p2pPageAppBanner.setEndTime(DateUtils.getDate(bannerDTO.getEndTime(), ""));			//投放结束时间
				String OID = StringUtil.getUUID();
				p2pPageAppBanner.setOID(OID);         												//id
				p2pPageAppBanner.setValid(true);      												//是否有效
				p2pPageAppBanner.setState(1); 														//状态：投放中
				p2pPageAppBanner.setRefresh(true);    												//是否更新
				p2pPageAppBanner.setVersion(0);       												//版本号
				Integer num = contentAppAdvertisementDao.addAppAdvertisement(p2pPageAppBanner);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("addAppAdvertisement()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 删除一条App轮播广告
     * @param OID App轮播广告oid
     * @return
     */
	@Override
	public String deleteAppAdvertisement(String OID) {
		try{
			if(StringUtil.isNotEmpty(OID)){
				Integer num = contentAppAdvertisementDao.deleteAppAdvertisement(OID);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("deleteAppAdvertisement()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 修改一条App轮播广告
     * @param bannerDTO App轮播广告DTObean
     * @return 
     */
	@Override
	public String updateAppAdvertisement(BannerDTO bannerDTO) {
		try{
			if(StringUtil.isNotEmpty(bannerDTO.getOID())&& StringUtil.isNotEmpty(bannerDTO.getParentOID())){
				//根据id查询原数据
				P2pPageAppBanner p2pPageAppBanner = contentAppAdvertisementDao.findAppAdvertisementByOID(bannerDTO.getOID());
				if(p2pPageAppBanner != null){
					//更改数据赋值
					if(bannerDTO.getName() != null){ 
						p2pPageAppBanner.setName(bannerDTO.getName());  									//标题名称
					}
					if(bannerDTO.getAppurl() != null){
						p2pPageAppBanner.setAppurl(bannerDTO.getAppurl()); 								//跳转地址
					}
					if(bannerDTO.getPicimgOID() != null){
						p2pPageAppBanner.setPicimgOID(bannerDTO.getPicimgOID());  							//图片oid
					}
					if(bannerDTO.getParentOID() != null){
						p2pPageAppBanner.setParentOID(bannerDTO.getParentOID());  							//父级OID
					}
					if(bannerDTO.getState() != null){
						p2pPageAppBanner.setState(bannerDTO.getState());  									//投放状态
					}
					if(bannerDTO.getStartTime() != null){
						p2pPageAppBanner.setStartTime(DateUtils.getDate(bannerDTO.getStartTime(), "")); 	//投放开始时间
					}
					if(bannerDTO.getEndTime() != null){
						p2pPageAppBanner.setEndTime(DateUtils.getDate(bannerDTO.getEndTime(),""));      	//投放结束时间
					}
					//更新表
					Integer num = contentAppAdvertisementDao.updateAppAdvertisement(p2pPageAppBanner);
					return num.toString();
				}
			}
			return null;
		}catch(Exception e){
			logger.error("updateAppAdvertisement()", e.getMessage());
			return null;
		}
	}
}
