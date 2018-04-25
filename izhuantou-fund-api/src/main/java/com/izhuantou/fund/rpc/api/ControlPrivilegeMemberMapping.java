package com.izhuantou.fund.rpc.api;

import java.util.List;

import com.izhuantou.damain.pay.PayPrivilegeMemberMapping;

public interface ControlPrivilegeMemberMapping extends BaseService<PayPrivilegeMemberMapping> {

    List<PayPrivilegeMemberMapping> gainByTqOIDAndMemebrOID(String TqOID, String MemberOID);

}
