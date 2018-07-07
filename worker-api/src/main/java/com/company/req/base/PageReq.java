package com.company.req.base;

import com.comtom.bc.common.Constants;

/**
 * 参数对象基类
 * 
 * @author chenxin
 * @date 2017年3月27日 上午11:42:26
 */
public class PageReq {

	/**
	 * 当前页数
	 */
	protected Integer currentPage = Constants.DEFAULT_PAGE_NUM;

	/**
	 * 每页显示记录数
	 */
	protected Integer pageSize = Constants.DEFAULT_LIMIT;

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
