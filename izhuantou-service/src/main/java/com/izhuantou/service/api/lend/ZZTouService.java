package com.izhuantou.service.api.lend;

import java.util.List;

import com.izhuantou.damain.lend.ZZTou;
import com.izhuantou.service.api.BaseService;

/**
 * 转转投接口
 * 
 * @author yangbosen
 *
 */
public interface ZZTouService extends BaseService<ZZTou> {
    /** 返回转转投信息结果集 */
    public List<ZZTou> findResult();

}
