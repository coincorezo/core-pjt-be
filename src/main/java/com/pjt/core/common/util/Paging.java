package com.pjt.core.common.util;

import lombok.Data;

@Data
public class Paging {

	// 현재 페이지
	private Integer page;
	// 페이지당 개수
	private Integer size;
	// 페이지 사이즈 (화면 하단의 페이징바 개수)
	private Integer pageSize;
	// 정렬
	private String sort;
	
	public Integer getPage() {
		if(page == null || page == 0) {
			page = 1;
		}
		
		return page;
	}
	
	public Integer getSize() {
		if(size == null || size == 0) {
			size = 0;
		}
		return size;
	}
	
	public Integer getOffset() { return (this.getPage() - 1 ) * this.getSize(); }
	
}
