package com.pjt.core.common.util;

import lombok.Data;

@Data
public class PagingResponse<T extends Object> {

	private Integer totalCount;
	private T data;
}
