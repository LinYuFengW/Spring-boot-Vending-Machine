package com.ecommerce.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.vo.MemberInfo;

@RestController
@RequestMapping("/ecommerce/FrontendController")
public class FrontendController {
	
	private static Logger logger = LoggerFactory.getLogger(FrontendController.class);
	
	@Autowired
	private HttpSession httpSession;
	
	@Resource
	private MemberInfo sessionMemberInfo;
	
//	@Resource(name = "sessionCartGoods")
//	private List<GoodsVo> cartGoods;
	
//	@Autowired
//	private FrontendService frontendService;
//	
//	@ApiOperation(value = "購物網-前臺-查詢商品列表")
//	@GetMapping(value = "/queryGoodsData")
//	public ResponseEntity<ProductGoodsInfo> queryGoodsData(@RequestParam(required = false) String searchKeyword,
//			 @RequestParam int currentPageNo, @RequestParam int pageDataSize, @RequestParam int pagesIconSize) {
//	
//		GenericPageable genericPageable = GenericPageable.builder().currentPageNo(currentPageNo)
//				.pageDataSize(pageDataSize).pagesIconSize(pagesIconSize).build();
//		
//		ProductGoodsInfo goodsDataInfo = frontendService.queryGoodsData(searchKeyword, genericPageable);		
//		
//		return ResponseEntity.ok(goodsDataInfo);
//	}
//	
//	@ApiOperation(value = "購物網-前臺-結帳購物車商品")
//	@PostMapping(value = "/checkoutGoods")
//	public ResponseEntity<CheckoutCompleteInfo> checkoutGoods(@RequestBody OrderCustomer customer) {
//		
//		logger.info("HttpSession checkoutGoods:" + httpSession.getId());
//		logger.info("CheckoutGoods:" + sessionMemberInfo.toString());
//		
//		CheckoutCompleteInfo checkoutCompleteInfo = frontendService.checkoutGoods(sessionMemberInfo, customer, cartGoods);
//		
//		return ResponseEntity.ok(checkoutCompleteInfo);
//	}

}
