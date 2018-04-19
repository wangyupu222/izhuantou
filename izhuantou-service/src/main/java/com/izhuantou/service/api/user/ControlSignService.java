package com.izhuantou.service.api.user;

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
    public Map<String, String> doSignRegistAgreement(String memberOID, String agreementContent);

}
