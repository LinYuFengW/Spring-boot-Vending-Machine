package com.ecommerce.vo;

import java.util.List;

import com.ecommerce.entity.BeverageGoodsNativeQueryMapping;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
public class GoodsDataInfo {
	
	private List<BeverageGoodsNativeQueryMapping> goodsDatas;

	private GenericPageable genericPageable;
}
