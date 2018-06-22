package com.izhuantou.third.rpc.api.tsign;

import java.util.Map;

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

}
