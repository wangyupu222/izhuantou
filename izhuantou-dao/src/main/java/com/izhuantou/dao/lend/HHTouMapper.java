package com.izhuantou.dao.lend;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.HHTou;

/**
 * 环环投Dao层
 * 
 * @author yangbosen
 *
 */
public interface HHTouMapper extends Mapper<HHTou> {
    /** 查找环环投信息 */
    public List<HHTou> findAll();

    /** 根据OID查找具体利率信息 */
    public HHTou findByOID(String OID);

}
