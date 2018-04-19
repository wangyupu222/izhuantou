package com.izhuantou.app.controller;

import java.util.ArrayList;
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
import com.izhuantou.damain.mobile.App_version;
import com.izhuantou.damain.mobile.MediaNewsModel;
import com.izhuantou.damain.mobile.MobileAdvert;
import com.izhuantou.damain.vo.BannerDTO;
import com.izhuantou.damain.vo.PageNewsDTO;
import com.izhuantou.service.api.mobile.AppVersionService;
import com.izhuantou.service.api.mobile.MobileAdvertService;
import com.izhuantou.service.api.p2p.PageService;

@Controller
@RequestMapping("app/mobilepage")
public class MobilePageController {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private PageService pageService;
    @Autowired
    private AppVersionService appVersionService;
    @Autowired
    private MobileAdvertService mobileAdvertService;

    /**
     * 返回轮播信息
     * 
     * @return
     */
    @RequestMapping(value = "/mobileGetBanner", method = RequestMethod.GET)
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
     * 首页平台公告只取一条
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobilegetnoticetitle")
    @ResponseBody
    public OpResult mobilegetnoticetitle(Integer currentPage) {// Integer
							       // currentPage
	Map<String, Object> map = new HashMap<String, Object>();
	currentPage = 1;
	map = pageService.findP2pNoticeNews(currentPage);
	List<PageNewsDTO> PintTaiList = new ArrayList<>();
	PintTaiList = (List) map.get("noticeList");
	String PingTai = PintTaiList.get(0).getTitleName();
	Map<String, Object> result = new HashMap<>();
	result.put("PingTai", PingTai);
	if (map == null) {
	    return OpResult.getFailedResult("暂无数据");
	} else {
	    return OpResult.getSuccessResult(result);
	}
    }

    /**
     * 平台公告信息列表
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobilegetnotice")
    @ResponseBody
    public OpResult notice(Integer currentPage) {// Integer currentPage
	Map<String, Object> map = new HashMap<String, Object>();
	currentPage = 1;
	map = pageService.findP2pNoticeNews(currentPage);
	if (map == null) {
	    return OpResult.getFailedResult("暂无数据");
	} else {
	    return OpResult.getSuccessResult(map);
	}
    }

    /**
     * 平台公告详情
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobilegetnoticedetails")
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
     * 手机端版本号
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobileVersion")
    @ResponseBody
    public OpResult mobileVersion() {// Integer currentPage

	App_version list = appVersionService.findVersion();
	if (list == null) {
	    return OpResult.getFailedResult("暂无数据");
	} else {
	    return OpResult.getSuccessResult(list);
	}
    }

    /**
     * 手机端广告
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobileAppggy")
    @ResponseBody
    public OpResult mobileAppggy() {// Integer currentPage

	List<MobileAdvert> list = mobileAdvertService.FindAppggy();
	if (list == null) {
	    return OpResult.getFailedResult("暂无数据");
	} else {
	    return OpResult.getSuccessResult(list);
	}
    }

    /**
     * 手机端广告
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobileApptc")
    @ResponseBody
    public OpResult mobileApptc() {// Integer currentPage

	List<MobileAdvert> list = mobileAdvertService.FindApptc();
	if (list == null) {
	    return OpResult.getFailedResult("暂无数据");
	} else {
	    return OpResult.getSuccessResult(list);
	}
    }

    /**
     * 新闻动态信息（砖头动态）
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobileGettoutiao")
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
     * 返回媒体报道信息
     * 
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/mobilegetmedianews")
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
     * 获得媒体报道的名字列表
     * 
     * @return
     */
    @RequestMapping(value = "/mobilegetmediaNewsname")
    @ResponseBody
    public OpResult mediaNewsName() {
	List<PageNewsDTO> dto = pageService.findMediaNewsTitleName();

	List<MediaNewsModel> dtores = new ArrayList<>();
	for (PageNewsDTO pageNewsDTO : dto) {
	    MediaNewsModel model = new MediaNewsModel();
	    model.setName(pageNewsDTO.getTitleName());
	    model.setUpdDateTime(pageNewsDTO.getAddTime());
	    model.setUploadImgOID(pageNewsDTO.getOID());
	    // model.setMessages(pageNewsDTO.getMessages());
	    dtores.add(model);
	}

	if (dto == null) {
	    return OpResult.getFailedResult("暂无数据");
	} else {
	    return OpResult.getSuccessResult(dtores);
	}
    }

    /***
     * 根据OID查询媒体报道的详情
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/mobilegetmediaNewsdetails")
    @ResponseBody
    public OpResult mediaNewsMessage(String OID) {// Integer currentPage
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
     * 平台公告详情页
     * 
     * @return
     */

    @RequestMapping(value = "/mobilegetnoticename")
    @ResponseBody
    public OpResult noticeName() {
	List<PageNewsDTO> dto = pageService.findNoticeTitleName();
	MediaNewsModel model = new MediaNewsModel();
	List<MediaNewsModel> dtores = new ArrayList<>();
	for (PageNewsDTO pageNewsDTO : dto) {
	    model.setName(pageNewsDTO.getTitleName());
	    model.setUpdDateTime(pageNewsDTO.getAddTime());
	    model.setUploadImgOID(pageNewsDTO.getOID());
	    // model.setMessages(pageNewsDTO.getMessages());
	    dtores.add(model);
	}
	if (dto == null) {
	    return OpResult.getFailedResult("暂无数据");
	} else {
	    return OpResult.getSuccessResult(dtores);
	}
    }

}
