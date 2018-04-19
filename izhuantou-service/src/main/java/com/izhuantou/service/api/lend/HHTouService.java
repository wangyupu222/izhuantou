package com.izhuantou.service.api.lend;

import java.util.List;

import com.izhuantou.damain.lend.HHTou;
import com.izhuantou.service.api.BaseService;

/**
 * 环环投接口
 * 
 * @author yangbosen
 *
 */
public interface HHTouService extends BaseService<HHTou> {
    /** 返回环环投信息结果集 */
    public List<HHTou> findResult();

}
