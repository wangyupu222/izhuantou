package com.izhuantou.service.api.lend;

import java.util.List;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.DisplayDDT;
import com.izhuantou.service.api.BaseService;

/**
 * 点点投调用接口
 * 
 * @author yangbosen
 *
 */
public interface DisplayDDTService extends BaseService<DisplayDDT> {

    /**
     * 
     * @param page
     *            当前页数
     * @param sortp
     *            排序参数
     * @return
     */
    public Pagination<DisplayDDT> showProductsByPage(Integer page, String sortp);

    public List<DisplayDDT> findProductList();
}
