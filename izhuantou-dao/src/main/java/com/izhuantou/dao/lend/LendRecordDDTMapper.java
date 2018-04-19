package com.izhuantou.dao.lend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.LendRecordDDT;

/**
 * 点点投出借记录
 * 
 * @author yangbosen
 *
 */
public interface LendRecordDDTMapper extends Mapper<LendRecordDDT> {

    public List<LendRecordDDT> findByBusinessOID(@Param(value = "BusinessOID") String BussinessOID,
	    @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    public Integer findCount(String BussinessOID);

    public List<LendRecordDDT> findList(String BussinessOID);
}
