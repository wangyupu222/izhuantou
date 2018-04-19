package com.izhuantou.service.api.lend;

import java.util.List;

import com.izhuantou.damain.lend.DDTou;
import com.izhuantou.service.api.BaseService;

/**
 * 点点投接口
 * 
 * @author yangbosen
 *
 */
public interface DDTouService extends BaseService<DDTou> {
    /** 点点投结果集 */
    public List<DDTou> findResult();

}
