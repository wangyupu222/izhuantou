package com.izhauntou.portal.controller.p2p;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.izhuantou.common.bean.OpResult;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.vo.BannerDTO;
import com.izhuantou.damain.vo.FlinkDTO;
import com.izhuantou.damain.vo.PageNewsDTO;
import com.izhuantou.service.api.p2p.PageService;

@Controller
@RequestMapping("page")
public class PageController {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private PageService pageService;

    /**
     * 返回轮播信息
     * 
     * @return
     */
    @RequestMapping(value = "/getbanner", method = RequestMethod.GET)
    @ResponseBody
    public OpResult pageBanner() {
	List<BannerDTO> page = pageService.findBunnerPage();
	if (page == null) {
	    return OpResult.getFailedResult("没有图片了");
	} else {
	    return OpResult.getSuccessResult(page);
	}
    }

    /**
     * 关于我们
     * 
     * @return
     */
    @RequestMapping(value = "/aboutus", method = RequestMethod.GET)
    public String aboutOursUI() {

	return "aboutus";
    }

    /**
     * 媒体报道列表页
     * 
     * @return
     */
    @RequestMapping(value = "/mediaNews", method = RequestMethod.GET)
    public String mediaNewsList() {

	return "mediaNews";
    }

    /**
     * 媒体报道列表详情页
     * 
     * @return
     */
    @RequestMapping(value = "/mediaNews_details", method = RequestMethod.GET)
    public String mediaNewsInfo() {

	return "mediaNews_details";
    }

    /**
     * 获得媒体报道的名字列表
     * 
     * @return
     */
    @RequestMapping(value = "/getmediaNewsname")
    @ResponseBody
    public OpResult mediaNewsName() {
	List<PageNewsDTO> dto = pageService.findMediaNewsTitleName();
	if (dto == null) {
	    return OpResult.getFailedResult("暂无数据");
	} else {
	    return OpResult.getSuccessResult(dto);
	}
    }

    /***
     * 根据OID查询媒体报道的详情
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/getmediaNewsdetails")
    @ResponseBody
    public OpResult mediaNewsMessage(String OID) {
	if (StringUtil.isNotEmpty(OID)) {
	    PageNewsDTO dto = pageService.findMediaNewsByOID(OID);
	    if (dto == null) {
		return OpResult.getFailedResult("暂无数据");
	    } else {
		return OpResult.getSuccessResult(dto);
	    }
	} else {
	    return OpResult.getFailedResult("OID不能为空");
	}

    }

    /**
     * 返回媒体报道信息
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/getmedianews")
    @ResponseBody
    public OpResult MediaNews(Integer currentPage) {
	Map<String, Object> map = new HashMap<String, Object>();
	map = pageService.findP2pMediaNews(currentPage);
	if (map == null) {
	    return OpResult.getFailedResult("暂无报道");
	} else {
	    return OpResult.getSuccessResult(map);
	}
    }

    /**
     * 平台公告列表页
     * 
     * @return
     */
    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public String notice() {

	return "notice";
    }

    /**
     * 平台公告详情页
     * 
     * @return
     */
    @RequestMapping(value = "/notice_details", method = RequestMethod.GET)
    public String noticeDetails() {
	return "notice_details";
    }

    @RequestMapping(value = "/getnoticename")
    @ResponseBody
    public OpResult noticeName() {
	List<PageNewsDTO> dto = pageService.findNoticeTitleName();
	if (dto == null) {
	    return OpResult.getFailedResult("暂无数据");
	} else {
	    return OpResult.getSuccessResult(dto);
	}
    }

    @RequestMapping(value = "/getnoticedetails")
    @ResponseBody
    public OpResult noticeMessage(String OID) {// Integer currentPage
	if (StringUtil.isNotEmpty(OID)) {
	    PageNewsDTO dto = pageService.findNoticeByOID(OID);
	    if (dto == null) {
		return OpResult.getFailedResult("暂无数据");
	    } else {
		return OpResult.getSuccessResult(dto);
	    }
	} else {
	    return OpResult.getFailedResult("OID不能为空");
	}

    }

