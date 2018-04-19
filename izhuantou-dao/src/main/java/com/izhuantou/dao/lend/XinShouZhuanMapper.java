package com.izhuantou.dao.lend;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.XinShouZhuan;

/**
 * 头笔赚Dao层
 * 
 * @author yangbosen
 *
 */
public interface XinShouZhuanMapper extends Mapper<XinShouZhuan> {
    /** 查找新手赚具体信息 */
    public XinShouZhuan FindAll();

}
