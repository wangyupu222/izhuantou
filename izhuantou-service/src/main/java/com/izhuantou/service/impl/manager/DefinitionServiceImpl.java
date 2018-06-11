package com.izhuantou.service.impl.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.izhuantou.common.bean.Pagination;
import com.izhuantou.damain.manager.Definition;
import com.izhuantou.dao.DefinitionMapper;
import com.izhuantou.service.api.managerMenu.DefinitionService;
import com.izhuantou.service.impl.BaseServiceImpl;

@Service("definitionService")
public class DefinitionServiceImpl extends BaseServiceImpl<Definition> implements DefinitionService {

    @Autowired
    private DefinitionMapper definitionMapper;

    public Pagination<Definition> queryPageListByPage(Integer currentPage) {
	Pagination<Definition> pagination = new Pagination<Definition>();
	try {
	    // 设置每页显示条数
	    pagination.setPageSize(10);

	    // 获取 总条数
	    Integer count = this.definitionMapper.selectCount(null);
	    pagination.setTotalNumber(count);
	    // 获取 总页数
	    if (count % pagination.getPageSize() == 0) {
		pagination.setTotalPage(count / pagination.getPageSize());
	    } else {
		pagination.setTotalPage((count / pagination.getPageSize().intValue() + 1));
	    }
	    // 获取分页数据
	    List<Definition> list = this.queryByPage(currentPage, 10, null);
	    pagination.setData(list);
	} catch (Exception e) {
	    e.getMessage();
	}
	return pagination;
    }

    @Override
    public List<Definition> all() {

	return this.definitionMapper.queryAll();
    }

}
