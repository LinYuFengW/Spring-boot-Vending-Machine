package com.ecommerce.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.entity.BeverageGoods;
import com.ecommerce.entity.BeverageGoodsNativeQueryMapping;
import com.ecommerce.entity.GoodsReportSales;
import com.ecommerce.oracle.dao.BackendInfoOracleGoodsDao;
import com.ecommerce.oracle.dao.BackendInfoOracleGoodsNativeQueryDao;
import com.ecommerce.oracle.dao.BackendInfoOracleOrderDao;
import com.ecommerce.vo.GenericPageable;
import com.ecommerce.vo.GoodsDataCondition;
import com.ecommerce.vo.GoodsDataInfo;
import com.ecommerce.vo.GoodsReportSalesInfo;
import com.ecommerce.vo.GoodsSalesReportCondition;
import com.ecommerce.vo.GoodsVo;

@Service
public class BackendService {
	
	private static Logger logger = LoggerFactory.getLogger(BackendService.class);		
	
	@Autowired
	private BackendInfoOracleGoodsDao beverageGoodsDao;
	
	@Autowired
	private BackendInfoOracleOrderDao beverageOrderDao;
	
	@Autowired
	private BackendInfoOracleGoodsNativeQueryDao backendInfoOracleGoodsNativeQueryDao;
	
	@Autowired
	@Qualifier("queryGoodsSales")
	private GoodsReportSalesInfo queryGoodsSales;
	
	@Autowired
	@Qualifier("goodsDataInfo")
	private GoodsDataInfo goodsDataInfo;
	
	
	// 購物網-後臺-查詢商品列表
	public GoodsDataInfo queryGoodsData(GoodsDataCondition condition,GenericPageable genericPageable) {		
		
		// PageRequest.of(int page, int size, Sort sort)
		// Pageable 由於頁數從零開始代表第一頁，在此減1，讓前端輸入1代表查第一頁。
//		Pageable pageable = PageRequest.of(genericPageable.getCurrentPageNo()-1, genericPageable.getPageDataSize(),Sort.by("GOODS_ID").ascending());
//		
//		List<BeverageGoods> queryGoodsData;
//		List<BeverageGoods> queryGoodsDataTotal;
//		if (condition.getGoodsName() == null) {
//			condition.setGoodsName("%%");
//		} else {
//			condition.setGoodsName("%" + condition.getGoodsName() + "%");
//		}
//		
//		
//		if(condition.getGoodsID() == null && condition.getQuantity() == null) {
//			queryGoodsData = beverageGoodsDao.queryNativeByQueryGoodsData(pageable,condition.getGoodsName(),condition.getStatus());
//			queryGoodsDataTotal = beverageGoodsDao.queryNativeByQueryGoodsData(condition.getGoodsName(),condition.getStatus());
//		} else if (condition.getGoodsID() == null && condition.getQuantity() != null) {
//			queryGoodsData = beverageGoodsDao.queryNativeByQueryGoodsData(pageable,condition.getGoodsName(),condition.getQuantity(),condition.getStatus());
//			queryGoodsDataTotal = beverageGoodsDao.queryNativeByQueryGoodsData(condition.getGoodsName(),condition.getQuantity(),condition.getStatus());
//		} else if (condition.getGoodsID() != null && condition.getQuantity() == null) {
//			queryGoodsData = beverageGoodsDao.queryNativeByQueryGoodsData(pageable,condition.getGoodsID(),condition.getGoodsName(),condition.getStatus());
//			queryGoodsDataTotal = beverageGoodsDao.queryNativeByQueryGoodsData(condition.getGoodsID(),condition.getGoodsName(),condition.getStatus());
//		} else {
//			queryGoodsData = beverageGoodsDao.queryNativeByQueryGoodsData(pageable,condition.getGoodsID(),condition.getGoodsName(),condition.getQuantity(),condition.getStatus());
//			queryGoodsDataTotal = beverageGoodsDao.queryNativeByQueryGoodsData(condition.getGoodsID(),condition.getGoodsName(),condition.getQuantity(),condition.getStatus());
//		}
//						
//		goodsDataInfo.setGoodsDatas(queryGoodsData);
//		goodsDataInfo.setGenericPageable(countPage(genericPageable,queryGoodsDataTotal.size()));
		
		
		List<BeverageGoodsNativeQueryMapping> queryGoodsDataNativeQuery = backendInfoOracleGoodsNativeQueryDao.entityManagerCreateQuery(condition, genericPageable);
		
		goodsDataInfo.setGoodsDatas(queryGoodsDataNativeQuery);
		goodsDataInfo.setGenericPageable(countPage(genericPageable,queryGoodsDataNativeQuery.get(0).getGoodsSize()));
		
		return goodsDataInfo;
	}	
	
	
	
	// 購物網-後臺-商品訂單查詢(一個商品對應到多筆訂單)
	public GoodsReportSalesInfo queryGoodsSales(GoodsSalesReportCondition condition,GenericPageable genericPageable) {		
		
		// PageRequest.of(int page, int size, Sort sort)
		// Pageable 由於頁數從零開始代表第一頁，在此減1，讓前端輸入1代表查第一頁。
		Pageable pageable = PageRequest.of(genericPageable.getCurrentPageNo()-1, genericPageable.getPageDataSize(), 
			Sort.by("GOODS_BUY_PRICE").descending().and(Sort.by("GOODS_ID").ascending())
		);
		
		List<GoodsReportSales> orderInfos = beverageOrderDao.findByOrderDateBetween(pageable, condition.getStartDate(), condition.getEndDate());
		List<GoodsReportSales> orderInfosTotal = beverageOrderDao.findByOrderDateBetween(condition.getStartDate(), condition.getEndDate());
		
		queryGoodsSales.setGoodsReportSalesList(orderInfos);
		queryGoodsSales.setGenericPageable(countPage(genericPageable,orderInfosTotal.size()));
		
		return queryGoodsSales;
	}	
	
