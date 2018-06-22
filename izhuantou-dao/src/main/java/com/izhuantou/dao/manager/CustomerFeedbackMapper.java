package com.izhuantou.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.manager.CustomerFeedback;
import com.izhuantou.damain.vo.member.CustomerQueryConditionDTO;

/**
 * 客户反馈Mapper
 * 
 * @author sweet
 * @date 2018年6月20日
 *
 */
public interface CustomerFeedbackMapper extends Mapper<CustomerFeedback> {

	/**
	 * 统计客服登记的总数
	 */
	int countServiceRegisterNumber(@Param("type") String type);

	/**
	 * 分页获取客服登记的数据
	 * 
	 * @param i
	 * @param pageSize
	 * @return
	 */
	List<CustomerFeedback> listServiceRegister(@Param("startIndex") int startIndex, @Param("pageSize") Integer pageSize,
			@Param("type") String type);

	/**
	 * 根据条件统计客服登记总数
	 * 
	 * @param cqcDTO
	 * @return
	 */
	int countByCondition(@Param("cqcDTO") CustomerQueryConditionDTO cqcDTO);

	/**
	 * 根据条件分页获取客服登记数据
	 * 
	 * @param i
	 * @param pageSize
	 * @param cqcDTO
	 * @return
	 */
	List<CustomerFeedback> selectByCondition(@Param("startIndex") int startIndex, @Param("pageSize") Integer pageSize,
			@Param("cqcDTO") CustomerQueryConditionDTO cqcDTO);

	/**
	 * 根据oid修改状态
	 * 
	 * @param customerFeedback
	 * @return
	 */
	int updateStatusByOid(CustomerFeedback customerFeedback);

	/**
	 * 根据oid修改状态为解决中
	 * 
	 * @param customerFeedback
	 * @return
	 */
	int updateStatus(CustomerFeedback customerFeedback);

	/**
	 * 根据多个oid获取数据
	 * 
	 * @param oidArray
	 * @return
	 */
	List<CustomerFeedback> selectByOids(String[] oidArray);

	/**
	 * 根据条件获取反馈信息
	 * 
	 * @param cqcDTO
	 * @return
	 */
	List<CustomerFeedback> selectAllByCondition(@Param("cqcDTO")CustomerQueryConditionDTO cqcDTO);

}
