package com.homeloan.main.payload;

import lombok.Data;

@Data
public class PaginationRequest {
	
	private int pageNumber;
	
	private int pageSize;

}
