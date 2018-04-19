package com.izhuantou.service.api.lend;

import java.util.List;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.DisplayZZT;
import com.izhuantou.service.api.BaseService;

/**
 * 转转投调用接口
 * 
 * @author yangbosen
 *
 */
public interface DisplayZZTService extends BaseService<DisplayZZT> {
    /**
     * 
     * @param page
     *            当前页数
     * @param sortp
     *            排序参数
     * @return
     */
    public Pagination<DisplayZZT> showProductsByPage(Integer page, String sortp);

    public List<DisplayZZT> findProductList();
}
