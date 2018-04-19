package com.izhuantou.damain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.izhuantou.damain.BasePojo;

/**
 * 后台系统异常日志实体
 *
 * @author fucheng
 * @date 2018-01-18
 */

@Document(collection = "ManagerErrorLog")
@CompoundIndexes({ @CompoundIndex(name = "index_unique", def = "{'name': 1, 'created': 1}", unique = true) })
public class ManagerErrorLog extends BasePojo {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @Indexed
    // 功能名称
    private String name;
    // 方法名称
    private String methodName;
    // 错误信息
    private String message;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getMethodName() {
	return methodName;
    }

    public void setMethodName(String methodName) {
	this.methodName = methodName;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
}
