package com.izhuantou.service.api.p2p;

import java.util.List;
import java.util.Map;

import com.izhuantou.damain.vo.BannerDTO;
import com.izhuantou.damain.vo.FlinkDTO;
import com.izhuantou.damain.vo.PageNewsDTO;

public interface PageService {
    /**
     * 查询轮播图
     * 
     * @return
     */
    public List<BannerDTO> findBunnerPage();

    /**
     * 媒体报道 查询
     * 
     * @param parentOID
     * @return
     */
    public Map<String, Object> findP2pMediaNews(Integer currentPage);

    /**
     * 根据OID查询媒体报道详情
     * 
     * @param OID
     * @return
     */
    public PageNewsDTO findMediaNewsByOID(String OID);

    /**
     * 查询媒体报道的标题名和时间,
     * 
     * @return
     */
    public List<PageNewsDTO> findMediaNewsTitleName();

    /**
     * 平台公告
     * 
     * @param
     * @param currentPage
     * @return
     */
    public Map<String, Object> findP2pNoticeNews(Integer currentPage);

    /**
     * 根据OID查询公告具体内容
     * 
     * @param OID
     * @return
     */
    public PageNewsDTO findNoticeByOID(String OID);

    /**
     * 查询平台公告的名字
     * 
     * @return
     */
    public List<PageNewsDTO> findNoticeTitleName();

    /**
     * 砖头动态（新闻动态）
     * 
     * @param parentOID
     * @param currentPage
     * @return
     */
    public Map<String, Object> findP2pToutiaoNews(Integer currentPage);

    /**
     * 根据OID查询头条信息的详情
     * 
     * @param OID
     * @return
     */
    public PageNewsDTO findToutiaoNewsByOID(String OID);

    /***
     * 查询友情链接
     * 
     * @return
     */
    public List<FlinkDTO> findP2pFlinks();

    /**
     * 根据问题类型查找问题（parentOID）
     * 
     * @return
     */
    public Map<String, Object> findProblems(String parentOID, Integer currentPage);

    /**
     * 活动页面 查找各种期限的环环投标的和新手转标的（环环转转） 的OID和期数
     */
    public Map<String, String> findOIDandTerm();

}
