package com.izhuantou.service.api.mobile.mobilePersonalCenter;

import java.util.List;

import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendCYZDTO;
import com.izhuantou.damain.mobile.personalCenter.myLendDetial.MobileMyLendYWCDTO;

public interface MobileMyLendListService {
    /** 查找出借中列表 */
    public List<MobileMyLendCYZDTO> LendCYZList(String memberOID);

    /** 查找出借已完成中列表 */
    public List<MobileMyLendYWCDTO> LendYWCList(String memberOID);

}
