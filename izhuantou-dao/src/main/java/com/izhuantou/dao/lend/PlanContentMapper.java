package com.izhuantou.dao.lend;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.lend.PlanContent;

public interface PlanContentMapper extends Mapper<PlanContent> {
	/**
	 * 根据className获取线程信息
	 * @param string
	 * @return
	 */
	PlanContent selectByClassName(String className);

}
