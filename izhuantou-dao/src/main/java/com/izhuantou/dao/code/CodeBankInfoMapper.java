package com.izhuantou.dao.code;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.code.CodeBankInfo;

/**
 * 银行卡对应的code码和名字
 * 
 * @author dear
 * @version 1.0
 */
public interface CodeBankInfoMapper extends Mapper<CodeBankInfo> {
    /**
     * 根据银行看的名字查询银行信息
     * 
     * @return
     */
    CodeBankInfo findByBankName(String bankName);

}
