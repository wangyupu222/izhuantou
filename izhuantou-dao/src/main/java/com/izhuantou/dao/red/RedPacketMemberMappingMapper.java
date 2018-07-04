package com.izhuantou.dao.red;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.red.RedPacketMemberMapping;

public interface RedPacketMemberMappingMapper extends Mapper<RedPacketMemberMapping> {
	/**
	 * 修改红包状态为已使用
	 * @param redOidList
	 */
	void updateIsUsed(List<String> redOidList);
	
	/**
	 * 通过OID获取信息
	 * @param redOid
	 * @return
	 */
	RedPacketMemberMapping selectByOid(@Param("oid")String redOid);

}
