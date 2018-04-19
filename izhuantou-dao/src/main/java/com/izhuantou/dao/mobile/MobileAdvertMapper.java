package com.izhuantou.dao.mobile;

import java.util.List;

import com.izhuantou.damain.mobile.MobileAdvert;

/**
 * 移动端广告
 * 
 * @author yangbosen
 *
 */
public interface MobileAdvertMapper {
    // 状态和类型都是1
    public List<MobileAdvert> findListByType();

    // 类型是0状态是1
    public List<MobileAdvert> findByStatus();
}
