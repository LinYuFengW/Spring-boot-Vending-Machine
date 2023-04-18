package com.ecommerce.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class GoodsSalesReportCondition {
	
	// 查尋開始時間
	private String startDate;
	
	// 查尋結束時間
	private String endDate;
	
}
