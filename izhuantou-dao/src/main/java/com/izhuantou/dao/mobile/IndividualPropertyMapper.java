package com.izhuantou.dao.mobile;

import java.util.List;

import com.izhuantou.damain.vo.MylendDDTDaoDTO;
import com.izhuantou.damain.vo.MylendDaoDTO;
import com.izhuantou.damain.vo.MylendZZTDaoDTO;

/**
 * 个人资产
 * 
 * @author Administrator
 *
 */
public interface IndividualPropertyMapper {
    /**
     * app点点持有投列表
     * 
     * @return
     */
    public List<MylendDDTDaoDTO> findAppfindDDThavingBymemberOID(String memberOID);

    /**
     * app点点投完成列表
     * 
     * @param memberOID
     * @return
     */
    public List<MylendDDTDaoDTO> findAppDDTWCBymemberOID(String memberOID);

    /**
     * app环环投持有列表
     * 
     * @param memberOID
     * @return
     */
    public List<MylendDaoDTO> findAppHHhavingBymemberOID(String memberOID);

    /**
     * app环环完成列表
     * 
     * @param memberOID
     * @return
     */
    public List<MylendDaoDTO> findAppHHTWCBymemberOID(String memberOID);

    /**
     * 转转头持有列表
     * 
     * @param memberOID
     * @return
     */
    public List<MylendZZTDaoDTO> findAppZZThavingBymemberOID(String memberOID);

    /**
     * 转转投完成列表
     * 
     * @param memberOID
     * @return
     */
    public List<MylendZZTDaoDTO> findAppZZTWCBymemberOID(String memberOID);

}
