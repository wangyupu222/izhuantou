package com.izhuantou.admin.controller.contentManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.admin.ims.annotation.SystemControllerLog;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.p2p.P2pPageBanner;
import com.izhuantou.damain.vo.BannerDTO;
import com.izhuantou.service.api.contentManagement.AdvertisementService;

@Controller
@RequestMapping("advertisement")
public class AdvertisementController {
	@Autowired
	private AdvertisementService advertisementService;
	
	/*************************************************Web首页轮播图************************************************************/
	
	/**
     * 返回轮播广告信息
     * @state 状态 
     * @name 模糊搜索标题
     * @return
     */
	@SystemControllerLog(description = "返回轮播广告信息")
    @RequestMapping(value = "/getadvertisementlist")
    @ResponseBody
    public OpResult getAdvertisementList(String state,String name) {
		List<BannerDTO> advertisementList = advertisementService.findAdvertisement(state,name);
		if (advertisementList == null) {
		    return OpResult.getFailedResult("没有数据");
		}
		return OpResult.getSuccessResult(advertisementList);
    }
    
    /**
	 * 添加一条轮播广告
	 * @param bannerDTO 轮播广告DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "添加一条轮播广告")
	@RequestMapping(value = "/addadvertisement")
	@ResponseBody
	public OpResult addAdvertisement(BannerDTO bannerDTO){
		if(StringUtil.isNotEmpty(bannerDTO.getName()) && StringUtil.isNotEmpty(bannerDTO.getJumpurl())
				&& StringUtil.isNotEmpty(bannerDTO.getPicimgOID()) && StringUtil.isNotEmpty(bannerDTO.getParentOID())){
			String num = advertisementService.addAdvertisement(bannerDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("添加失败");
	}
	
	/**
	 * 删除一条轮播广告
	 * @param OID 轮播广告oid
	 * @return
	 */
	@SystemControllerLog(description = "删除一条轮播广告")
	@RequestMapping(value = "/deleteadvertisement")
	@ResponseBody
	public OpResult deleteAdvertisement(String OID){
		if(StringUtil.isNotEmpty(OID)){
			String num = advertisementService.deleteAdvertisement(OID);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("删除失败");
	}
	
	/**
	 * 修改一条轮播广告
	 * @param bannerDTO 轮播广告DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "修改一条轮播广告")
	@RequestMapping(value = "/updateadvertisement")
	@ResponseBody
	public OpResult updateAdvertisement(BannerDTO bannerDTO){
		if(StringUtil.isNotEmpty(bannerDTO.getOID())){
			String num = advertisementService.updateAdvertisement(bannerDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("修改失败");
	}
	
	/*************************************************App首页轮播图************************************************************/
	
	/**
     * 返回app轮播广告信息
     * @state 状态 
     * @name 模糊搜索标题
     * @return
     */
	@SystemControllerLog(description = "返回app轮播广告信息")
    @RequestMapping(value = "/getappadvertisementlist")
    @ResponseBody
    public OpResult getAppAdvertisementList(String state,String name) {
		List<BannerDTO> advertisementList = advertisementService.findAppAdvertisement(state,name);
		if (advertisementList == null) {
		    return OpResult.getFailedResult("没有数据");
		}
		return OpResult.getSuccessResult(advertisementList);
    }
    
    /**
	 * 添加一条app轮播广告
	 * @param bannerDTO app轮播广告DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "添加一条app轮播广告")
	@RequestMapping(value = "/addappadvertisement")
	@ResponseBody
	public OpResult addAppAdvertisement(BannerDTO bannerDTO){
		if(StringUtil.isNotEmpty(bannerDTO.getName()) && StringUtil.isNotEmpty(bannerDTO.getAppurl())
				&& StringUtil.isNotEmpty(bannerDTO.getPicimgOID()) && StringUtil.isNotEmpty(bannerDTO.getParentOID())){
			String num = advertisementService.addAppAdvertisement(bannerDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("添加失败");
	}
	
	/**
	 * 删除一条app轮播广告
	 * @param OID app轮播广告oid
	 * @return
	 */
	@SystemControllerLog(description = "删除一条app轮播广告")
	@RequestMapping(value = "/deleteappadvertisement")
	@ResponseBody
	public OpResult deleteAppAdvertisement(String OID){
		if(StringUtil.isNotEmpty(OID)){
			String num = advertisementService.deleteAppAdvertisement(OID);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("删除失败");
	}
	
	/**
	 * 修改一条app轮播广告
	 * @param bannerDTO app轮播广告DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "修改一条app轮播广告")
	@RequestMapping(value = "/updateappadvertisement")
	@ResponseBody
	public OpResult updateAppAdvertisement(BannerDTO bannerDTO){
		if(StringUtil.isNotEmpty(bannerDTO.getOID())){
			String num = advertisementService.updateAppAdvertisement(bannerDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("修改失败");
	}
}
