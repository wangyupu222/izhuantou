package com.izhuantou.dao.sequence;

import com.github.abel533.mapper.Mapper;
import com.izhuantou.damain.sequence.SequenceDefinition;

/**
 * 序列号定义
 * 
 * @author dear
 * @version 1.0
 */
public interface SequenceDefinitionMapper extends Mapper<SequenceDefinition> {
    /**
     * 查看序列
     * 
     * @param name
     * @return
     */
    public SequenceDefinition findSequenceDefinitionByName(String name);

    /**
     * 保存序列
     * 
     * @param definition
     * @return
     */
    public Integer updSequenceDefinitionByName(SequenceDefinition definition);

}
