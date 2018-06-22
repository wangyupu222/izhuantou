package com.izhuantou.service.impl.p2p;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.common.utils.DateUtils;
import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.p2p.FileInfo;
import com.izhuantou.damain.p2p.P2pFlink;
import com.izhuantou.damain.p2p.P2pMediaNews;
import com.izhuantou.damain.p2p.P2pNotice;
import com.izhuantou.damain.p2p.P2pPageBanner;
import com.izhuantou.damain.p2p.P2pPageproblems;
import com.izhuantou.damain.p2p.P2pToutiaoNews;
import com.izhuantou.damain.vo.ActivityNewPrivilegesDTO;
import com.izhuantou.damain.vo.BannerDTO;
import com.izhuantou.damain.vo.FlinkDTO;
import com.izhuantou.damain.vo.PageNewsDTO;
import com.izhuantou.damain.vo.PageproblemsDTO;
import com.izhuantou.dao.p2p.FileInfoMapper;
import com.izhuantou.dao.p2p.P2pFlinkMapper;
import com.izhuantou.dao.p2p.P2pMediaNewsMapper;
import com.izhuantou.dao.p2p.P2pNoticeMapper;
import com.izhuantou.dao.p2p.P2pPageBannerMapper;
import com.izhuantou.dao.p2p.P2pPageproblemsMapper;
import com.izhuantou.dao.p2p.P2pToutiaoNewsMapper;
import com.izhuantou.dao.webp2p.WebP2pPackageBiddingMainRuningMapper;
import com.izhuantou.service.api.p2p.PageService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("pageService")
public class PageServiceImpl extends BaseServiceImpl<P2pPageBanner> implements PageService {
    private static final Logger logger = LoggerFactory.getLogger(PageServiceImpl.class);
    @Autowired
    private P2pPageBannerMapper p2pPageBannerDao;
    @Autowired
    private P2pMediaNewsMapper p2pMediaNewsDao;
    @Autowired
    private FileInfoMapper fileInfoDao;
    @Autowired
    private P2pNoticeMapper p2pNoticeDao;
    @Autowired
    private P2pToutiaoNewsMapper p2pToutiaoNewsDao;
    @Autowired
    private P2pFlinkMapper p2pFlinkDao;
    @Autowired
    private WebP2pPackageBiddingMainRuningMapper webP2pPackageBiddingMainRuningMapper;
    @Autowired
    private P2pPageproblemsMapper P2pPageproblemsDao;

    /**
     * 查询轮播图
     */
    @Override
    public List<BannerDTO> findBunnerPage() {
	List<BannerDTO> filepage = new ArrayList<BannerDTO>();
	try {
	    List<P2pPageBanner> p2ppage = p2pPageBannerDao.findBunnerPage();
	    if (p2ppage != null) {
		for (P2pPageBanner bann : p2ppage) {
		    BannerDTO pa = new BannerDTO();
		    FileInfo file = fileInfoDao.findPageByOID(bann.getPicimgOID()); // 图片的id
		    if (file != null) {
			file.getName(); // 图片的名字
			file.getPhysicalName();// 图片的物理名字
			pa.setJumpurl(bann.getJumpurl());// banner图要跳转的地址
			pa.setName(bann.getName()); // banner下方的tap名字
			pa.setOID(file.getOID()); // 图片的id
			pa.setpName(file.getName());// 图片的真实名字
			pa.setPhysicalName(file.getPhysicalName());// 图片的物理名字
			filepage.add(pa);
		    }
		}
		return filepage;
	    }
	    return null;
	} catch (Exception e) {
	    logger.error("findBunnerPage()" + e.getMessage());
	    return null;
	}
    }

