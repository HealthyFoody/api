package com.healthyfoody.util;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.healthyfoody.dto.ApiResponse;
import com.healthyfoody.mapper.ResponseMapper;

public class PageMapper {
	
	public static <R extends ApiResponse, E> Page<R> mapPage(Page<E> page, ResponseMapper<R, E> mapper) {
		List<E> content = page.getContent();
		return new PageImpl<R>(mapper.mapResponseList(content), page.getPageable(), page.getTotalElements());
	}
}
