package com.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import lombok.Data;

// 如使用StoreGroupSumMapping.class，entity無需設置@SqlResultSetMapping(欄位對照)
//@SqlResultSetMapping(
//    name = "StoreGroupSumMapping",
//    entities={
//        @EntityResult(
//	        entityClass = com.ecommerce.entity.BeverageGoodsNativeQueryMapping.class,
//	        fields = {
//	        	@FieldResult(name="goodsID", column="GOODS_ID"),
//	            @FieldResult(name="goodsName",  column="GOODS_NAME"),
//	            @FieldResult(name="description",  column="DESCRIPTION"),
//	            @FieldResult(name="price",  column="PRICE"),
//	            @FieldResult(name="quantity",  column="QUANTITY"),
//	            @FieldResult(name="imageName",  column="IMAGE_NAME"),
//	            @FieldResult(name="status",  column="STATUS")
//	        }
//        )
//    }
//)
@Data
@Entity
public class BeverageGoodsNativeQueryMapping {
	
	@Id
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
	@Column(name = "ROW_NUM")
	private int rowNum;
	
	// 產生有幾樣商品的欄位，做分頁使用。
	@Column(name = "GOODS_SIZE")
	private int goodsSize;
		
}
