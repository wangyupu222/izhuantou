package com.izhuantou.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.manager.DetailFeedback;

/**
 * 反馈内容详情Mapper
 * 
 * @author sweet
 * @date 2018年6月20日
 *
 */
public interface DetailFeedbackMapper extends Mapper<DetailFeedback> {

	/**
	 * 获取该反馈的最近反馈人
	 * 
	 * @param oid
	 * @param name
	 * @return
	 */
	DetailFeedback findFeedbackPerson(@Param("oid") String oid, @Param("name") String name);

	/**
	 * 根据客户反馈oid获取客服回复内容
	 * 
	 * @param oid
	 * @return
	 */
	List<DetailFeedback> selectByCustomerFeedbackOid(@Param("oid") String oid,@Param("name")String name);
	
	/**
	 * 根据客户反馈oid获取会话内容
	 * @param oid
	 * @return
	 */
	List<DetailFeedback> selectByOid(String oid);

}
