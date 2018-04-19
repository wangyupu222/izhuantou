package com.izhuantou.service.api.lend;

import java.util.List;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.lend.DisplayTBZ;
import com.izhuantou.service.api.BaseService;

/**
 * 头笔赚投调用接口
 * 
 * @author yangbosen
 *
 */
public interface DisplayTBZService extends BaseService<DisplayTBZ> {
    /**
     * 
     * @param page
     *            当前页数
     * @param sortp
     *            排序参数
     * @return
     */
    public Pagination<DisplayTBZ> showProductsByPage(Integer page, String sortp);

    // 查找头笔赚列表信息 不做分页 暂时用于手机端
    public List<DisplayTBZ> findTBZList();
}