	// 購物網-後臺-商品新增作業
	public BeverageGoods createGoods(GoodsVo goodsVo) throws IOException {
		
		BeverageGoods goodsInfo = BeverageGoods.builder()
				.goodsID(goodsVo.getGoodsID())
				.goodsName(goodsVo.getGoodsName())
				.description(goodsVo.getDescription())
				.price(goodsVo.getPrice())
				.quantity(goodsVo.getQuantity())
				.imageName(goodsVo.getImageName())
				.status(goodsVo.getStatus())
				.build();	
		
		MultipartFile file = goodsVo.getFile();
		if(file != null && file.getSize() > 0) {	
			Files.copy(file.getInputStream(), Paths.get("/home/VendingMachine/DrinksImage").resolve(file.getOriginalFilename()));
		}
		
		return beverageGoodsDao.save(goodsInfo);
	}
	
	// 購物網-後臺-商品維護作業-查詢全部商品清單
	public List<BeverageGoods> queryAllGoods() {
		
		return beverageGoodsDao.findAll();
	}	
	
	// 購物網-後臺-商品維護作業-查詢單一商品資料
	public BeverageGoods queryGoodsByID(long goodsID) {
		
		return beverageGoodsDao.queryNativeByGoods_ID(goodsID);
	}
	
	
	// 購物網-後臺-商品維護作業-更新商品資料
	@Transactional(transactionManager = "oracleTransactionManager", rollbackFor = Exception.class)
	public BeverageGoods updateGoods(GoodsVo goodsVo) throws IOException {
		
		Optional<BeverageGoods> optGoods = beverageGoodsDao.findById(goodsVo.getGoodsID());
		BeverageGoods updateGoods = null;
		if(optGoods.isPresent()) {
			updateGoods = optGoods.get();
			
			if(goodsVo.getGoodsName() != null) {
				updateGoods.setGoodsName(goodsVo.getGoodsName());
			}
			if(goodsVo.getDescription() != null) {
				updateGoods.setDescription(goodsVo.getDescription());
			}			
			if(goodsVo.getPrice() > 0) {
				updateGoods.setPrice(goodsVo.getPrice());
			}
			if(goodsVo.getQuantity() > 0) {
				updateGoods.setQuantity(goodsVo.getQuantity());
			}
			if(goodsVo.getStatus() != null) {
				updateGoods.setStatus(goodsVo.getStatus());
			}
			
			MultipartFile file = goodsVo.getFile();
			if(file != null && file.getSize() > 0) {
				Files.delete(Paths.get("/home/VendingMachine/DrinksImage/").resolve(updateGoods.getImageName()));
				Files.copy(file.getInputStream(), Paths.get("/home/VendingMachine/DrinksImage/").resolve(file.getOriginalFilename()));
			}
		}
		
		return updateGoods;
	}
	
	// 計算分頁
	public GenericPageable countPage(GenericPageable genericPageable,int dataTotalSize) {
		
		// 查尋資料筆數
		genericPageable.setDataTotalSize(dataTotalSize);
		
		// 總頁數
		int totalPages = dataTotalSize % genericPageable.getPageDataSize() == 0? 
				dataTotalSize/genericPageable.getPageDataSize() : dataTotalSize/genericPageable.getPageDataSize() + 1;
		
		// 最後一頁分頁數
		genericPageable.setEndPageNo(totalPages);
		
		// 固定分頁數(1.2.3 - 4.5.6)，genericPageable.getPagesIconSize()
		// 餘數 =>  1.2.0 - 1.2.0 ，固定3分頁舉例，3%1餘1  3%2餘2  3%3餘0
		// 取得目前分頁在(固定分頁)中的(位置)，把目前分頁%固定分頁的餘數，等同在分頁的第幾個(位置)，餘數為0代表位置就是固定分頁數。
		int location = genericPageable.getCurrentPageNo() % genericPageable.getPagesIconSize() == 0? 
				genericPageable.getPagesIconSize() : genericPageable.getCurrentPageNo() % genericPageable.getPagesIconSize();
		
		// 底部分頁從第幾開始算 = 目前分頁 -(位置location-1)
		// 1-(1-1)=1  2-(2-1)=1  3-(3-1)=1  4-(1-1)=4  5-(2-1)=4  6-(3-1)=4
		int pageStart = genericPageable.getCurrentPageNo() - (location - 1);
		
		// 分頁按鈕
		Set<Integer> forEachPageNO = new HashSet();
		for (int i=0 ; i < genericPageable.getPagesIconSize() ; i++) {
			// 分頁數大於總頁數，不加入add。
			if (pageStart + i <= totalPages) {
				forEachPageNO.add(pageStart + i);
			}else if (dataTotalSize == 0) {
				forEachPageNO.add(1);
			}
		}
		
		// 計算出要顯示的分頁
		genericPageable.setPagination(forEachPageNO);
		
		return genericPageable;	
	}
	
}
