package com.izhuantou.service.impl.contentManagement;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.p2p.P2pFlink;
import com.izhuantou.damain.p2p.P2pPageBanner;
import com.izhuantou.damain.vo.FlinkDTO;
import com.izhuantou.dao.p2p.P2pFlinkMapper;
import com.izhuantou.service.api.contentManagement.BottomLinkService;
import com.izhuantou.service.impl.BaseServiceImpl;
@Service("bottomLinkService")
public class BottomLinkServiceImpl extends BaseServiceImpl<P2pFlink> implements BottomLinkService {
	private static final Logger logger = LoggerFactory.getLogger(BottomLinkServiceImpl.class);
	@Autowired
	private P2pFlinkMapper BottomLinkDao;
	/**
	 * 获取底部链接信息列表
	 * @return 
	 */
	@Override
	public List<FlinkDTO> findBottomLink() {
		try {
		    List<FlinkDTO> Flinkdto = new ArrayList<FlinkDTO>();
		    List<P2pFlink> P2pFlink = BottomLinkDao.findFlinkList();
		    if (P2pFlink != null && P2pFlink.size()>0) {
				for (P2pFlink flink : P2pFlink) {
				    FlinkDTO dto = new FlinkDTO();
				    dto.setOID(flink.getOID());
				    dto.setName(flink.getName());
				    dto.setLinkUrl(flink.getLinkUrl());
				    dto.setNO(flink.getNO());
				    Flinkdto.add(dto);
				}
				return Flinkdto;
		    }
			return null;
		} catch (Exception e) {
		    logger.error(" findBottomLink(String parentOID)" + e.getMessage());
		    return null;
		}
	}
	
	/**
     * 添加一条底部链接
     * @param flinkDTO 底部链接DTOBean
     * @return 
     */
	@Override
	public String addBottomLink(FlinkDTO flinkDTO) {
		try{
			//必要参数验证非空
			if(StringUtil.isNotEmpty(flinkDTO.getName()) && StringUtil.isNotEmpty(flinkDTO.getLinkUrl())
					&& StringUtil.isNotEmpty(flinkDTO.getParentOID())){
				P2pFlink p2pFlink = new P2pFlink();
				String OID = StringUtil.getUUID();
				p2pFlink.setOID(OID);         						//id
				p2pFlink.setName(flinkDTO.getName());				//名称
				p2pFlink.setLinkUrl(flinkDTO.getLinkUrl());			//链接地址
				p2pFlink.setParentOID(flinkDTO.getParentOID());		//父类oid
				p2pFlink.setNO(flinkDTO.getNO());					//序号
				p2pFlink.setValid(true);      						//是否有效
				p2pFlink.setRefresh(true);    						//是否更新
				p2pFlink.setVersion(0);       						//版本号
				Integer num = BottomLinkDao.addBottomLink(p2pFlink);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("addBottomLink()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 删除一条底部链接
     * @param OID 底部链接oid
     * @return
     */
	@Override
	public String deleteBottomLink(String OID) {
		try{
			if(StringUtil.isNotEmpty(OID)){
				Integer num = BottomLinkDao.deleteBottomLink(OID);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("deleteBottomLink()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 修改一条底部链接
     * @param flinkDTO 底部链接DTOBean
     * @return 
     */
	@Override
	public String updateBottomLink(FlinkDTO flinkDTO) {
		try{
			if(StringUtil.isNotEmpty(flinkDTO.getOID()) && StringUtil.isNotEmpty(flinkDTO.getParentOID())){
				//根据id查询原数据
				P2pFlink p2pFlink = BottomLinkDao.findBottomLinkById(flinkDTO.getOID());
				if(p2pFlink != null){
					//更改数据赋值
					if(flinkDTO.getName() != null){ 
						p2pFlink.setName(flinkDTO.getName());  				//标题名称
					}
					if(flinkDTO.getLinkUrl() != null){
						p2pFlink.setLinkUrl(flinkDTO.getLinkUrl()); 		//跳转地址
					}
					if(flinkDTO.getParentOID() != null){
						p2pFlink.setParentOID(flinkDTO.getParentOID());  	//父级OID
					}
					if(flinkDTO.getNO() != null){
						p2pFlink.setNO(flinkDTO.getNO());  					//序号
					}
					//更新表
					Integer num = BottomLinkDao.updateBottomLink(p2pFlink);
					return num.toString();
				}
			}
			return null;
		}catch(Exception e){
			logger.error("updateBottomLink()", e.getMessage());
			return null;
		}
	}
}
