package com.izhuantou.service.impl.system;

import org.springframework.stereotype.Service;

import com.izhuantou.damain.system.Log;
import com.izhuantou.service.api.system.LogService;

@Service("logService")
public class LogServiceImpl implements LogService {

    @Override
    public int createLog(Log log) {
	System.err.println("模拟日志入库" + log);
	return 1;
    }
}
