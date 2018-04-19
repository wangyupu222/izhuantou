package com.izhuantou.dao.lend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.HHTLendRecord;

/**
 * 环环投出借记录
 * 
 * @author yangbosen
 *
 */
public interface HHTLendRecordMapper extends Mapper<HHTLendRecord> {
    /** 根据OID查找出借记录并分页 */
    public List<HHTLendRecord> findByOID(@Param(value = "OID") String OID, @Param(value = "startPos") Integer startPos,
	    @Param(value = "pageSize") Integer pageSize);

    /** 计算总记录数 */
    public Integer findCount(String OID);

    public List<HHTLendRecord> findList(String OID);

}
