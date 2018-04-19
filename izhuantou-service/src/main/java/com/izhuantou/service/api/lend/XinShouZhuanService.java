package com.izhuantou.service.api.lend;

import com.izhuantou.damain.lend.XinShouZhuan;
import com.izhuantou.service.api.BaseService;

/**
 * 头笔赚接口
 * 
 * @author yangbosen
 *
 */
public interface XinShouZhuanService extends BaseService<XinShouZhuan> {
    /** 查找所有头笔赚信息 */
    public XinShouZhuan FindAll();

}
