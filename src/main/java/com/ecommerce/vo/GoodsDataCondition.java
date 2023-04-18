package com.ecommerce.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

//@Builder
@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class GoodsDataCondition {
	
	// 飲料ID
	private Long goodsID;
	
	// 飲料名稱
	private String goodsName;
	
	// 設定價格
	private String priceSort;
	
	// 設定價格
	@Builder.Default
	private Integer startPrice = 0;
	
	// 設定價格
	@Builder.Default
	private Integer endPrice = 99999;
	
	// 初始數量
	private Integer quantity;
	
	// 商品狀態
	private String status;

	
}
