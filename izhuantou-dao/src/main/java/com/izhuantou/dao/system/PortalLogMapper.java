package com.izhuantou.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.system.PortalLog;
import com.izhuantou.damain.vo.member.TrackQueryConditionDTO;

public interface PortalLogMapper extends Mapper<PortalLog> {

	/**
	 * 分页获取日志信息
	 * 
	 * @param i
	 * @param pageSize
	 * @return
	 */
	List<PortalLog> listByPage(@Param("startIndex") int startIndex, @Param("pageSize") Integer pageSize);

	/**
	 * 按条件统计总数
	 * 
	 * @param trackQueryConditionDTO
	 * @return
	 */
	int countByCondition(@Param("tqcDTO") TrackQueryConditionDTO trackQueryConditionDTO);

	/**
	 * 按条件分页获取数据
	 * 
	 * @param i
	 * @param pageSize
	 * @param trackQueryConditionDTO
	 * @return
	 */
	List<PortalLog> listByCondition(@Param("startIndex") int startIndex, @Param("pageSize") Integer pageSize,
			@Param("tqcDTO") TrackQueryConditionDTO trackQueryConditionDTO);

}
