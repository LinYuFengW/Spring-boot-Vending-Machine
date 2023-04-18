package com.ecommerce.oracle.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.BeverageGoods;

@Repository
public interface BackendInfoOracleGoodsDao extends JpaRepository<BeverageGoods, Long>{	

	// StringBuilder querySQL = new StringBuilder();
	// Native Queries 購物網-後臺-查詢商品列表
	@Query(value = "SELECT * FROM BEVERAGE_GOODS WHERE UPPER(GOODS_NAME) LIKE UPPER(?1) AND STATUS = ?2", nativeQuery = true)
	List<BeverageGoods> queryNativeByQueryGoodsData(Pageable pageable,String goodsName,String status);
	@Query(value = "SELECT * FROM BEVERAGE_GOODS WHERE UPPER(GOODS_NAME) LIKE UPPER(?1) AND STATUS = ?2", nativeQuery = true)
	List<BeverageGoods> queryNativeByQueryGoodsData(String goodsName,String status);
	
	@Query(value = "SELECT * FROM BEVERAGE_GOODS WHERE UPPER(GOODS_NAME) LIKE UPPER(?1) OR QUANTITY = ?2 AND STATUS = ?3", nativeQuery = true)
	List<BeverageGoods> queryNativeByQueryGoodsData(Pageable pageable,String goodsName,Integer quantity,String status);
	@Query(value = "SELECT * FROM BEVERAGE_GOODS WHERE UPPER(GOODS_NAME) LIKE UPPER(?1) OR QUANTITY = ?2 AND STATUS = ?3", nativeQuery = true)
	List<BeverageGoods> queryNativeByQueryGoodsData(String goodsName,Integer quantity,String status);
	
	@Query(value = "SELECT * FROM BEVERAGE_GOODS WHERE GOODS_ID = ?1 OR UPPER(GOODS_NAME) LIKE UPPER(?2) AND STATUS = ?3", nativeQuery = true)
	List<BeverageGoods> queryNativeByQueryGoodsData(Pageable pageable,long goodsID,String goodsName,String status);
	@Query(value = "SELECT * FROM BEVERAGE_GOODS WHERE GOODS_ID = ?1 OR UPPER(GOODS_NAME) LIKE UPPER(?2) AND STATUS = ?3", nativeQuery = true)
	List<BeverageGoods> queryNativeByQueryGoodsData(long goodsID,String goodsName,String status);
	
	@Query(value = "SELECT * FROM BEVERAGE_GOODS WHERE GOODS_ID = ?1 OR UPPER(GOODS_NAME) LIKE UPPER(?2) OR QUANTITY = ?3 AND STATUS = ?4", nativeQuery = true)
	List<BeverageGoods> queryNativeByQueryGoodsData(Pageable pageable,long goodsID,String goodsName,Integer quantity,String status);
	@Query(value = "SELECT * FROM BEVERAGE_GOODS WHERE GOODS_ID = ?1 OR UPPER(GOODS_NAME) LIKE UPPER(?2) OR QUANTITY = ?3 AND STATUS = ?4", nativeQuery = true)
	List<BeverageGoods> queryNativeByQueryGoodsData(long goodsID,String goodsName,Integer quantity,String status);
	
	
	
	// Native Queries 購物網-後臺-商品維護作業-查詢單一商品資料
	@Query(value = "SELECT * FROM BEVERAGE_GOODS B WHERE B.GOODS_ID = ?1", nativeQuery = true)
	BeverageGoods queryNativeByGoods_ID(long goodsID);
		
}
