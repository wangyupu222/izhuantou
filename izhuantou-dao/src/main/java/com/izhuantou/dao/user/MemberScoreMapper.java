package com.izhuantou.dao.user;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.user.MemberScore;

public interface MemberScoreMapper extends Mapper<MemberScore>{
	
	/**
	 * 统计风险测评用户数
	 * @return
	 */
	int countTotal();

	/**
	 * 根据会员OID获取信息
	 * @param memberOID
	 * @return
	 */
	MemberScore selectByMemberOID(String memberOID);

	
}
