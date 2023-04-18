package com.ecommerce.oracle.dao;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ecommerce.entity.BeverageGoods;
import com.ecommerce.entity.BeverageGoodsNativeQueryMapping;
import com.ecommerce.vo.GenericPageable;
import com.ecommerce.vo.GoodsDataCondition;


@Repository
public class BackendInfoOracleGoodsNativeQueryDao {

	@PersistenceContext(name = "oracleEntityManager")
    private EntityManager entityManager;
	
	
	public List<BeverageGoodsNativeQueryMapping> entityManagerCreateQuery(GoodsDataCondition condition, GenericPageable genericPageable) {
		
		// SQL NativeQuery ResultSetMapping
		// 支援可動態組SQL又可自訂義物件欄位對應查詢欄位資料取得查詢結果
		// 須建立欄位相對應的  @SqlResultSetMapping @Entity 實體物件
		int no = 1;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM (SELECT ROWNUM ROW_NUM, G.*, "); // 給每樣商品前面加序號，給BETWEEN做條件判斷
		sql.append("COUNT(G.GOODS_ID)OVER() GOODS_SIZE "); // 產生有幾樣商品的欄位，做分頁使用。
		sql.append("FROM BEVERAGE_GOODS G ");
		sql.append("WHERE UPPER(GOODS_NAME) LIKE UPPER(?" + no++ + ") AND STATUS = ?" + no++ + " ");
		sql.append("AND PRICE BETWEEN ?" + no++ + " AND ?" + no++ + " ");
		
		if (condition.getGoodsID() != null && condition.getQuantity() == null) {
			sql.append("AND GOODS_ID = ?" + no++);
		} else if (condition.getGoodsID() == null && condition.getQuantity() != null) {
			sql.append("AND QUANTITY = ?" + no++);
		} else if (condition.getGoodsID() != null && condition.getQuantity() != null) {
			sql.append("AND GOODS_ID = ?" + no++ + " AND QUANTITY = ?" + no++);
		}
		
		sql.append(") WHERE ROW_NUM BETWEEN ?" + no++ + " AND ?" + no++);
		
		// "SELECT * FROM BEVERAGE_GOODS WHERE GOODS_ID = ?1 OR UPPER(GOODS_NAME) LIKE UPPER(?2) OR QUANTITY = ?3 AND STATUS = ?4"
		// com.ecommerce.oracle.entity.BeverageGoodsCriteriaMapping
		// Query query  = entityManager.createNativeQuery(sql.toString(), "StoreGroupSumMapping");
		
		// 如使用StoreGroupSumMapping.class，entity無需設置@SqlResultSetMapping(欄位對照)
		// Query query3  = entityManager.createNativeQuery(sql.toString(), StoreGroupSumMapping.class);
		
		Query query  = entityManager.createNativeQuery(sql.toString(), BeverageGoodsNativeQueryMapping.class);
		
		if (condition.getGoodsName() == null) {
			condition.setGoodsName("%%");
		} else {
			condition.setGoodsName("%" + condition.getGoodsName() + "%");
		}
		
		if (condition.getStartPrice() == null) {condition.setStartPrice(0);}		
		if (condition.getEndPrice() == null) {condition.setEndPrice(99999);}
		
		// 設定分頁，每頁N項商品。例:每頁6項商品，start = 6*(第1頁-1)+1 ， end = start+5
		int start = genericPageable.getPageDataSize() * (genericPageable.getCurrentPageNo() - 1) + 1;
		int end = start + genericPageable.getPageDataSize() - 1;
		
		no = 1;
		query.setParameter(no++, condition.getGoodsName());
		query.setParameter(no++, condition.getStatus());
		query.setParameter(no++, condition.getStartPrice());
		query.setParameter(no++, condition.getEndPrice());
		
		if (condition.getGoodsID() != null && condition.getQuantity() == null) {
			query.setParameter(no++, condition.getGoodsID());
			
		} else if (condition.getGoodsID() == null && condition.getQuantity() != null) {
			query.setParameter(no++, condition.getQuantity());
		} else if (condition.getGoodsID() != null && condition.getQuantity() != null) {
			query.setParameter(no++, condition.getGoodsID());
			query.setParameter(no++, condition.getQuantity());
		}
		
		query.setParameter(no++, start);
		query.setParameter(no++, end);
			
		Stream<BeverageGoodsNativeQueryMapping> bakendGoodsStream = query.getResultStream().map(s -> (BeverageGoodsNativeQueryMapping)s);
		List<BeverageGoodsNativeQueryMapping> bakendGoods = bakendGoodsStream.collect(Collectors.toList());
		bakendGoods.stream().forEach(System.out::println);
		
		return bakendGoods;
	}
	
}
