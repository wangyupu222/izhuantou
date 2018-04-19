package com.izhuantou.dao.mobile.PersonalMyLend;

import java.util.List;

import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileDDTYwcDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileHHTYwcDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileXinShouYwcDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileZZTYwcDTO;

/**
 * 个人中新我的出借已完成
 * 
 * @author Administrator
 *
 */
public interface MobilePersonalChuJieYWCMapper {
    /** 新手转已完成列表 */
    public List<MobileXinShouYwcDTO> findByMemberOID(String memberOID);

    /** 环环投已完成列表 */
    public List<MobileHHTYwcDTO> findByhhtMemberOID(String memberOID);

    /** 点点投查询所有数据(size>0证明有数据) */
    // public List<MobileDDTAllInfo> findAllDDT();

    /** 点点投已完成列表 */
    public List<MobileDDTYwcDTO> findByddtMembeOID(String memberOID);

    /** 转转投已完成列表 */
    public List<MobileZZTYwcDTO> findByzztMembeOID(String memberOID);
}
