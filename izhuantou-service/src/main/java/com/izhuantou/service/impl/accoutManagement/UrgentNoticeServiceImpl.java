package com.izhuantou.service.impl.accoutManagement;

import java.util.ArrayList;
import java.util.Date;
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
import com.izhuantou.damain.p2p.P2pUrgentNotice;
import com.izhuantou.damain.vo.UrgentNoticeDTO;
import com.izhuantou.dao.accoutManagement.AccoutUrgentNoticeMapper;
import com.izhuantou.dao.p2p.FileInfoMapper;
import com.izhuantou.service.api.accoutManagement.UrgentNoticeService;
import com.izhuantou.service.impl.BaseServiceImpl;
@Service("urgentNoticeService")
public class UrgentNoticeServiceImpl extends BaseServiceImpl<P2pUrgentNotice> implements UrgentNoticeService {
	private static final Logger logger = LoggerFactory.getLogger(UrgentNoticeServiceImpl.class);
	@Autowired
	private AccoutUrgentNoticeMapper accoutUrgentNoticeMapper;
	@Autowired
    private FileInfoMapper fileInfoDao;
	
	/**
	 * 根据条件查询紧急公告列表
	 * @param currentPage 页数
     * @param urgentNoticeDTO 紧急公告DTOBean
	 */
	@Override
	public Map<String,Object> findUrgentNotice(Integer currentPage,UrgentNoticeDTO urgentNoticeDTO){
		//返回分页信息和内容
		Map<String,Object> map = new HashMap<String,Object>();
		//UrgentNoticeDTO集合
		List<UrgentNoticeDTO> urgentNoticeDTOList = new ArrayList<UrgentNoticeDTO>();
		try{
			//日期String转换为Date类型
			/*Date startTimeDate = null;
			Date endTimeDate = null;
			if(StringUtil.isNotEmpty(urgentNoticeDTO.getStartTime())){
				startTimeDate = DateUtils.getDate(urgentNoticeDTO.getStartTime(), "");
			}
			if(StringUtil.isNotEmpty(urgentNoticeDTO.getEndTime())){
				endTimeDate = DateUtils.getDate(urgentNoticeDTO.getEndTime(), "");
			}*/
			//分页信息
			Pagination<P2pUrgentNotice> pageUrgentNotices = new Pagination<>();
			if(currentPage != null){
				pageUrgentNotices.setCurrentPage(currentPage);
			}
			//获取问题总数
			int rows = accoutUrgentNoticeMapper.getRowCount(urgentNoticeDTO);
			pageUrgentNotices.setTotalNumber(rows);
			//设置每页显示条数
			pageUrgentNotices.setPageSize(10);
			//获取总页数
			Integer totalPage = pageUrgentNotices.getTotalPage();
			//起始位置
			Integer startIndex = (pageUrgentNotices.getCurrentPage()-1)*pageUrgentNotices.getPageSize();
			//每页条数
			Integer pageSize = pageUrgentNotices.getPageSize();
			//P2pUrgentNotice集合
			List<P2pUrgentNotice> p2pUrgentNoticeList = accoutUrgentNoticeMapper.findUrgentNotice(urgentNoticeDTO,startIndex, pageSize);
			if (p2pUrgentNoticeList != null && p2pUrgentNoticeList.size() > 0) {
				for (P2pUrgentNotice p2pUn : p2pUrgentNoticeList) {
					UrgentNoticeDTO unDTO = new UrgentNoticeDTO();
					unDTO.setOID(p2pUn.getOID());  									     //OID
					unDTO.setName(p2pUn.getName());            							 //公告标题
					unDTO.setMessages(p2pUn.getMessages());								 //公告内容
					unDTO.setAuthor(p2pUn.getAuthor());									 //作者
					unDTO.setType(p2pUn.getType());										 //类型
					unDTO.setStartTime(DateUtils.formatDate(p2pUn.getStartTime(),""));   //发布起始时间
					unDTO.setEndTime(DateUtils.formatDate(p2pUn.getEndTime(),""));		 //发布结束时间
					urgentNoticeDTOList.add(unDTO);
				}
				map.put("urgentNoticeDTOList", urgentNoticeDTOList);
				map.put("Pagination", pageUrgentNotices);
				return map;
			}
		    return null;
		} catch (Exception e){
			logger.error("findUrgentNoticeByTypes"+e.getMessage());
			return null;
		}
	}
	
