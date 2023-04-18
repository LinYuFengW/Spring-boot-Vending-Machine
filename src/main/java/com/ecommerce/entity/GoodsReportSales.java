package com.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "BEVERAGE_ORDER", schema="LOCAL")
public class GoodsReportSales {
	
	// 訂單編號
	@Id
	@Column(name = "ORDER_ID")
	private Long orderID;
	
	// 訂單日期
	@Column(name = "ORDER_DATE")
	private String orderDate;
	
	// 客戶身份證
	@Column(name = "CUSTOMER_ID")
	private String customerID;
	
	// 商品編號
    @Column(name = "GOODS_ID")
	private Long goodsID;
	
    // 購買價錢
	@Column(name = "GOODS_BUY_PRICE")
	private int goodsBuyPrice;
    
	// 購買數量
	@Column(name = "BUY_QUANTITY")
	private int buyQuantity;	
	
	// 地址
	@Column(name = "ADDRESS")
	private String address;			
	
	// 郵政號碼
	@Column(name = "ZIP_CODE")
	private int zipCode;
	
	// 付款方式(0:貨到付款、1:超商取貨、2:信用卡繳費)
	@Column(name = "PAY_METHOD")
	private Long payMethod;
	
	// 送貨日期
	@Column(name = "DELIVERY_DATE")
	private String deliveryDate;
	
	// 超商門市
	@Column(name = "SUPERMARKET")
	private String superMarket;
	
	// 信用卡
	@Column(name = "CREDIT_CARD")
	private String creditCard;
	
}
