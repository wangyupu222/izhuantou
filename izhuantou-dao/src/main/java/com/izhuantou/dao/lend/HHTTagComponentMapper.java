package com.izhuantou.dao.lend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.HHTTagComponent;

/**
 * 环环投标的组成
 * 
 * @author yangbosen
 *
 */
public interface HHTTagComponentMapper extends Mapper<HHTTagComponent> {

    public List<HHTTagComponent> findByOID(@Param(value = "OID") String OID,
	    @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    public Integer findCount(String OID);

}
