package com.izhuantou.dao.lend;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.DDTou;

/**
 * 点点投Dao层
 * 
 * @author yangbosen
 *
 */
public interface DDTouMapper extends Mapper<DDTou> {
    /** 查询基础信息 */
    public List<DDTou> findAll();

    /** 根据OID查询具体信息 */
    public DDTou findByOID(String OID);

}
