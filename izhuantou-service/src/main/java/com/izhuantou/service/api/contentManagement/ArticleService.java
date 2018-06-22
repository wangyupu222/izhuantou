package com.izhuantou.service.api.contentManagement;

import java.util.Map;

import com.izhuantou.damain.p2p.P2pMediaNews;
import com.izhuantou.damain.p2p.P2pNotice;
import com.izhuantou.damain.p2p.P2pPageproblems;
import com.izhuantou.damain.p2p.P2pToutiaoNews;
import com.izhuantou.damain.vo.PageNewsDTO;
import com.izhuantou.damain.vo.PageproblemsDTO;

public interface ArticleService {
	/*******************************************帮助中心************************************************/
	/**
     * 按条件查找问题
     * @param currentPage 页数
	 * @param pageproblemsDTO 问题DTOBean
     * @return
     */
    public Map<String, Object> findProblems(Integer currentPage,PageproblemsDTO pageproblemsDTO);
    
    /**
     * 添加一条帮组中心问题
     * @param pageproblemsDTO 问题DTOBean
     * @return 
     */
    public String addProblem(PageproblemsDTO pageproblemsDTO);
    
    /**
     * 删除一条帮组中心问题
     * @param OID 问题id
     * @return 
     */
    public String deleteProblem(String OID);
    
    /**
     * 修改一条帮组中心问题
     * @param pageproblemsDTO 问题DTOBean
     * @return 
     */
    public String updateProblem(PageproblemsDTO pageproblemsDTO);
    
    /**
     * 查询一条帮组中心问题
     * @param OID 问题id
     * @return 
     */
    public PageproblemsDTO findProblemById(String OID);
    
    /*************************************************关于我们***************************************************/
    /**
     * 获取关于我们-平台公告信息列表
     * @param currentPage 页数
     * @return
     */
    public Map<String, Object> findNotices(Integer currentPage);
    
    /**
     * 添加一条公告
     * @param pageNewsDTO 公告DTOBean
     * @return 
     */
    public String addNotice(PageNewsDTO pageNewsDTO);
    
    /**
     * 删除一条公告
     * @param OID 公告id
     * @return 
     */
    public String deleteNotice(String OID);
    
    /**
     * 修改一条公告
     * @param pageNewsDTO 公告DTOBean
     * @return 
     */
    public String updateNotice(PageNewsDTO pageNewsDTO);
    
    /**
     * 根据OID查询公告具体内容
     * @param OID 公告id
     * @return
     */
    public PageNewsDTO findNoticeByOID(String OID);
    
    /**
     * 获取关于我们-砖头动态信息列表
     * @param currentPage 页数
     * @return
     */
    public Map<String, Object> findDynamicNews(Integer currentPage);
    
    /**
     * 添加一条砖头动态
     * @param pageNewsDTO 砖头动态DTOBean
     * @return 
     */
    public String addDynamicNews(PageNewsDTO pageNewsDTO);
    
    /**
     * 删除一条砖头动态
     * @param OID 砖头动态oid
     * @return 
     */
    public String deleteDynamicNews(String OID);
    
    /**
     * 修改一条砖头动态
     * @param pageNewsDTO 砖头动态DTOBean
     * @return 
     */
    public String updateDynamicNews(PageNewsDTO pageNewsDTO);
    
    /**
     * 根据OID查询砖头动态具体内容
     * @param OID 砖头动态oid
     * @return
     */
    public PageNewsDTO findDynamicNewsByOID(String OID);
    
    /**
     * 获取关于我们-媒体报道信息列表
     * @param currentPage 页数
     * @return
     */
    public Map<String, Object> findMediaNews(Integer currentPage);
    
    /**
     * 添加一条媒体报道
     * @param pageNewsDTO 媒体报道DTOBean
     * @return 
     */
    public String addMediaNews(PageNewsDTO pageNewsDTO);
    
    /**
     * 删除一条媒体报道
     * @param OID 媒体报道oid
     * @return 
     */
    public String deleteMediaNews(String OID);
    
    /**
     * 修改一条媒体报道
     * @param pageNewsDTO 媒体报道DTOBean
     * @return 
     */
    public String updateMediaNews(PageNewsDTO pageNewsDTO);
    
    /**
     * 根据OID查询媒体报道具体内容
     * @param OID 媒体报道oid
     * @return
     */
    public PageNewsDTO findMediaNewsByOID(String OID);
}
