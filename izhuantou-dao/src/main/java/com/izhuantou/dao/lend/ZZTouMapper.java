package com.izhuantou.dao.lend;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.ZZTou;

/**
 * 转转投DAO层
 * 
 * @author yangbosen
 *
 */
public interface ZZTouMapper extends Mapper<ZZTou> {
    /** 查找基本信息 */
    public List<ZZTou> findAll();

    /** 根据OID查询利率等信息 */
    public ZZTou findByOID(String OID);

}
