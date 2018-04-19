package com.izhuantou.dao.loan;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.loan.LoanDistribeTask;

/**
 * 工作流任务分配
 * 
 * @author dear
 * @version 1.0
 */
public interface LoanDistribeTaskMapper extends Mapper<LoanDistribeTask> {
    /**
     * 查看工作客服 获取当前角色任务最小值 用户
     * 
     * @param rolename
     * @return
     */
    public LoanDistribeTask gainLoanDistribeTaskByRoleName(String rolename);

    /**
     * 更新服务的次数
     * 
     * @param OID
     * @return
     */
    public int updateTaskNum(LoanDistribeTask loanDistribeTask);

}