    /**
     * 平台公告信息列表
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/getnotice")
    @ResponseBody
    public OpResult notice(Integer currentPage) {// Integer currentPage
	Map<String, Object> map = new HashMap<String, Object>();
	map = pageService.findP2pNoticeNews(currentPage);
	if (map == null) {
	    return OpResult.getFailedResult("暂无数据");
	} else {
	    return OpResult.getSuccessResult(map);
	}
    }

    /**
     * 砖头动态列表页
     * 
     * @return
     */
    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String news() {

	return "news";
    }

    /**
     * 砖头动态列表页
     * 
     * @return
     */
    @RequestMapping(value = "/news_details", method = RequestMethod.GET)
    public String newsDetails() {

	return "news_details";
    }

    /**
     * 新闻动态信息（砖头动态）
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/gettoutiao")
    @ResponseBody
    public OpResult TouTiao(Integer currentPage) {// Integer currentPage
	Map<String, Object> map = new HashMap<String, Object>();
	map = pageService.findP2pToutiaoNews(currentPage);
	if (map == null) {
	    return OpResult.getFailedResult("暂无数据");
	} else {
	    return OpResult.getSuccessResult(map);
	}
    }

    @RequestMapping(value = "/getToutaiodetails")
    @ResponseBody
    public OpResult ToutaioMessage(String OID) {// Integer currentPage
	if (StringUtil.isNotEmpty(OID)) {
	    PageNewsDTO dto = pageService.findToutiaoNewsByOID(OID);
	    if (dto == null) {
		return OpResult.getFailedResult("暂无数据");
	    } else {
		return OpResult.getSuccessResult(dto);
	    }
	} else {
	    return OpResult.getFailedResult("OID不能为空");
	}

    }

    /**
     * 合作伙伴
     * 
     * @return
     */
    @RequestMapping(value = "/getflink", method = RequestMethod.GET)
    @ResponseBody
    public OpResult Flink() {
	List<FlinkDTO> flink = pageService.findP2pFlinks();
	if (flink == null) {
	    return OpResult.getFailedResult("暂无合作伙伴");
	} else {
	    return OpResult.getSuccessResult(flink);
	}
    }

    @RequestMapping(value = "/helpmore")
    public String HellpIndex() {

	return "helpmore";
    }

    /**
     * 帮助中心
     * 
     * @return
     */
    @RequestMapping(value = "/gethellp")
    @ResponseBody
    public OpResult Hellp(String parentOID, Integer currentPage) {
	Map<String, Object> flink = pageService.findProblems(parentOID, currentPage);
	if (flink == null) {
	    return OpResult.getFailedResult("暂无内容");
	} else {
	    return OpResult.getSuccessResult(flink);
	}
    }

    @RequestMapping(value = "/guide")
    public String guide() {

	return "guide";
    }

    @RequestMapping(value = "/security")
    public String security() {

	return "security";
    }

    /**
     * 为新手注册赠送加息券页面查询环环标的的OID和期数
     * 
     * @return
     */
    @RequestMapping(value = "/gethhOIDAndTerm")
    @ResponseBody
    public OpResult gethhOIDAndTerm() {
	Map<String, String> list = pageService.findOIDandTerm();
	if (list == null) {
	    return OpResult.getFailedResult("暂无内容");
	} else {
	    return OpResult.getSuccessResult(list);
	}
    }

    /**
     * 为新手注册赠送加息券页面查询环环标的的OID和期数
     * 
     * @return
     */
    @RequestMapping(value = "/New_privileges")
    public String New_privileges() {
	return "New_privileges";
    }

    /**
     * 收益计算
     * 
     * @return
     */
    @RequestMapping(value = "/calc")
    public String calc() {
	return "calc";
    }
}
