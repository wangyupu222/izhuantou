package com.izhuantou.dao.mobile.PersonalMyLend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileDDTAllInfo;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileDDTCyzDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileHHTCyzDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileXinShouCyzDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileXinShouListDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileZZTCyzDTO;

/**
 * 个人中新我的出借持有中
 * 
 * @author Administrator
 *
 */
public interface MobilePersonalChuJieCYZMapper {
    /** 新手转持有中列表 */
    public List<MobileXinShouListDTO> findByMemberOID(String memberOID);

    /** 新手转持有中债转列表 */
    public List<MobileXinShouCyzDTO> findByOutMemberOID(@Param("memberOID") String memberOID,
	    @Param("state") String state);

    /** 环环投持有中列表 */
    public List<MobileHHTCyzDTO> findByhhtMemberOID(String memberOID);

    /** 点点投查询所有数据(size>0证明有数据) */
    public List<MobileDDTAllInfo> findAllDDT();

    /** 点点投持有中列表 */
    public List<MobileDDTCyzDTO> findByddtMembeOID(String memberOID);

    /** 转转投持有中列表 */
    public List<MobileZZTCyzDTO> findByzztMembeOID(String memberOID);
}
