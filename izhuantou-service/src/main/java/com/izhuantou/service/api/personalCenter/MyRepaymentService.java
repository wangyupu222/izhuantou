package com.izhuantou.service.api.personalCenter;

import java.util.List;

import com.izhuantou.damain.vo.ListRepaymentCollection;

public interface MyRepaymentService {
    public List<ListRepaymentCollection> findMyRepayment(String memberOID);
}
