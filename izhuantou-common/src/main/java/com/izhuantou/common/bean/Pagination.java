package com.izhuantou.common.bean;

import java.io.Serializable;
import java.util.List;

public class Pagination<T> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** 每页显示条数 */
    private Integer pageSize = 10;

    /** 当前页 */
    private Integer currentPage = 1;

    /** 总页数 */
    private Integer totalPage = 1;

    /** 查询到的总数据量 */
    private Integer totalNumber = 0;

    /** 数据集 */
    private List<T> data;
    private String sortp;

    public String getSortp() {
	return sortp;
    }

    public void setSortp(String sortp) {
	this.sortp = sortp;
    }

    public Pagination() {
    }

    public Pagination(Integer currentPage, Integer totalPage, Integer totalNumber) {
	this.currentPage = currentPage;
	this.totalPage = totalPage;
	this.totalNumber = totalNumber;
    }

    public Integer getPageSize() {
	return pageSize;
    }

    public Integer getCurrentPage() {
	return currentPage;
    }

    // 取得总页数，总页数=总记录数/总页数
    public Integer getTotalPage() {
	totalPage = getTotalNumber() / getPageSize();
	return (totalNumber % pageSize == 0) ? totalPage : totalPage + 1;
    }

    public Integer getTotalNumber() {
	return totalNumber;
    }

    public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
    }

    public void setCurrentPage(Integer currentPage) {
	this.currentPage = currentPage;
    }

    // 需要修改
    public void setTotalPage(Integer totalPage) {
	this.totalPage = totalPage;
    }

    public void setTotalNumber(Integer totalNumber) {
	this.totalNumber = totalNumber;
    }

    public List<T> getData() {
	return data;
    }

    public void setData(List<T> data) {
	this.data = data;
    }

    @Override
    public String toString() {
	return "Pagination [pageSize=" + pageSize + ", currentPage=" + currentPage + ", totalPage=" + totalPage
		+ ", totalNumber=" + totalNumber + ", data=" + data + ", sortp=" + sortp + "]";
    }

}
