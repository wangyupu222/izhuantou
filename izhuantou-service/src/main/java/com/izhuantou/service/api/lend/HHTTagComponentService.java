package com.izhuantou.service.api.lend;

import java.util.Map;

import com.izhuantou.damain.lend.HHTTagComponent;
import com.izhuantou.service.api.BaseService;

/**
 * 环环投标的组成
 * 
 * @author Administrator
 *
 */
public interface HHTTagComponentService extends BaseService<HHTTagComponent> {

    public Map<String, Object> findByCondition(Integer page, String OID);

}
