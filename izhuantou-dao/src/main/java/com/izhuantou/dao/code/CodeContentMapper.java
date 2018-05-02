package com.izhuantou.dao.code;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.code.CodeContent;

/**
 * 编码内容
 * 
 * @author dear
 * @version 1.0
 */
public interface CodeContentMapper extends Mapper<CodeContent> {
    /**
     * 根据用户表里的 questionOne 和questionTwo来获取code_content表中的 nameCN 问题的名字
     * 
     * @param NO
     * @return
     */
    public CodeContent findTwoQuestion(Integer NO);

    /**
     * 根据编码类型和ID查询单条
     * 
     * @param ID
     * @param definitionName
     * @return
     */
    public CodeContent findCodeContent(@Param("ID") String ID, @Param("definitionName") String definitionName);

}
