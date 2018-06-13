package com.izhuantou.service.impl.system;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.system.Log;
import com.izhuantou.service.api.system.LogService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

    @Override
    public int createLog(Log log) {
	this.save(log);
	return 1;
    }
}
