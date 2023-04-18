package com.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(of = {"goodsID"})
@Table(name = "BEVERAGE_GOODS", schema="LOCAL")
public class BeverageGoods {
	
	// 飲料ID
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BEVERAGE_GOODS_SEQ_GEN")
    @SequenceGenerator(name = "BEVERAGE_GOODS_SEQ_GEN", sequenceName = "BEVERAGE_GOODS_SEQ", allocationSize = 1)
    @Column(name = "GOODS_ID")
	private Long goodsID;
	
	// 飲料名稱
	@Column(name = "GOODS_NAME")
	private String goodsName;
	
	// 商品介紹
	@Column(name = "DESCRIPTION")
	private String description;
	
	// 設定價格
	@Column(name = "PRICE")
	private int price;
	
	// 初始數量
	@Column(name = "QUANTITY")
	private Integer quantity;
	
	// 商品照片名稱
	@Column(name = "IMAGE_NAME")
	private String imageName;
	
	// 商品狀態
	@Column(name = "STATUS")
	private String status;
	
	// 給每樣商品前面加序號，給BETWEEN做條件判斷
//	@Column(name = "ROW_NUM")
//	private int rowNum;
	
	// 產生有幾樣商品的欄位，做分頁使用。
//	@Column(name = "GOODS_SIZE")
//	private int goodsSize;
	
}