    /**
     * 根据OID查询媒体报道详情
     */
    @Override
    public PageNewsDTO findMediaNewsByOID(String OID) {
	try {
	    if (StringUtil.isNotEmpty(OID)) {
		P2pMediaNews pnc = p2pMediaNewsDao.findMediaNewsByOID(OID);
		if (pnc != null) {
		    PageNewsDTO dto = new PageNewsDTO();
		    String addDate = DateUtils.formatDate(pnc.getAddDateTime(), "");
		    dto.setAddDateTime(addDate);
		    dto.setMessages(pnc.getMessages());
		    dto.setTitleName(pnc.getName());
		    return dto;
		} else {
		    return null;
		}
	    } else {
		return null;
	    }
	} catch (Exception e) {
	    logger.error("findMediaNewsByOID(String OID)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 根据OID查询媒体报道
     */
    @Override
    public List<PageNewsDTO> findMediaNewsTitleName() {
	try {
	    List<PageNewsDTO> noticedto = new ArrayList<PageNewsDTO>();
	    List<P2pMediaNews> p2pMediaNews = p2pMediaNewsDao.findMediaNewsTitleName();
	    if (p2pMediaNews == null) {
		return null;
	    } else {
		for (P2pMediaNews pnc : p2pMediaNews) {
		    PageNewsDTO dto = new PageNewsDTO();
		    String addTime = DateUtils.formatJustDate(pnc.getAddDateTime().getTime());
		    dto.setAddTime(addTime);
		    dto.setTitleName(pnc.getName());
		    dto.setOID(pnc.getOID());
		    noticedto.add(dto);
		}
		return noticedto;
	    }
	} catch (Exception e) {
	    logger.error("findNoticeTitleName()" + e.getMessage());
	    return null;
	}

    }

    /***
     * 媒体报道 查询
     */
    @Override
    public Map<String, Object> findP2pMediaNews(Integer currentPage) {
	try {
	    // 存放图片和信息内容
	    List<PageNewsDTO> mediaNewsList = new ArrayList<PageNewsDTO>();
	    // 分页信息
	    Pagination<P2pMediaNews> pageController = new Pagination<>();
	    // 返回分页信息和内容
	    Map<String, Object> map = new HashMap<String, Object>();
	    /** 接受前台当前页参数 */
	    if (currentPage != null) {
		pageController.setCurrentPage(currentPage);
	    }
	    /** 获取总数 */
	    int rows = this.p2pMediaNewsDao.getRowCount();
	    pageController.setTotalNumber(rows);
	    // 设置每页显示5条
	    pageController.setPageSize(5);
	    // 起始位置
	    Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
	    // 每页条数
	    Integer pageSize = pageController.getPageSize();
	    // 获取查询记录
	    List<P2pMediaNews> select = this.p2pMediaNewsDao.findMediaNewsByPage(startIndex, pageSize);

	    for (P2pMediaNews pmn : select) {
		// 获取图片的id
		String ImgOID = pmn.getUploadImgOID();
		if (StringUtil.isNotEmpty(ImgOID)) {
		    FileInfo file = fileInfoDao.findPageByOID(ImgOID); // 图片的id
		    PageNewsDTO dto = new PageNewsDTO();
		    // 标题名字
		    dto.setTitleName(pmn.getName());
		    // 内容
		    dto.setMessages(pmn.getMessages());
		    // 图片的OID
		    dto.setImgOID(ImgOID);
		    // 图片名字
		    dto.setName(file.getName());
		    // 路径
		    dto.setSource(file.getSource());
		    // 内容OID
		    dto.setOID(pmn.getOID());
		    // 添加时间
		    String addTime = DateUtils.formatJustDate(pmn.getAddDateTime().getTime());
		    dto.setAddTime(addTime);
		    String addDate = DateUtils.formatDate(pmn.getAddDateTime(), "");
		    dto.setAddDateTime(addDate);
		    mediaNewsList.add(dto);
		}
	    }
	    map.put("mediaNewsList", mediaNewsList);
	    map.put("Pagination", pageController);
	    return map;
	} catch (Exception e) {
	    logger.error(" findP2pMediaNews(String parentOID,Integer currentPage)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 根据OID查询公告具体内容
     */
    @Override
    public PageNewsDTO findNoticeByOID(String OID) {
	try {
	    if (StringUtil.isNotEmpty(OID)) {
		P2pNotice pnc = p2pNoticeDao.findNoticeByOID(OID);
		if (pnc != null) {
		    PageNewsDTO dto = new PageNewsDTO();
		    String addDate = DateUtils.formatDate(pnc.getAddDateTime(), "");
		    dto.setAddDateTime(addDate);
		    dto.setMessages(pnc.getMessages());
		    dto.setTitleName(pnc.getName());
		    return dto;
		} else {
		    return null;
		}
	    } else {
		return null;
	    }
	} catch (Exception e) {
	    logger.error("findNoticeByOID(String OID)" + e.getMessage());
	    return null;
	}

    }

    /**
     * 查询公告平台公告的名字
     */
    @Override
    public List<PageNewsDTO> findNoticeTitleName() {
	try {
	    List<PageNewsDTO> noticedto = new ArrayList<PageNewsDTO>();
	    List<P2pNotice> p2pnotice = p2pNoticeDao.findNoticeTitleName();
	    if (p2pnotice == null) {
		return null;
	    } else {
		for (P2pNotice pnc : p2pnotice) {
		    PageNewsDTO dto = new PageNewsDTO();
		    String addTime = DateUtils.formatJustDate(pnc.getAddDateTime().getTime());
		    dto.setAddTime(addTime);
		    dto.setTitleName(pnc.getName());
		    dto.setOID(pnc.getOID());
		    noticedto.add(dto);
		}
		return noticedto;
	    }
	} catch (Exception e) {
	    logger.error("findNoticeTitleName()" + e.getMessage());
	    return null;
	}

    }

    /***
     * 分页查询平台公告
     */
    @Override
    public Map<String, Object> findP2pNoticeNews(Integer currentPage) {
	try {
	    // 存放图片和信息内容
	    List<PageNewsDTO> noticeList = new ArrayList<PageNewsDTO>();
	    // 分页信息
	    Pagination<P2pNotice> pageController = new Pagination<>();
	    // 返回分页信息和内容
	    Map<String, Object> map = new HashMap<String, Object>();
	    /** 接受前台当前页参数 */
	    if (currentPage != null) {
		pageController.setCurrentPage(currentPage);
	    }
	    /** 获取总数 */
	    int rows = p2pNoticeDao.getRowCount();
	    pageController.setTotalNumber(rows);
	    // 取得总页数，总页数=总记录数/总页数
	    Integer totalPage = pageController.getTotalPage();
	    // 设置每页显示5条
	    pageController.setPageSize(5);
	    // 起始位置
	    Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
	    // 每页条数
	    Integer pageSize = pageController.getPageSize();
	    // 获取查询记录
	    List<P2pNotice> select = this.p2pNoticeDao.findNoticeByPage(startIndex, pageSize);

	    for (P2pNotice pmn : select) {
		// 获取图片的id
		String ImgOID = pmn.getUploadImgOID();
		if (StringUtil.isNotEmpty(ImgOID)) {
		    FileInfo file = fileInfoDao.findPageByOID(ImgOID); // 图片的id
		    PageNewsDTO dto = new PageNewsDTO();
		    // 标题名字
		    dto.setTitleName(pmn.getName());
		    // 图片的OID
		    dto.setImgOID(ImgOID);
		    // 图片名字
		    dto.setName(file.getName());
		    // 路径
		    dto.setSource(file.getSource());
		    // 内容OID
		    dto.setOID(pmn.getOID());

		    String addDate = DateUtils.formatDate(pmn.getAddDateTime(), "");
		    dto.setAddDateTime(addDate);
		    // 添加时间
		    dto.setAddDateTime(addDate);
		    String addTime = DateUtils.formatJustDate(pmn.getAddDateTime().getTime());
		    dto.setAddTime(addTime);
		    // 内容
		    dto.setMessages(pmn.getMessages());
		    noticeList.add(dto);
		}
	    }
	    map.put("noticeList", noticeList);
	    map.put("Pagination", pageController);
	    return map;
	} catch (Exception e) {
	    logger.error("findP2pNoticeNews(String parentOID,Integer currentPage)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 根据OID查询头条信息的详情
     */
    @Override
    public PageNewsDTO findToutiaoNewsByOID(String OID) {
	try {
	    if (StringUtil.isNotEmpty(OID)) {
		P2pToutiaoNews pnc = p2pToutiaoNewsDao.findToutiaoNewsByOID(OID);
		if (pnc != null) {
		    PageNewsDTO dto = new PageNewsDTO();
		    String addDate = DateUtils.formatDate(pnc.getAddDateTime(), "");
		    dto.setAddDateTime(addDate);
		    dto.setMessages(pnc.getMessages());
		    dto.setTitleName(pnc.getName());
		    return dto;
		} else {
		    return null;
		}
	    } else {
		return null;
	    }
	} catch (Exception e) {
	    logger.error(" findToutiaoNewsByOID(String OID) " + e.getMessage());
	    return null;
	}

    }

    /**
     * 砖头动态（新闻动态）
     */
    @Override
    public Map<String, Object> findP2pToutiaoNews(Integer currentPage) {
	try {
	    // 存放图片和信息内容
	    List<PageNewsDTO> ToutiaoList = new ArrayList<PageNewsDTO>();
	    // 分页信息
	    Pagination<P2pToutiaoNews> pageController = new Pagination<>();
	    // 返回分页信息和内容
	    Map<String, Object> map = new HashMap<String, Object>();
	    /** 接受前台当前页参数 */
	    if (currentPage != null) {
		pageController.setCurrentPage(currentPage);
	    }
	    /** 获取总数 */
	    int rows = p2pToutiaoNewsDao.getRowCount();
	    pageController.setTotalNumber(rows);
	    // 取得总页数，总页数=总记录数/总页数
	    Integer totalPage = pageController.getTotalPage();
	    // 设置每页显示5条
	    pageController.setPageSize(5);
	    // 起始位置
	    Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
	    // 每页条数
	    Integer pageSize = pageController.getPageSize();
	    // 获取查询记录
	    List<P2pToutiaoNews> select = this.p2pToutiaoNewsDao.findToutiaoNewsByPage(startIndex, pageSize);
	    for (P2pToutiaoNews pmn : select) {
		// 获取图片的id
		String ImgOID = pmn.getUploadImgOID();
		if (StringUtil.isNotEmpty(ImgOID)) {
		    FileInfo file = fileInfoDao.findPageByOID(ImgOID); // 图片的id
		    PageNewsDTO dto = new PageNewsDTO();
		    // 标题名字
		    dto.setTitleName(pmn.getName());
		    // 内容
		    dto.setMessages(pmn.getMessages());
		    // 图片的OID
		    dto.setImgOID(ImgOID);
		    // 图片名字
		    dto.setName(file.getName());
		    // 路径
		    dto.setSource(file.getSource());
		    // 内容OID
		    dto.setOID(pmn.getOID());
		    // 添加时间
		    String addDate = DateUtils.formatDate(pmn.getAddDateTime(), "");
		    String addTime = DateUtils.formatJustDate(pmn.getAddDateTime().getTime());
		    dto.setAddTime(addTime);
		    dto.setAddDateTime(addDate);

		    ToutiaoList.add(dto);
		}
	    }
	    map.put("ToutiaoList", ToutiaoList);
	    map.put("Pagination", pageController);
	    return map;
	} catch (Exception e) {
	    logger.error("findP2pToutiaoNews( Integer currentPage)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 查询友情链接
     */
    @Override
    public List<FlinkDTO> findP2pFlinks() {
	try {
	    List<FlinkDTO> Flinkdto = new ArrayList<FlinkDTO>();
	    List<P2pFlink> P2pFlink = p2pFlinkDao.findFlinkList();
	    if (P2pFlink != null) {
		for (P2pFlink flink : P2pFlink) {
		    FlinkDTO dto = new FlinkDTO();
		    dto.setName(flink.getName());
		    dto.setLinkUrl(flink.getLinkUrl());
		    Flinkdto.add(dto);
		}
		return Flinkdto;
	    } else {
		return null;
	    }
	} catch (Exception e) {
	    logger.error(" findP2pFlinks(String parentOID)" + e.getMessage());
	    return null;
	}
    }

    /**
     * 帮助中心
     * 
     * @return
     */
    @Override
    public Map<String, Object> findProblems(String parentOID, Integer currentPage) {
	try {
	    if (StringUtil.isEmpty(parentOID)) {
		return null;
	    }
	    // 分页信息
	    Pagination<P2pPageproblems> pageController = new Pagination<>();
	    // 返回分页信息和内容
	    Map<String, Object> map = new HashMap<String, Object>();
	    /** 接受前台当前页参数 */
	    if (currentPage != null) {
		pageController.setCurrentPage(currentPage);
	    }
	    /** 获取总数 */
	    int rows = P2pPageproblemsDao.getRowCount(parentOID);
	    pageController.setTotalNumber(rows);
	    // 取得总页数，总页数=总记录数/总页数
	    Integer totalPage = pageController.getTotalPage();
	    // 设置每页显示5条
	    pageController.setPageSize(12);
	    // 起始位置
	    Integer startIndex = (pageController.getCurrentPage() - 1) * (pageController.getPageSize());
	    // 每页条数
	    Integer pageSize = pageController.getPageSize();
	    List<P2pPageproblems> p2pProblems = P2pPageproblemsDao.findProblems(parentOID, startIndex, pageSize);
	    List<PageproblemsDTO> p2ptdo = new ArrayList<PageproblemsDTO>();
	    for (P2pPageproblems ppb : p2pProblems) {
		PageproblemsDTO ppbl = new PageproblemsDTO();
		ppbl.setProblemsContent(ppb.getProblemsContent());
		ppbl.setOID(ppb.getOID());
		ppbl.setName(ppb.getName());
		p2ptdo.add(ppbl);
	    }
	    map.put("ProblemsList", p2ptdo);
	    map.put("Pagination", pageController);
	    return map;
	} catch (Exception e) {
	    logger.error("findProblems(String parentOID,Integer currentPage)" + e.getMessage());
	    return null;
	}
    }

    /** 为新手注册赠送加息券页面查询环环标的的OID和期数 */
    @Override
    public Map<String, String> findOIDandTerm() {
	try {
	    List<ActivityNewPrivilegesDTO> list = webP2pPackageBiddingMainRuningMapper.findOIDAndTerm();
	    Map<String, String> resultMap = new HashMap<>();
	    for (ActivityNewPrivilegesDTO activityNewPrivilegesDTO : list) {
		if (1 == (int) activityNewPrivilegesDTO.getProductTerm()) {
		    resultMap.put("one", activityNewPrivilegesDTO.getOID());
		}
		if (3 == (int) activityNewPrivilegesDTO.getProductTerm()) {
		    resultMap.put("three", activityNewPrivilegesDTO.getOID());
		}
		if (6 == (int) activityNewPrivilegesDTO.getProductTerm()) {
		    resultMap.put("six", activityNewPrivilegesDTO.getOID());
		}
	    }
	    return resultMap;
	} catch (Exception e) {
	    logger.error("findOIDandTerm" + e.getMessage());
	    return null;
	}

    }

}
