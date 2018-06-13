package com.izhuantou.admin.ims;

import com.izhuantou.damain.system.Log;
import com.izhuantou.service.api.system.LogService;

/**
 * 保存日志线程
 * 
 * @author fucheng
 * @date 2018-04-11
 *
 */
public class LogThread implements Runnable {

    private Log log;
    private LogService logService;

    public LogThread(Log log, LogService logService) {
	this.log = log;
	this.logService = logService;
    }

    @Override
    public void run() {
	logService.createLog(log);
    }

}