	/**
     * 添加一条紧急公告
     * @param UrgentNoticeDTO 紧急公告bean
     * @return 
     */
	@Override
	public String addUrgentNotice(UrgentNoticeDTO urgentNoticeDTO) {
		try{
			//必要参数验证非空
			if(StringUtil.isNotEmpty(urgentNoticeDTO.getName()) && StringUtil.isNotEmpty(urgentNoticeDTO.getMessages())
					&& StringUtil.isNotEmpty(urgentNoticeDTO.getType()) && StringUtil.isNotEmpty(urgentNoticeDTO.getStartTime())
					&& StringUtil.isNotEmpty(urgentNoticeDTO.getEndTime())){
				P2pUrgentNotice p2pUrgentNotice = new P2pUrgentNotice();
				p2pUrgentNotice.setName(urgentNoticeDTO.getName());   									//公告标题
				p2pUrgentNotice.setMessages(urgentNoticeDTO.getMessages());								//公告内容
				p2pUrgentNotice.setAuthor(urgentNoticeDTO.getAuthor());									//作者
				p2pUrgentNotice.setType(urgentNoticeDTO.getType());										//类型
				p2pUrgentNotice.setStartTime(DateUtils.getDate(urgentNoticeDTO.getStartTime(), ""));	//发布起始时间
				p2pUrgentNotice.setEndTime(DateUtils.getDate(urgentNoticeDTO.getEndTime(), ""));		//发布结束时间
				p2pUrgentNotice.setParentOID(urgentNoticeDTO.getParentOID()); 							//parentOID
				String OID = StringUtil.getUUID();
				p2pUrgentNotice.setOID(OID);         													//OID
				p2pUrgentNotice.setValid(true);      													//是否有效
				p2pUrgentNotice.setRefresh(true);    													//是否更新
				p2pUrgentNotice.setVersion(0);       													//版本号
				Integer num = accoutUrgentNoticeMapper.addUrgentNotice(p2pUrgentNotice);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("addUrgentNotice()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 删除一条紧急公告
     * @param OID 紧急公告oid
     * @return
     */
	@Override
	public String deleteUrgentNotice(String OID) {
		try{
			if(StringUtil.isNotEmpty(OID)){
				Integer num = accoutUrgentNoticeMapper.deleteUrgentNotice(OID);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("deleteUrgentNotice()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 修改一条紧急公告
     * @param UrgentNoticeDTO 紧急公告bean
     * @return 
     */
	@Override
	public String updateUrgentNotice(UrgentNoticeDTO urgentNoticeDTO) {
		try{
			if(StringUtil.isNotEmpty(urgentNoticeDTO.getOID())){
				//根据id查询原数据
				P2pUrgentNotice p2pUrgentNotice = accoutUrgentNoticeMapper.findUrgentNoticeByOID(urgentNoticeDTO.getOID());
				if(p2pUrgentNotice!=null){
					//更改数据赋值
					if(urgentNoticeDTO.getName() != null){ 
						p2pUrgentNotice.setName(urgentNoticeDTO.getName());  								//公告标题
					}
					if(urgentNoticeDTO.getMessages() != null){
						p2pUrgentNotice.setMessages(urgentNoticeDTO.getMessages()); 						//公告内容
					}
					if(urgentNoticeDTO.getType() != null){
						p2pUrgentNotice.setType(urgentNoticeDTO.getType());  								//类型
					}
					if(urgentNoticeDTO.getStartTime() != null){
						p2pUrgentNotice.setStartTime(DateUtils.getDate(urgentNoticeDTO.getStartTime(), ""));//发布起始时间
					}
					if(urgentNoticeDTO.getEndTime() != null){
						p2pUrgentNotice.setEndTime(DateUtils.getDate(urgentNoticeDTO.getEndTime(), ""));  	//发布结束时间
					}
					if(urgentNoticeDTO.getParentOID() != null){
						p2pUrgentNotice.setParentOID(urgentNoticeDTO.getParentOID());						//parentOID
					}
					//更新表
					Integer num = accoutUrgentNoticeMapper.updateUrgentNotice(p2pUrgentNotice);
					return num.toString();
				}
			}
			return null;
		}catch(Exception e){
			logger.error("updateUrgentNotice()", e.getMessage());
			return null;
		}
	}
}
