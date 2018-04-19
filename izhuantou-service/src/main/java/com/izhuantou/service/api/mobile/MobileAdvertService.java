package com.izhuantou.service.api.mobile;

import java.util.List;

import com.izhuantou.damain.mobile.MobileAdvert;

public interface MobileAdvertService {
    // type0,status1
    public List<MobileAdvert> FindAppggy();

    // 1,1
    public List<MobileAdvert> FindApptc();
}
