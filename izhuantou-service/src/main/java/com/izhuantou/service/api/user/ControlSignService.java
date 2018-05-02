package com.izhuantou.service.api.user;

import java.util.Map;

import com.timevale.esign.sdk.tech.impl.constants.OrganRegType;

public interface ControlSignService {

    /**
     * 1 砖头网注册协议(电子签章)
     * 
     * @param memberOID
     * @param agreementContent
     *            协议内容
     * @return
     */
    Map<String, String> doSignRegistAgreement(String memberOID, String agreementContent);

    /***
     * 2 借款咨询及管理服务协议(电子签章)
     * 
     * @param memberOID
     * @param agreementContent
     *            协议内容
     * @param
     * @throws ExceptionQueryFail
     */
    Map<String, String> doSignJKZXAgreement(String memberOID, String agreementContent);

    /***
     * 3 砖头网各产品的服务协议： 砖头网点点投服务协议(电子签章) 砖头网环环投服务协议(电子签章) 砖头网新手标服务协议(电子签章)
     * 砖头网转转投服务协议 (电子签章)
     * 
     * @param memberOID
     * @param agreementContent
     *            协议内容
     * @param agreeType
     *            协议类别：ddt：点点投 hht：环环投 xsb：新手标 zzt：转转投 用来生成文件名
     * @param
     * @throws ExceptionQueryFail
     */
    Map<String, String> doSignFWAgreement(String memberOID, String agreementContent, String agreeType);

    /***
     * 4 砖头网信贷借款协议(电子签章)（借款协议）
     * 
     * @param memberOID
     * @param agreementContent
     *            协议内容
     * @param
     * @throws ExceptionQueryFail
     */
    Map<String, String> doSignXDJKAgreement(String memberOID, String agreementContent);

    /***
     * 5 砖头网债权转让协议 (电子签章)
     * 
     * @param memberOID
     * @param agreementContent
     *            协议内容
     * @param
     * @throws ExceptionQueryFail
     */
    Map<String, String> doSignZQZRAgreement(String memberOID, String agreementContent);

    /***
     * 6 逾期债权预收购协议 (电子签章)(预回购)
     * 
     * @param memberOID
     * @param agreementContent
     *            协议内容
     * @param
     * @throws ExceptionQueryFail
     */
    Map<String, String> doSignYQZQYSGAgreement(String memberOID, String agreementContent);

    /**
     * 创建个人客户账户 并存表（只创建个人客户账户时使用）
     * 
     * @param nameCN
     *            中文名
     * @param cardNO
     *            身份证号/护照号
     * @param personArea
     *            个人归属地：0-大陆，1-香港，2-澳门，3-台湾，4-外籍，默认0
     */
    void addPersonAccount(String nameCN, String cardNO, int personArea);

    /**
     * 创建企业客户账户 并存表（只创建企业客户账户时使用）
     * 
     * @param name
     *            企业名称
     * @param organType
     *            单位类型，0-普通企业，1-社会团体，2-事业单位，3-民办非企业单位，4-党政及国家机构，默认0
     * @param regType
     *            企业注册类型，NORMAL:组织机构代码号，MERGE：多证合一，传递社会信用代码号,REGCODE:企业工商注册码,
     *            默认NORMAL （传入：OrganRegType.MERGE等）
     * @param organCode
     *            组织机构代码号、社会信用代码号或工商注册号
     * @param address
     *            公司地址,可空
     * @param userType
     *            注册类型，1-代理人注册，2-法人注册，默认1
     * @param agentName
     *            代理人姓名，当注册类型为1时必填
     * @param agentIdNo
     *            代理人身份证号，当注册类型为1时必填
     */
    void addOrganizeAccount(String name, int organType, OrganRegType regType, String organCode, String address,
	    int userType, String agentName, String agentIdNo);

}
