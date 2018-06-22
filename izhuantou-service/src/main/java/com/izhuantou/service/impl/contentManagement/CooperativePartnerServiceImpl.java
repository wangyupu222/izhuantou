package com.izhuantou.service.impl.contentManagement;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.utils.StringUtil;
import com.izhuantou.damain.p2p.FileInfo;
import com.izhuantou.damain.p2p.P2pCooperativePartner;
import com.izhuantou.damain.vo.CooperativePartnerDTO;
import com.izhuantou.dao.contentManagement.ContentCooperativePartnerMapper;
import com.izhuantou.dao.p2p.FileInfoMapper;
import com.izhuantou.service.api.contentManagement.CooperativePartnerService;
import com.izhuantou.service.impl.BaseServiceImpl;
@Service("cooperativePartnerService")
public class CooperativePartnerServiceImpl implements CooperativePartnerService {
	private static final Logger logger = LoggerFactory.getLogger(CooperativePartnerServiceImpl.class);
	@Autowired
	private ContentCooperativePartnerMapper contentCooperativePartnerDao;
	@Autowired
    private FileInfoMapper fileInfoDao;
	/**
	 * 获取合作伙伴信息列表
	 * @return 
	 */
	@Override
	public List<CooperativePartnerDTO> findCooperativePartner() {
		List<CooperativePartnerDTO> cooperativePartnerList = new ArrayList<CooperativePartnerDTO>();
		try {
		    List<P2pCooperativePartner> p2pcooperativePartnerList = contentCooperativePartnerDao.findCooperativePartner();
		    if (p2pcooperativePartnerList != null && p2pcooperativePartnerList.size()>0) {
				for (P2pCooperativePartner p2cp : p2pcooperativePartnerList) {
					CooperativePartnerDTO cpdto = new CooperativePartnerDTO();
			    	cpdto.setLinkUrl(p2cp.getLinkUrl());           	// 合作伙伴跳转的地址
			    	cpdto.setName(p2cp.getName());         	   	   	// 合作伙伴名字
			    	cpdto.setOID(p2cp.getOID());         		   	// oid
			    	cpdto.setPicimgOID(p2cp.getPicimgOID()); 		// 图片oid
					cooperativePartnerList.add(cpdto);
				}
				return cooperativePartnerList;
			}
		    return null;
		} catch (Exception e) {
		    logger.error("findCooperativePartner()" + e.getMessage());
		    return null;
		}
	}
	
	/**
     * 添加一条合作伙伴
     * @param cooperativePartnerDTO 合作伙伴DTOBean
     * @return 
     */
	@Override
	public String addCooperativePartner(CooperativePartnerDTO cooperativePartnerDTO) {
		try{
			//必要参数验证非空
			if(StringUtil.isNotEmpty(cooperativePartnerDTO.getName()) && StringUtil.isNotEmpty(cooperativePartnerDTO.getLinkUrl())
					&& StringUtil.isNotEmpty(cooperativePartnerDTO.getPicimgOID()) && StringUtil.isNotEmpty(cooperativePartnerDTO.getParentOID())){
				P2pCooperativePartner p2pCooperativePartner = new P2pCooperativePartner();
				String OID = StringUtil.getUUID();
				p2pCooperativePartner.setOID(OID);         									//id
				p2pCooperativePartner.setName(cooperativePartnerDTO.getName());            	//名称
				p2pCooperativePartner.setLinkUrl(cooperativePartnerDTO.getLinkUrl());		//链接地址 
				p2pCooperativePartner.setPicimgOID(cooperativePartnerDTO.getPicimgOID());   //图片oid
				p2pCooperativePartner.setParentOID(cooperativePartnerDTO.getParentOID());	//父类oid
				p2pCooperativePartner.setNO(cooperativePartnerDTO.getNO());					//序号
				p2pCooperativePartner.setValid(true);      									//是否有效
				p2pCooperativePartner.setRefresh(true);    									//是否更新
				p2pCooperativePartner.setVersion(0);       									//版本号
				Integer num = contentCooperativePartnerDao.addCooperativePartner(p2pCooperativePartner);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("addCooperativePartner()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 删除一条合作伙伴
     * @param OID 合作伙伴oid
     * @return
     */
	@Override
	public String deleteCooperativePartner(String OID) {
		try{
			if(StringUtil.isNotEmpty(OID)){
				Integer num = contentCooperativePartnerDao.deleteCooperativePartner(OID);
				return num.toString();
			}
			return null;
		}catch(Exception e){
			logger.error("deleteCooperativePartner()", e.getMessage());
			return null;
		}
	}
	
	/**
     * 修改一条合作伙伴
     * @param cooperativePartnerDTO 合作伙伴DTOBean
     * @return 
     */
	@Override
	public String updateCooperativePartner(CooperativePartnerDTO cooperativePartnerDTO) {
		try{
			if(StringUtil.isNotEmpty(cooperativePartnerDTO.getOID())&& StringUtil.isNotEmpty(cooperativePartnerDTO.getParentOID())){
				//根据id查询原数据
				P2pCooperativePartner p2pCooperativePartner = contentCooperativePartnerDao.findCooperativePartnerByOID(cooperativePartnerDTO.getOID());
				if(p2pCooperativePartner != null){
					//更改数据赋值
					if(cooperativePartnerDTO.getName() != null){ 
						p2pCooperativePartner.setName(cooperativePartnerDTO.getName());  			//标题名称
					}
					if(cooperativePartnerDTO.getLinkUrl() != null){
						p2pCooperativePartner.setLinkUrl(cooperativePartnerDTO.getLinkUrl()); 		//链接地址
					}
					if(cooperativePartnerDTO.getPicimgOID() != null){
						p2pCooperativePartner.setPicimgOID(cooperativePartnerDTO.getPicimgOID());  	//图片oid
					}
					if(cooperativePartnerDTO.getParentOID() != null){
						p2pCooperativePartner.setParentOID(cooperativePartnerDTO.getParentOID());  	//父级OID
					}
					if(cooperativePartnerDTO.getNO() != null){
						p2pCooperativePartner.setNO(cooperativePartnerDTO.getNO());  				//序号
					}
					//更新表
					Integer num = contentCooperativePartnerDao.updateCooperativePartner(p2pCooperativePartner);
					return num.toString();
				}
			}
			return null;
		}catch(Exception e){
			logger.error("updateCooperativePartner()", e.getMessage());
			return null;
		}
	}
}
