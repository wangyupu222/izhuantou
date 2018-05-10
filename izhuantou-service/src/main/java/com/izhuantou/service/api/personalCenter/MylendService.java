package com.izhuantou.service.api.personalCenter;

import java.util.List;
import java.util.Map;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.vo.HHThavingDetailsDTO;
import com.izhuantou.damain.vo.MylendDaoDTO;
import com.izhuantou.damain.vo.MylendResultDTO;
import com.izhuantou.damain.vo.MylendZQZRDTO;

public interface MylendService {
    /**
     * 查询用户持有环环投列表
     * 
     * @return
     */
    public Pagination<MylendDaoDTO> findHHhavingBymemberOID(String memberOID, Integer currentPage);

    /**
     * 查询用户持有环环投列表详情
     * 
     * @param cashPoolOID
     * @return
     */
    public List<HHThavingDetailsDTO> findHHhavingDetails(String cashPoolOID);

    /**
     * 已完成环环投列表
     * 
     * @param memberOID
     * @return
     */
    public Map<String, Object> findHHTWCBymemberOID(String memberOID, Integer currentPage);

    /**
     * 用户持有头笔赚列表
     * 
     * @param memberOID
     * @return
     */
    public List<MylendResultDTO> findTBZHavingBymemberOID(String memberOID);

    /**
     * 用户头笔赚完成列表
     * 
     * @param memberOID
     * @return
     */
    public List<MylendResultDTO> findTBZWCBymemberOID(String memberOID);

    /**
     * 点点投持有列表
     * 
     * @param memberOID
     * @return
     */
    public Map<String, Object> findDDTBymemberOID(String memberOID, Integer currentPage);

    /**
     * 点点投持有完成列表
     * 
     * @param memberOID
     * @return
     */
    public Map<String, Object> findDDTWCBymemberOID(String memberOID, Integer currentPage);

    /**
     * 债权转让可转让列表
     * 
     * @param memberOID
     * @return
     */
    public Map<String, Object> findZQKZBymemberOID(String memberOID, Integer currentPage);

    /**
     * 债权转让已完成列表
     * 
     * @param memberOID
     * @return
     */
    public Map<String, Object> findZQWCBymemberOID(String memberOID, Integer currentPage);

    /**
     * 点击申请获得申请的信息展示
     * 
     * @param memberOID
     * @param PaydebitOID
     * @return
     */
    public MylendZQZRDTO findZQSQBymemberOIDAndPaydebitOID(String memberOID, String PaydebitOID, String businessOID);

    /**
     * 确认债权转让申请
     * 
     * @return
     */
    public String ZQZRBoxsave(MylendZQZRDTO mylendZQZRDTO);

}
