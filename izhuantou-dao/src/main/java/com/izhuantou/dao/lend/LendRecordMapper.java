package com.izhuantou.dao.lend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.LendRecord;

public interface LendRecordMapper extends Mapper<LendRecord> {
    /** 出借记录(原标) */
    public List<LendRecord> findByBusinessOID(@Param(value = "BusinessOID") String BussinessOID,
	    @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    public Integer findCount(String BussinessOID);

    /** 出借记录(债转) */
    public List<LendRecord> findByBusinessZCOID(@Param(value = "BusinessOID") String BussinessOID,
	    @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    public Integer findCountZC(String BussinessOID);

    // 查找所有出借记录 不进行分页 暂时用于移动端(债转)
    public List<LendRecord> findList(String BussinessOID);

}
