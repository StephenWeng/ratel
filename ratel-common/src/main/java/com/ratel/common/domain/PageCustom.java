package com.ratel.common.domain;

import java.util.List;
import org.springframework.data.domain.Page;
import org.apache.poi.hssf.record.formula.functions.T;

public class PageCustom<T>{

	private Integer current;//当前页
	
	private Integer pageSize;//每页显示的条数
	
	private long total;//总条数
	
	private List<T> content;//集合
	
	public PageCustom(Page<T> pages) {
		this.current=pages.getNumber()+1;
		this.pageSize=pages.getSize();
		this.total=pages.getTotalElements();
		this.content=pages.getContent();
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	
	
	

}
