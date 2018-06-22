package com.izhuantou.service.impl.contentManagement;

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
import com.izhuantou.damain.p2p.P2pMediaNews;
import com.izhuantou.damain.p2p.P2pNotice;
import com.izhuantou.damain.p2p.P2pPageproblems;
import com.izhuantou.damain.p2p.P2pToutiaoNews;
import com.izhuantou.damain.vo.PageNewsDTO;
import com.izhuantou.damain.vo.PageproblemsDTO;
import com.izhuantou.dao.contentManagement.ContentDynamicNewsMapper;
import com.izhuantou.dao.contentManagement.ContentMediaNewsMapper;
import com.izhuantou.dao.contentManagement.ContentNoticeMapper;
import com.izhuantou.dao.contentManagement.ContentPageProblemMapper;
import com.izhuantou.dao.p2p.FileInfoMapper;
import com.izhuantou.service.api.contentManagement.ArticleService;
@Service("articleService")
public class ArticleServiceImpl implements ArticleService{
	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	@Autowired
	private ContentPageProblemMapper contentPageProblemDao; 
	@Autowired
	private ContentNoticeMapper contentNoticeDao;
	@Autowired
    private FileInfoMapper fileInfoDao;
	@Autowired
	private ContentDynamicNewsMapper contentDynamicNewsDao;
	@Autowired
	private ContentMediaNewsMapper contentMediaNewsDao;
	/**
	 * 获取帮助中心问题列表
	 * @param currentPage 页数
	 * @param pageproblemsDTO 问题DTOBean
	 */
	@Override
	public Map<String, Object> findProblems(Integer currentPage,PageproblemsDTO pageproblemsDTO) {
		try{
			if(StringUtil.isEmpty(pageproblemsDTO.getParentOID())){
				return null;
			}
			//分页信息
			Pagination<P2pPageproblems> pageProblems = new Pagination<>();
			//返回分页信息和内容
			Map<String,Object> map = new HashMap<String,Object>();
			if(currentPage != null){
				pageProblems.setCurrentPage(currentPage);
			}
			//获取问题总数
			int rows = contentPageProblemDao.getRowCountByTypes(pageproblemsDTO);
			pageProblems.setTotalNumber(rows);
			//设置每页显示条数
			pageProblems.setPageSize(12);
			//获取总页数
			Integer totalPage = pageProblems.getTotalPage();
			//起始位置
			Integer startIndex = (pageProblems.getCurrentPage()-1)*pageProblems.getPageSize();
			//每页条数
			Integer pageSize = pageProblems.getPageSize();
			//获取问题列表
			List<P2pPageproblems> p2pProblemsList = contentPageProblemDao.findProblems(pageproblemsDTO,startIndex, pageSize);
			List<PageproblemsDTO> p2ptdo = new ArrayList<PageproblemsDTO>();
			if(p2pProblemsList != null && p2pProblemsList.size()>0){
				for(P2pPageproblems ppb : p2pProblemsList){
					PageproblemsDTO ppbl = new PageproblemsDTO();
					ppbl.setOID(ppb.getOID());					//oid
					ppbl.setName(ppb.getName());				//标题
					ppbl.setAuthor(ppb.getAuthor());			//作者
					if(ppb.getEffectiveTime() !=null){       	//生效时间存在   
						ppbl.setEffectiveTime(DateUtils.formatDate(ppb.getEffectiveTime(),""));
					}
					p2ptdo.add(ppbl);
				}
				map.put("ProblemsList", p2ptdo);
				map.put("Pagination", pageProblems);
				return map;
			}
			return null;
		} catch (Exception e){
			 logger.error("findProblems(String parentOID,Integer currentPage)" + e.getMessage());
			 return null;
		}
	}
	/**
     * 添加一条帮助中心问题
     * @param pageproblemsDTO 问题DTObean
     * @return 
     */
	@Override
	public String addProblem(PageproblemsDTO pageproblemsDTO) {
		try{
			//必要参数验证非空
			if (StringUtil.isNotEmpty(pageproblemsDTO.getName()) && StringUtil.isNotEmpty(pageproblemsDTO.getParentOID()) 
				&& StringUtil.isNotEmpty(pageproblemsDTO.getProblemsContent())) {
				P2pPageproblems p2pPageproblems = new P2pPageproblems();
				String OID = StringUtil.getUUID();
				p2pPageproblems.setOID(OID);         									   	//id
				p2pPageproblems.setName(pageproblemsDTO.getName());                        	//标题
				p2pPageproblems.setProblemsContent(pageproblemsDTO.getProblemsContent());  	//内容
				p2pPageproblems.setParentOID(pageproblemsDTO.getParentOID());			   	//类型oid
				p2pPageproblems.setAuthor(pageproblemsDTO.getAuthor());  				  	//作者
				if(pageproblemsDTO.getEffectiveTime() != null){
					p2pPageproblems.setEffectiveTime(DateUtils.getDate(pageproblemsDTO.getEffectiveTime(),""));//生效时间
				}
				p2pPageproblems.setValid(true);      										//是否有效
				p2pPageproblems.setRefresh(true);    										//是否更新
				p2pPageproblems.setVersion(0);       										//版本号
				Integer num = contentPageProblemDao.addProblem(p2pPageproblems);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("addProblem()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 删除一条帮助中心问题
     * @param OID 问题id
     * @return
     */
	@Override
	public String deleteProblem(String OID) {
		try{
			if(StringUtil.isNotEmpty(OID)){
				Integer num = contentPageProblemDao.deleteProblem(OID);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("deleteProblem()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 修改一条帮助中心问题
     * @param pageproblemsDTO 问题DTOBean
     * @return 
     */
	@Override
	public String updateProblem(PageproblemsDTO pageproblemsDTO) {
		try{
			if(StringUtil.isNotEmpty(pageproblemsDTO.getOID())&& StringUtil.isNotEmpty(pageproblemsDTO.getParentOID())){
				//根据id查询原数据
				P2pPageproblems p2pPageproblem = contentPageProblemDao.selectByPrimaryKey(pageproblemsDTO.getOID());
				if(p2pPageproblem != null){
					//更改数据赋值
					if(pageproblemsDTO.getName() != null){ 
						p2pPageproblem.setName(pageproblemsDTO.getName());  					 //问题名称
					}
					if(pageproblemsDTO.getProblemsContent() != null){
						p2pPageproblem.setProblemsContent(pageproblemsDTO.getProblemsContent()); //发布内容
					}
					if(pageproblemsDTO.getEffectiveTime() != null){
						p2pPageproblem.setEffectiveTime(DateUtils.getDate(pageproblemsDTO.getEffectiveTime(),""));   //生效时间
					}
					if(pageproblemsDTO.getParentOID() != null){
						p2pPageproblem.setParentOID(pageproblemsDTO.getParentOID());  			 //父级OID
					}
					//更新表
					Integer num = contentPageProblemDao.updateProblem(p2pPageproblem);
					return num.toString();
				}
			}
			return null;
		}catch(Exception e){
			logger.error("updateProblem()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 查询一条帮助中心问题
     * @param OID 问题id
     * @return 
     */
	@Override
	public PageproblemsDTO findProblemById(String OID) {
		try{
			if(StringUtil.isNotEmpty(OID)){
				//根据id查询问题
				P2pPageproblems p2pPageproblems = contentPageProblemDao.findProblemById(OID);
				if(p2pPageproblems != null){
					PageproblemsDTO pageproblemsDTO = new PageproblemsDTO();
					pageproblemsDTO.setOID(p2pPageproblems.getOID());
					pageproblemsDTO.setName(p2pPageproblems.getName());
					pageproblemsDTO.setAuthor(p2pPageproblems.getAuthor());
					pageproblemsDTO.setProblemsContent(p2pPageproblems.getProblemsContent());
					pageproblemsDTO.setParentOID(p2pPageproblems.getParentOID());
					//判断非空
					if(p2pPageproblems.getEffectiveTime()!=null){
						pageproblemsDTO.setEffectiveTime(DateUtils.formatDate(p2pPageproblems.getEffectiveTime(),""));
					}
					return pageproblemsDTO;
				}
			}
			return null;
		}catch(Exception e){
			logger.error("findProblemById()", e.getMessage());
			return null;
		}
	}
	
	/*************************************************关于我们***************************************************/
	/**
     * 获取关于我们-平台公告信息列表
     * @param currentPage 页数
     * @return
     */
	@Override
	public Map<String, Object> findNotices(Integer currentPage) {
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
		    int rows = contentNoticeDao.getRowCount();
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
		    List<P2pNotice> select = this.contentNoticeDao.findNoticeByPage(startIndex, pageSize);
		    if(select != null && select.size()>0){
		    	for (P2pNotice pmn : select) {
					// 获取图片的id
					String ImgOID = pmn.getUploadImgOID();
					if (StringUtil.isNotEmpty(ImgOID)) {
					    FileInfo file = fileInfoDao.findPageByOID(ImgOID); // 图片的id
					    PageNewsDTO dto = new PageNewsDTO();
					    // 标题名字
					    dto.setTitleName(pmn.getName());
					    // 作者
					    dto.setAuthor(pmn.getAuthor());
					    // 图片的OID
					    dto.setImgOID(ImgOID);
					    // 图片名字
					    dto.setName(file.getName());
					    // 路径
					    dto.setSource(file.getSource());
					    // 内容OID
					    dto.setOID(pmn.getOID());
					    if(pmn.getAddDateTime() != null){
					    	//时间格式yyyy-MM-dd HH:mm:ss
						    String addDate = DateUtils.formatDate(pmn.getAddDateTime(), "");
						    dto.setAddDateTime(addDate);
						    //时间格式yyyy-MM-dd
						    String addTime = DateUtils.formatJustDate(pmn.getAddDateTime().getTime());
						    dto.setAddTime(addTime);
					    }
					    noticeList.add(dto);
					}
			    }
			    map.put("noticeList", noticeList);
			    map.put("Pagination", pageController);
			    return map;
		    }
		    return null;
		} catch (Exception e) {
		    logger.error("findNotices(String parentOID,Integer currentPage)" + e.getMessage());
		    return null;
		}
	}
    
    /**
     * 添加一条公告
     * @param pageNewsDTO 公告DTOBean
     * @return 
     */
	@Override
	public String addNotice(PageNewsDTO pageNewsDTO) {
		try{
			//必要参数验证非空
			if (StringUtil.isNotEmpty(pageNewsDTO.getName()) && StringUtil.isNotEmpty(pageNewsDTO.getParentOID()) 
				&& StringUtil.isNotEmpty(pageNewsDTO.getMessages()) && StringUtil.isNotEmpty(pageNewsDTO.getImgOID())) {
				P2pNotice p2pNotice = new P2pNotice();
				p2pNotice.setName(pageNewsDTO.getName());				//标题
				p2pNotice.setAuthor(pageNewsDTO.getAuthor());			//作者
				p2pNotice.setMessages(pageNewsDTO.getMessages());		//内容
				p2pNotice.setParentOID(pageNewsDTO.getParentOID());		//类型id
				p2pNotice.setUploadImgOID(pageNewsDTO.getImgOID());		//图片oid
				String OID = StringUtil.getUUID();
				p2pNotice.setOID(OID);         							//id
				p2pNotice.setValid(true);      							//是否有效
				p2pNotice.setRefresh(true);    							//是否更新
				p2pNotice.setVersion(0);       							//版本号
				Integer num = contentNoticeDao.addNotice(p2pNotice);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("addNotice()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 删除一条公告
     * @param OID 公告id
     * @return 
     */
	@Override
	public String deleteNotice(String OID) {
		try{
			if(StringUtil.isNotEmpty(OID)){
				Integer num = contentNoticeDao.deleteNotice(OID);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("deleteNotice()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 修改一条公告
     * @param pageNewsDTO 公告DTOBean
     * @return 
     */
	@Override
	public String updateNotice(PageNewsDTO pageNewsDTO) {
		try{
			if(StringUtil.isNotEmpty(pageNewsDTO.getOID())&& StringUtil.isNotEmpty(pageNewsDTO.getParentOID())){
				//根据id查询原数据
				P2pNotice p2pNotice = contentNoticeDao.selectByPrimaryKey(pageNewsDTO.getOID());
				if(p2pNotice != null){
					//更改数据赋值
					if(pageNewsDTO.getName() != null){ 
						p2pNotice.setName(pageNewsDTO.getName());  				//公告名称
					}
					if(pageNewsDTO.getMessages() != null){
						p2pNotice.setMessages(pageNewsDTO.getMessages()); 		//公告内容
					}
					if(pageNewsDTO.getParentOID() != null){
						p2pNotice.setParentOID(pageNewsDTO.getParentOID());  	//父级OID
					}
					if(pageNewsDTO.getImgOID() != null){
						p2pNotice.setUploadImgOID(pageNewsDTO.getImgOID());  	//上传图片OID
					}
					//更新表
					Integer num = contentNoticeDao.updateNotice(p2pNotice);
					return num.toString();
				}
			}
			return null;
		}catch(Exception e){
			logger.error("updateNotice()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 根据OID查询公告具体内容
     * @param OID 公告id
     * @return
     */
    @Override
    public PageNewsDTO findNoticeByOID(String OID) {
		try {
		    if (StringUtil.isNotEmpty(OID)) {
				P2pNotice pnc = contentNoticeDao.findNoticeByOID(OID);
				if (pnc != null) {
				    PageNewsDTO dto = new PageNewsDTO();
				    if(pnc.getAddDateTime() != null){
				    	String addDateTime = DateUtils.formatDate(pnc.getAddDateTime(), "");
					    String addTime = DateUtils.formatJustDate(pnc.getAddDateTime().getTime());
					    dto.setAddDateTime(addDateTime);
					    dto.setAddTime(addTime);
				    }
				    dto.setMessages(pnc.getMessages());
				    dto.setTitleName(pnc.getName());
				    dto.setAuthor(pnc.getAuthor());
				    dto.setImgOID(pnc.getUploadImgOID());
				    return dto;
				}
		    }
			return null;
		} catch (Exception e) {
		    logger.error("findNoticeByOID(String OID)" + e.getMessage());
		    return null;
		}
    }
    
    /**
     * 获取关于我们-砖头动态信息列表
     * @param currentPage 页数
     * @return
     */
	@Override
	public Map<String, Object> findDynamicNews(Integer currentPage) {
		try {
		    // 存放图片和信息内容
		    List<PageNewsDTO> noticeList = new ArrayList<PageNewsDTO>();
		    // 分页信息
		    Pagination<P2pToutiaoNews> pageController = new Pagination<>();
		    // 返回分页信息和内容
		    Map<String, Object> map = new HashMap<String, Object>();
		    /** 接受前台当前页参数 */
		    if (currentPage != null) {
			pageController.setCurrentPage(currentPage);
		    }
		    /** 获取总数 */
		    int rows = contentDynamicNewsDao.getRowCount();
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
		    List<P2pToutiaoNews> select = this.contentDynamicNewsDao.findDynamicNewsByPage(startIndex, pageSize);
		    if(select != null && select.size()>0){
		    	for (P2pToutiaoNews pmn : select) {
		  			// 获取图片的id
		  			String ImgOID = pmn.getUploadImgOID();
	  				if (StringUtil.isNotEmpty(ImgOID)) {
	  				    FileInfo file = fileInfoDao.findPageByOID(ImgOID); // 图片的id
	  				    PageNewsDTO dto = new PageNewsDTO();
	  				    // 标题名字
	  				    dto.setTitleName(pmn.getName());
	  				    //作者
	  				    dto.setAuthor(pmn.getAuthor());
	  				    // 图片的OID
	  				    dto.setImgOID(ImgOID);
	  				    // 图片名字
	  				    dto.setName(file.getName());
	  				    // 路径
	  				    dto.setSource(file.getSource());
	  				    // 内容OID
	  				    dto.setOID(pmn.getOID());
	  				    if(pmn.getAddDateTime() != null){
	  				    //时间格式yyyy-MM-dd HH:mm:ss
		  				    String addDate = DateUtils.formatDate(pmn.getAddDateTime(), "");
		  				    dto.setAddDateTime(addDate);
		  				    //时间格式yyyy-MM-dd
		  				    String addTime = DateUtils.formatJustDate(pmn.getAddDateTime().getTime());
		  				    dto.setAddTime(addTime);
	  				    }
	  				    noticeList.add(dto);
	  				}
	  		    }
	  		    map.put("noticeList", noticeList);
	  		    map.put("Pagination", pageController);
	  		    return map;
		    }
		  return null;
		} catch (Exception e) {
		    logger.error("findDynamicNews(String parentOID,Integer currentPage)" + e.getMessage());
		    return null;
		}
	}
    
    /**
     * 添加一条砖头动态
     * @param pageNewsDTO 砖头动态DTOBean
     * @return 
     */
	@Override
	public String addDynamicNews(PageNewsDTO pageNewsDTO) {
		try{
			//必要参数验证非空
			if (StringUtil.isNotEmpty(pageNewsDTO.getName()) && StringUtil.isNotEmpty(pageNewsDTO.getParentOID()) 
				&& StringUtil.isNotEmpty(pageNewsDTO.getMessages()) && StringUtil.isNotEmpty(pageNewsDTO.getImgOID())) {
				P2pToutiaoNews p2pToutiaoNews = new P2pToutiaoNews();
				p2pToutiaoNews.setName(pageNewsDTO.getName());					//标题
				p2pToutiaoNews.setAuthor(pageNewsDTO.getAuthor());				//作者
				p2pToutiaoNews.setMessages(pageNewsDTO.getMessages());			//内容
				p2pToutiaoNews.setParentOID(pageNewsDTO.getParentOID());		//类型id
				p2pToutiaoNews.setUploadImgOID(pageNewsDTO.getImgOID());		//图片oid
				String OID = StringUtil.getUUID();
				p2pToutiaoNews.setOID(OID);         							//id
				p2pToutiaoNews.setValid(true);      							//是否有效
				p2pToutiaoNews.setRefresh(true);    							//是否更新
				p2pToutiaoNews.setVersion(0);       							//版本号
				Integer num = contentDynamicNewsDao.addDynamicNews(p2pToutiaoNews);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("addDynamicNews()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 删除一条砖头动态
     * @param OID 砖头动态oid
     * @return 
     */
	@Override
	public String deleteDynamicNews(String OID) {
		try{
			if(StringUtil.isNotEmpty(OID)){
				Integer num = contentDynamicNewsDao.deleteDynamicNews(OID);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("deleteDynamicNews()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 修改一条砖头动态
     * @param pageNewsDTO 砖头动态DTOBean
     * @return 
     */
	@Override
	public String updateDynamicNews(PageNewsDTO pageNewsDTO) {
		try{
			if(StringUtil.isNotEmpty(pageNewsDTO.getOID())&& StringUtil.isNotEmpty(pageNewsDTO.getParentOID())){
				//根据id查询原数据
				P2pToutiaoNews P2pToutiaoNews = contentDynamicNewsDao.selectByPrimaryKey(pageNewsDTO.getOID());
				if(P2pToutiaoNews != null){
					//更改数据赋值
					if(pageNewsDTO.getName() != null){ 
						P2pToutiaoNews.setName(pageNewsDTO.getName());  			//公告名称
					}
					if(pageNewsDTO.getMessages() != null){
						P2pToutiaoNews.setMessages(pageNewsDTO.getMessages()); 		//公告内容
					}
					if(pageNewsDTO.getParentOID() != null){
						P2pToutiaoNews.setParentOID(pageNewsDTO.getParentOID());  	//父级OID
					}
					if(pageNewsDTO.getImgOID() != null){
						P2pToutiaoNews.setUploadImgOID(pageNewsDTO.getImgOID());  	//上传图片OID
					}
					//更新表
					Integer num = contentDynamicNewsDao.updateDynamicNews(P2pToutiaoNews);
					return num.toString();
				}
			}
			return null;
		}catch(Exception e){
			logger.error("updateDynamicNews()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 根据OID查询砖头动态具体内容
     * @param OID 砖头动态oid
     * @return
     */
    @Override
    public PageNewsDTO findDynamicNewsByOID(String OID) {
		try {
		    if (StringUtil.isNotEmpty(OID)) {
		    	P2pToutiaoNews pttn = contentDynamicNewsDao.findDynamicNewsByOID(OID);
				if (pttn != null) {
				    PageNewsDTO dto = new PageNewsDTO();
				    if(pttn.getAddDateTime() != null){
				    	String addDateTime = DateUtils.formatDate(pttn.getAddDateTime(), "");
					    String addTime = DateUtils.formatJustDate(pttn.getAddDateTime().getTime());
					    dto.setAddDateTime(addDateTime);
					    dto.setAddTime(addTime);
				    }
				    dto.setMessages(pttn.getMessages());
				    dto.setTitleName(pttn.getName());
				    dto.setAuthor(pttn.getAuthor());
				    dto.setImgOID(pttn.getUploadImgOID());
				    return dto;
				}
		    }
			return null;
		} catch (Exception e) {
		    logger.error("findDynamicNewsByOID(String OID)" + e.getMessage());
		    return null;
		}
    }
    
    /**
     * 获取关于我们-媒体报道信息列表
     * @param currentPage 页数
     * @return
     */
	@Override
	public Map<String, Object> findMediaNews(Integer currentPage) {
		try {
		    // 存放图片和信息内容
		    List<PageNewsDTO> noticeList = new ArrayList<PageNewsDTO>();
		    // 分页信息
		    Pagination<P2pMediaNews> pageController = new Pagination<>();
		    // 返回分页信息和内容
		    Map<String, Object> map = new HashMap<String, Object>();
		    /** 接受前台当前页参数 */
		    if (currentPage != null) {
			pageController.setCurrentPage(currentPage);
		    }
		    /** 获取总数 */
		    int rows = contentMediaNewsDao.getRowCount();
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
		    List<P2pMediaNews> select = this.contentMediaNewsDao.findMediaNewsByPage(startIndex, pageSize);
		    if(select != null && select.size()>0){
		    	for (P2pMediaNews pmn : select) {
					// 获取图片的id
					String ImgOID = pmn.getUploadImgOID();
					if (StringUtil.isNotEmpty(ImgOID)) {
					    FileInfo file = fileInfoDao.findPageByOID(ImgOID); // 图片的id
					    PageNewsDTO dto = new PageNewsDTO();
					    // 标题名字
					    dto.setTitleName(pmn.getName());
					    //作者
					    dto.setAuthor(pmn.getAuthor());
					    // 图片的OID
					    dto.setImgOID(ImgOID);
					    // 图片名字
					    dto.setName(file.getName());
					    // 路径
					    dto.setSource(file.getSource());
					    // 内容OID
					    dto.setOID(pmn.getOID());
					    if(pmn.getAddDateTime() != null){
					    	//时间格式yyyy-MM-dd HH:mm:ss
						    String addDate = DateUtils.formatDate(pmn.getAddDateTime(), "");
						    dto.setAddDateTime(addDate);
						    //时间格式yyyy-MM-dd
						    String addTime = DateUtils.formatJustDate(pmn.getAddDateTime().getTime());
						    dto.setAddTime(addTime);
					    }
					    noticeList.add(dto);
					}
			    }
			    map.put("noticeList", noticeList);
			    map.put("Pagination", pageController);
			    return map;
		    }
		    return null;
		} catch (Exception e) {
		    logger.error("findMediaNews(String parentOID,Integer currentPage)" + e.getMessage());
		    return null;
		}
	}
    
    /**
     * 添加一条媒体报道
     * @param pageNewsDTO 媒体报道DTOBean
     * @return 
     */
	@Override
	public String addMediaNews(PageNewsDTO pageNewsDTO) {
		try{
			//必要参数验证非空
			if (StringUtil.isNotEmpty(pageNewsDTO.getName()) && StringUtil.isNotEmpty(pageNewsDTO.getParentOID()) 
				&& StringUtil.isNotEmpty(pageNewsDTO.getMessages()) && StringUtil.isNotEmpty(pageNewsDTO.getImgOID())) {
				P2pMediaNews p2pMediaNews = new P2pMediaNews();
				p2pMediaNews.setName(pageNewsDTO.getName());					//标题
				p2pMediaNews.setAuthor(pageNewsDTO.getAuthor());				//作者
				p2pMediaNews.setMessages(pageNewsDTO.getMessages());			//内容
				p2pMediaNews.setParentOID(pageNewsDTO.getParentOID());			//类型id
				p2pMediaNews.setUploadImgOID(pageNewsDTO.getImgOID());			//图片oid
				String OID = StringUtil.getUUID();
				p2pMediaNews.setOID(OID);         								//id
				p2pMediaNews.setValid(true);      								//是否有效
				p2pMediaNews.setRefresh(true);    								//是否更新
				p2pMediaNews.setVersion(0);       								//版本号
				Integer num = contentMediaNewsDao.addMediaNews(p2pMediaNews);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("addMediaNews()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 删除一条媒体报道
     * @param OID 媒体报道oid
     * @return 
     */
	@Override
	public String deleteMediaNews(String OID) {
		try{
			if(StringUtil.isNotEmpty(OID)){
				Integer num = contentMediaNewsDao.deleteMediaNews(OID);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("deleteMediaNews()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 修改一条媒体报道
     * @param pageNewsDTO 媒体报道DTOBean
     * @return 
     */
	@Override
	public String updateMediaNews(PageNewsDTO pageNewsDTO) {
		try{
			if(StringUtil.isNotEmpty(pageNewsDTO.getOID())&& StringUtil.isNotEmpty(pageNewsDTO.getParentOID())){
				//根据id查询原数据
				P2pMediaNews p2pMediaNews = contentMediaNewsDao.findMediaNewsByOID(pageNewsDTO.getOID());
				if(p2pMediaNews != null){
					//更改数据赋值
					if(pageNewsDTO.getName() != null){ 
						p2pMediaNews.setName(pageNewsDTO.getName());  			//公告名称
					}
					if(pageNewsDTO.getMessages() != null){
						p2pMediaNews.setMessages(pageNewsDTO.getMessages()); 	//公告内容
					}
					if(pageNewsDTO.getParentOID() != null){
						p2pMediaNews.setParentOID(pageNewsDTO.getParentOID());  //父级OID
					}
					if(pageNewsDTO.getImgOID() != null){
						p2pMediaNews.setUploadImgOID(pageNewsDTO.getImgOID());  //上传图片OID
					}
					//更新表
					Integer num = contentMediaNewsDao.updateMediaNews(p2pMediaNews);
					return num.toString();
				}
			}
			return null;
		}catch(Exception e){
			logger.error("updateMediaNews()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 根据OID查询媒体报道具体内容
     * @param OID 媒体报道oid
     * @return
     */
    @Override
    public PageNewsDTO findMediaNewsByOID(String OID) {
		try {
		    if (StringUtil.isNotEmpty(OID)) {
		    	P2pMediaNews pmn = contentMediaNewsDao.findMediaNewsByOID(OID);
				if (pmn != null) {
				    PageNewsDTO dto = new PageNewsDTO();
				    if(pmn.getAddDateTime() != null){
				    	String addDateTime = DateUtils.formatDate(pmn.getAddDateTime(), "");
					    String addTime = DateUtils.formatJustDate(pmn.getAddDateTime().getTime());
					    dto.setAddDateTime(addDateTime);
					    dto.setAddTime(addTime);
				    }
				    dto.setMessages(pmn.getMessages());
				    dto.setTitleName(pmn.getName());
				    dto.setAuthor(pmn.getAuthor());
				    dto.setImgOID(pmn.getUploadImgOID());
				    return dto;
				}
		    }
			return null;
		} catch (Exception e) {
		    logger.error("findMediaNewsByOID(String OID)" + e.getMessage());
		    return null;
		}
    }
}
