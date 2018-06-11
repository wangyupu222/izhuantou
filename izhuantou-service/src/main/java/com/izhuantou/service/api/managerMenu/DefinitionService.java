package com.izhuantou.service.api.managerMenu;

import java.util.List;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.manager.Definition;
import com.izhuantou.service.api.BaseService;

public interface DefinitionService extends BaseService<Definition> {

    /**
     * 根据当前页获取分页列表
     * 
     * @param currentPage
     * @return
     */
    public Pagination<Definition> queryPageListByPage(Integer currentPage);

    public List<Definition> all();

}
