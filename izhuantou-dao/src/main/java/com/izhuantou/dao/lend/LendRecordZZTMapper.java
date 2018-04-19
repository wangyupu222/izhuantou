package com.izhuantou.dao.lend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.LendRecord;

/**
 * 转转投出借记录
 * 
 * @author Administrator
 *
 */
public interface LendRecordZZTMapper extends Mapper<LendRecord> {

    public List<LendRecord> findByZZTBusinessOID(@Param(value = "businessOID") String businessOID,
	    @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    public Integer findZZTCount(String businessOID);

    public List<LendRecord> findList(String businessOID);
}
