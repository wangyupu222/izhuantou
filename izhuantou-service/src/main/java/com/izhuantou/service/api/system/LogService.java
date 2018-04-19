package com.izhuantou.service.api.system;

import com.izhuantou.damain.system.Log;

/**
 * 后台操作系统日志接口
 * 
 * @author Administrator
 *
 */
public interface LogService {
    /**
     * 保存系统日志
     * 
     * @param log
     * @return
     */
    public int createLog(Log log);
}
