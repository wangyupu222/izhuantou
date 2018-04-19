package com.izhuantou.service.api.user;

import com.izhuantou.damain.sequence.SequenceDefinition;
import com.izhuantou.service.api.BaseService;

public interface SequenceDefinitionService extends BaseService<SequenceDefinition> {
    /**
     * 获取序列号
     * 
     * @param name
     * @return
     */
    public String gainSequence(String name);
}
