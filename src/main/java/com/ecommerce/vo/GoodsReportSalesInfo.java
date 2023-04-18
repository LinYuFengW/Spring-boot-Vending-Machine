package com.ecommerce.vo;

import java.util.List;

import com.ecommerce.entity.GoodsReportSales;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
public class GoodsReportSalesInfo {
	
	// 購物網-後臺-商品訂單查詢(一個商品對應到多筆訂單)
	private List<GoodsReportSales> goodsReportSalesList;
	
	// 商品訂單查詢分頁
	private GenericPageable genericPageable;

	
}
