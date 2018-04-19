package com.izhuantou.damain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.izhuantou.damain.BasePojo;

@Document(collection = "memberAgreement")
public class MemberAgreementMongo extends BasePojo {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    /**
     * OID
     */
    private String OID;
    /**
     * 协议内容
     */
    private String content;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    @Override
    public String toString() {
	return "MemberAgreement [id=" + id + ", OID=" + OID + ", content=" + content + "]";
    }

}
