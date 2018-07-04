package com.izhuantou.dao.user;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.user.MemberBlackList;

public interface MemberBlackListMapper extends Mapper<MemberBlackList>{
	
	/**
	 * 根据用户登录名查询
	 * @param name
	 * @return
	 */
	MemberBlackList selectByName(String name);
	/**
	 * 添加到黑名单
	 * @param black
	 * @return
	 */
	int insertMemberBlack(MemberBlackList black);
	
	
}
