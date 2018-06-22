package com.izhuantou.admin.controller.contentManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.admin.ims.annotation.SystemControllerLog;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.p2p.P2pCooperativePartner;
import com.izhuantou.damain.p2p.P2pFlink;
import com.izhuantou.damain.p2p.P2pPageBanner;
import com.izhuantou.damain.vo.CooperativePartnerDTO;
import com.izhuantou.damain.vo.FlinkDTO;
import com.izhuantou.service.api.contentManagement.BottomLinkService;
import com.izhuantou.service.api.contentManagement.CooperativePartnerService;

@Controller
@RequestMapping("links")
public class LinksController {
	@Autowired
	private BottomLinkService bottomLinkService;
	@Autowired
	private CooperativePartnerService cooperativePartnerService;
	/**
     * 获取底部链接信息
     * @return
     */
	@SystemControllerLog(description = "获取底部链接信息")
    @RequestMapping(value = "/getbottomlinklist")
    @ResponseBody
    public OpResult getBottomLinkList() {
		List<FlinkDTO> bottomLinkList = bottomLinkService.findBottomLink();
		if (bottomLinkList == null) {
		    return OpResult.getFailedResult("暂无内容");
		}
		return OpResult.getSuccessResult(bottomLinkList);
    }
    
    /**
	 * 添加一条底部链接
	 * @param flinkDTO 底部链接DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "添加一条底部链接")
	@RequestMapping(value = "/addbottomlink")
	@ResponseBody
	public OpResult addBottomLink(FlinkDTO flinkDTO){
		//必须参数非空判断
		if(StringUtil.isNotEmpty(flinkDTO.getName()) && StringUtil.isNotEmpty(flinkDTO.getLinkUrl())
				&& StringUtil.isNotEmpty(flinkDTO.getParentOID())){
			String num = bottomLinkService.addBottomLink(flinkDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("添加失败");
	}
	
	/**
	 * 删除一条底部链接
	 * @param OID 底部链接oid
	 * @return
	 */
	@SystemControllerLog(description = "删除一条底部链接")
	@RequestMapping(value = "/deletebottomlink")
	@ResponseBody
	public OpResult deleteBottomLink(String OID){
		if(StringUtil.isNotEmpty(OID)){
			String num = bottomLinkService.deleteBottomLink(OID);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("删除失败");
	}
	
	/**
	 * 修改一条底部链接
	 * @param flinkDTO 底部链接DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "修改一条底部链接")
	@RequestMapping(value = "/updatebottomlink")
	@ResponseBody
	public OpResult updateBottomLink(FlinkDTO flinkDTO){
		if(StringUtil.isNotEmpty(flinkDTO.getOID())){
			String num = bottomLinkService.updateBottomLink(flinkDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("修改失败");
	}
	
	/**
     * 获取合作伙伴信息列表
     * @return
     */
	@SystemControllerLog(description = "获取合作伙伴信息列表")
    @RequestMapping(value = "/getcooperativepartnerlist")
    @ResponseBody
    public OpResult getCooperativePartnerList() {
		List<CooperativePartnerDTO> cooperativePartnerList = cooperativePartnerService.findCooperativePartner();
		if (cooperativePartnerList == null) {
		    return OpResult.getFailedResult("没有图片了");
		}
		return OpResult.getSuccessResult(cooperativePartnerList);
    }
    
    /**
	 * 添加一条合作伙伴
	 * @param cooperativePartnerDTO 合作伙伴DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "添加一条合作伙伴")
	@RequestMapping(value = "/addcooperativepartner")
	@ResponseBody
	public OpResult addCooperativePartner(CooperativePartnerDTO cooperativePartnerDTO){
		if(StringUtil.isNotEmpty(cooperativePartnerDTO.getName()) && StringUtil.isNotEmpty(cooperativePartnerDTO.getLinkUrl())
				&& StringUtil.isNotEmpty(cooperativePartnerDTO.getPicimgOID()) && StringUtil.isNotEmpty(cooperativePartnerDTO.getParentOID())){
			String num = cooperativePartnerService.addCooperativePartner(cooperativePartnerDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("添加失败");
	}
	
	/**
	 * 删除一条合作伙伴
	 * @param OID 合作伙伴oid
	 * @return
	 */
	@SystemControllerLog(description = "删除一条合作伙伴")
	@RequestMapping(value = "/deletecooperativepartner")
	@ResponseBody
	public OpResult deleteCooperativePartner(String OID){
		if(StringUtil.isNotEmpty(OID)){
			String num = cooperativePartnerService.deleteCooperativePartner(OID);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("删除失败");
	}
	
	/**
	 * 修改一条合作伙伴
	 * @param cooperativePartnerDTO 合作伙伴DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "修改一条合作伙伴")
	@RequestMapping(value = "/updatecooperativepartner")
	@ResponseBody
	public OpResult updateCooperativePartner(CooperativePartnerDTO cooperativePartnerDTO){
		if(StringUtil.isNotEmpty(cooperativePartnerDTO.getOID())){
			String num = cooperativePartnerService.updateCooperativePartner(cooperativePartnerDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("修改失败");
	}
}
