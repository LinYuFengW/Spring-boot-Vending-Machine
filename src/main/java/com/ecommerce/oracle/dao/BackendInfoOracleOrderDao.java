package com.ecommerce.oracle.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.GoodsReportSales;

@Repository
public interface BackendInfoOracleOrderDao extends JpaRepository<GoodsReportSales, Long>{		
	
	// 購物網-後臺-商品訂單查詢(一個商品對應到多筆訂單)
//	List<GoodsReportSales> findByOrderDateBetween(Pageable pageable, String startDate, String endDate);
//	List<GoodsReportSales> findByOrderDateBetween(String startDate, String endDate);
	
	// Native Queries 購物網-後臺-商品訂單查詢(一個商品對應到多筆訂單)
	@Query(value = "SELECT * FROM BEVERAGE_ORDER WHERE ORDER_DATE BETWEEN TO_DATE(?1, 'yyyy/MM/dd HH24:mi:ss') AND TO_DATE(?2, 'yyyy/MM/dd HH24:mi:ss')", nativeQuery = true)
	List<GoodsReportSales> findByOrderDateBetween(Pageable pageable, String startDate, String endDate);
	
	@Query(value = "SELECT * FROM BEVERAGE_ORDER WHERE ORDER_DATE BETWEEN TO_DATE(?1, 'yyyy/MM/dd HH24:mi:ss') AND TO_DATE(?2, 'yyyy/MM/dd HH24:mi:ss')", nativeQuery = true)
	List<GoodsReportSales> findByOrderDateBetween(String startDate, String endDate);
			
}
