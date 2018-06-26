package com.izhuantou.admin.controller.contentManagement;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.izhuantou.admin.ims.annotation.SystemControllerLog;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.p2p.P2pMediaNews;
import com.izhuantou.damain.p2p.P2pNotice;
import com.izhuantou.damain.p2p.P2pPageproblems;
import com.izhuantou.damain.p2p.P2pToutiaoNews;
import com.izhuantou.damain.vo.PageNewsDTO;
import com.izhuantou.damain.vo.PageproblemsDTO;
import com.izhuantou.service.api.contentManagement.ArticleService;
/**
 * 文章相关Controller
 * @author liyang
 *
 */
@Controller
@RequestMapping("article")
public class articleController {
	@Autowired
	private ArticleService articleService;
	/*****************************************帮助中心**********************************************/
	/**
	 * 根据类型获取帮助中心问题列表
	 * @param currentPage 页数
	 * @param pageproblemsDTO 问题DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "根据类型获取帮助中心问题列表")
	@RequestMapping(value = "/getproblemlist")
	@ResponseBody
	public OpResult getProblemsList(Integer currentPage,PageproblemsDTO pageproblemsDTO){
		Map<String,Object> helpCentreMap = articleService.findProblems(currentPage,pageproblemsDTO);
		if(helpCentreMap == null){
			return OpResult.getFailedResult("暂无内容");
		}
		return OpResult.getSuccessResult(helpCentreMap);
	}
	
	/**
	 * 添加一条帮助中心问题
	 * @param P2pPageproblems 问题bean
	 * @return
	 */
	@SystemControllerLog(description = "添加一条帮助中心问题")
	@RequestMapping(value = "/addproblem")
	@ResponseBody
	public OpResult addHelpCentreProblem(PageproblemsDTO pageproblemsDTO){
		if(StringUtil.isNotEmpty(pageproblemsDTO.getName()) && StringUtil.isNotEmpty(pageproblemsDTO.getParentOID())
				&& StringUtil.isNotEmpty(pageproblemsDTO.getProblemsContent())){
			String num = articleService.addProblem(pageproblemsDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("添加失败");
	}
	
	/**
	 * 删除一条帮助中心问题
	 * @param OID 问题oid
	 * @return
	 */
	@SystemControllerLog(description = "删除一条帮助中心问题")
	@RequestMapping(value = "/deleteproblem")
	@ResponseBody
	public OpResult deleteHelpCentreProblem(String OID){
		if(StringUtil.isNotEmpty(OID)){
			String num = articleService.deleteProblem(OID);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("删除失败");
	}
	
	/**
	 * 修改一条帮助中心问题
	 * @param pageproblemsDTO 问题DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "修改一条帮助中心问题")
	@RequestMapping(value = "/updateproblem")
	@ResponseBody
	public OpResult updateHelpCentreProblem(PageproblemsDTO pageproblemsDTO){
		if(StringUtil.isNotEmpty(pageproblemsDTO.getOID())){
			String num = articleService.updateProblem(pageproblemsDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("修改失败");
	}
	
	/**
	 * 查询一条帮助中心问题
	 * @param OID 问题oid
	 * @return
	 */
	@SystemControllerLog(description = "查询一条帮助中心问题")
	@RequestMapping(value = "/findproblembyid")
	@ResponseBody
	public OpResult findHelpCentreProblemById(String OID){
		if(StringUtil.isNotEmpty(OID)){
			PageproblemsDTO pageproblemsDTO = articleService.findProblemById(OID);
			if(pageproblemsDTO != null){
				return OpResult.getSuccessResult(pageproblemsDTO);
			}
		}
		return OpResult.getFailedResult("查询失败");
	}
	
	
	/*****************************************关于我们**********************************************/
	
	/**
	 * 关于我们-平台公告信息列表 
	 * @param
	 * @return
	 */
	@SystemControllerLog(description = "关于我们-平台公告信息列表 ")
	@RequestMapping(value="getnoticelist")
	@ResponseBody
	public OpResult getNoticeList(Integer currentPage,PageNewsDTO pageNewsDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = articleService.findNotices(currentPage,pageNewsDTO);
		if (map == null) {
		    return OpResult.getFailedResult("暂无数据");
		}
		return OpResult.getSuccessResult(map);
	}
	
	/**
	 * 添加一条平台公告信息
	 * @param pageNewsDTO 平台公告DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "添加一条平台公告信息")
	@RequestMapping(value = "/addnotice")
	@ResponseBody
	public OpResult addNotice(PageNewsDTO pageNewsDTO){
		if(StringUtil.isNotEmpty(pageNewsDTO.getName()) && StringUtil.isNotEmpty(pageNewsDTO.getParentOID())
				&& StringUtil.isNotEmpty(pageNewsDTO.getMessages())){
			String num = articleService.addNotice(pageNewsDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("添加失败");
	}
	
	/**
	 * 删除一条平台公告信息
	 * @param OID 平台公告oid
	 * @return
	 */
	@SystemControllerLog(description = "删除一条平台公告信息")
	@RequestMapping(value = "/deletenotice")
	@ResponseBody
	public OpResult deleteNotice(String OID){
		if(StringUtil.isNotEmpty(OID)){
			String num = articleService.deleteNotice(OID);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("删除失败");
	}
	
	/**
	 * 修改一条平台公告信息
	 * @param pageNewsDTO 平台公告DTObean
	 * @return
	 */
	@SystemControllerLog(description = "修改一条平台公告信息")
	@RequestMapping(value = "/updatenotice")
	@ResponseBody
	public OpResult updateNotice(PageNewsDTO pageNewsDTO){
		if(StringUtil.isNotEmpty(pageNewsDTO.getOID())){
			String num = articleService.updateNotice(pageNewsDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("修改失败");
	}
	
	/**
	 * 根据oid获取一条平台公告信息
	 * @param OID  平台公告oid
	 * @return
	 */
	@SystemControllerLog(description = "根据oid获取一条平台公告信息")
	@RequestMapping(value = "/findnoticebyid")
    @ResponseBody
    public OpResult findNoticeByOId(String OID) {
		if (StringUtil.isNotEmpty(OID)) {
		    PageNewsDTO dto = articleService.findNoticeByOID(OID);
		    if (dto == null) {
		    	return OpResult.getFailedResult("暂无数据");
		    }
		    return OpResult.getSuccessResult(dto);
		}
		return OpResult.getFailedResult("查询失败");
	 }
	
	/**
	 * 关于我们-砖头动态信息列表
	 * @param
	 * @return
	 */
	@SystemControllerLog(description = "关于我们-砖头动态信息列表")
	@RequestMapping(value="getdynamicnewslist")
	@ResponseBody
	public OpResult getDynamicNewsList(Integer currentPage,PageNewsDTO pageNewsDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = articleService.findDynamicNews(currentPage,pageNewsDTO);
		if (map == null) {
		    return OpResult.getFailedResult("暂无数据");
		}
		return OpResult.getSuccessResult(map);
	}
	
	/**
	 * 添加一条砖头动态信息
	 * @param pageNewsDTO 砖头动态DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "添加一条砖头动态信息")
	@RequestMapping(value = "/adddynamicnews")
	@ResponseBody
	public OpResult addDynamicNews(PageNewsDTO pageNewsDTO){
		if(StringUtil.isNotEmpty(pageNewsDTO.getName()) && StringUtil.isNotEmpty(pageNewsDTO.getParentOID()) 
				&& StringUtil.isNotEmpty(pageNewsDTO.getMessages())){
			String num = articleService.addDynamicNews(pageNewsDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("添加失败");
	}
	
	/**
	 * 删除一条砖头动态信息
	 * @param OID 砖头动态oid
	 * @return
	 */
	@SystemControllerLog(description = "删除一条砖头动态信息")
	@RequestMapping(value = "/deletedynamicnews")
	@ResponseBody
	public OpResult deleteDynamicNews(String OID){
		if(StringUtil.isNotEmpty(OID)){
			String num = articleService.deleteDynamicNews(OID);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("删除失败");
	}
	
	/**
	 * 修改一条砖头动态信息
	 * @param pageNewsDTO 砖头动态DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "修改一条砖头动态信息")
	@RequestMapping(value = "/updatedynamicnews")
	@ResponseBody
	public OpResult updateDynamicNews(PageNewsDTO pageNewsDTO){
		if(StringUtil.isNotEmpty(pageNewsDTO.getOID())){
			String num = articleService.updateDynamicNews(pageNewsDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("修改失败");
	}
	
	/**
	 * 根据oid获取一条砖头动态信息
	 * @param OID  砖头动态oid
	 * @return
	 */
	@SystemControllerLog(description = "根据oid获取一条砖头动态信息")
	@RequestMapping(value = "/finddynamicnewsbyid")
    @ResponseBody
    public OpResult findDynamicNewsByOId(String OID) {
		if (StringUtil.isNotEmpty(OID)) {
		    PageNewsDTO dto = articleService.findDynamicNewsByOID(OID);
		    if (dto == null) {
		    	return OpResult.getFailedResult("暂无数据");
		    }
		    return OpResult.getSuccessResult(dto);
		}
		return OpResult.getFailedResult("查询失败");
	 }
	
	/**
	 * 关于我们-媒体报道信息列表
	 * @param
	 * @return
	 */
	@SystemControllerLog(description = "关于我们-媒体报道信息列表")
	@RequestMapping(value="getmedianewslist")
	@ResponseBody
	public OpResult getMediaNewsList(Integer currentPage,PageNewsDTO pageNewsDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = articleService.findMediaNews(currentPage,pageNewsDTO);
		if (map == null) {
		    return OpResult.getFailedResult("暂无数据");
		}
		return OpResult.getSuccessResult(map);
	}
	
	/**
	 * 添加一条媒体报道信息
	 * @param P2pMediaNews 媒体报道bean
	 * @return
	 */
	@SystemControllerLog(description = "添加一条媒体报道信息")
	@RequestMapping(value = "/addmedianews")
	@ResponseBody
	public OpResult addMediaNews(PageNewsDTO pageNewsDTO){
		if(StringUtil.isNotEmpty(pageNewsDTO.getName()) && StringUtil.isNotEmpty(pageNewsDTO.getParentOID())
				 && StringUtil.isNotEmpty(pageNewsDTO.getMessages())){
			String num = articleService.addMediaNews(pageNewsDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("添加失败");
	}
	
	/**
	 * 删除一条媒体报道信息
	 * @param OID 媒体报道oid
	 * @return
	 */
	@SystemControllerLog(description = "删除一条媒体报道信息")
	@RequestMapping(value = "/deletemedianews")
	@ResponseBody
	public OpResult deleteMediaNews(String OID){
		if(StringUtil.isNotEmpty(OID)){
			String num = articleService.deleteMediaNews(OID);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("删除失败");
	}
	
	/**
	 * 修改一条媒体报道信息
	 * @param pageNewsDTO 媒体报道DTOBean
	 * @return
	 */
	@SystemControllerLog(description = "修改一条媒体报道信息")
	@RequestMapping(value = "/updatemedianews")
	@ResponseBody
	public OpResult updateMediaNews(PageNewsDTO pageNewsDTO){
		if(StringUtil.isNotEmpty(pageNewsDTO.getOID())){
			String num = articleService.updateMediaNews(pageNewsDTO);
			if(StringUtil.isNotEmpty(num) && num.equals("1")){
				return OpResult.getSuccessResult(num);
			}
		}
		return OpResult.getFailedResult("修改失败");
	}
	
	/**
	 * 根据oid获取一条媒体报道信息
	 * @param OID  媒体报道oid
	 * @return
	 */
	@SystemControllerLog(description = "根据oid获取一条媒体报道信息")
	@RequestMapping(value = "/findmedianewsbyid")
    @ResponseBody
    public OpResult findMediaNewsByOId(String OID) {
		if (StringUtil.isNotEmpty(OID)) {
		    PageNewsDTO dto = articleService.findMediaNewsByOID(OID);
		    if (dto == null) {
		    	return OpResult.getFailedResult("暂无数据");
		    }
		    return OpResult.getSuccessResult(dto);
		}
	    return OpResult.getFailedResult("获取失败");
	 }
}
