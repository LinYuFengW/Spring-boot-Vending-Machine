package com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.service.MemberService;
import com.ecommerce.vo.GoodsVo;
import com.ecommerce.vo.MemberInfo;
import com.ecommerce.vo.MemberInfoVo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ecommerce/MemberController")
public class MemberController {
	
	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Resource(name = "SessionMemberInfo")
	private MemberInfo sessionMemberInfo;
	
	@Resource(name = "SessionMemberInfoVo")
	private MemberInfoVo sessionMemberInfoVo;
	
	@Resource(name = "SessionCartGoods")
	private List<GoodsVo> cartGoods;
	
	@Autowired
	private HttpSession httpSession; 
	
	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "購物網-會員-檢查登入")
	@GetMapping(value = "/checkLogin")
	public ResponseEntity<MemberInfo> checkLogin() {
		
		logger.info("HttpSession checkLogin:" + httpSession.getId());
		logger.info("CheckLogin:" + sessionMemberInfo.toString());	
		
		MemberInfo memberInfo = (MemberInfo) httpSession.getAttribute("MemberInfo");
		// 判斷session是否有登入資料
		sessionMemberInfo = memberInfo != null ? memberInfo : MemberInfo.builder().isLogin(false).build();
		
		return ResponseEntity.ok(sessionMemberInfo);
	}
	
	@ApiOperation(value = "購物網-會員-登入")
	@PostMapping(value = "/login")
	public ResponseEntity<MemberInfo> login(@RequestBody MemberInfoVo member) {
	/*
		{
		  "identificationNo": "A124243295",
		  "cusPassword": "123"
		}
	 */
		logger.info("HttpSession Login:" + httpSession.getId());
		logger.info("Before:" + sessionMemberInfoVo.toString());	
		
		sessionMemberInfo = memberService.queryMemberInfo(member);	
		
		logger.info("After:" + sessionMemberInfoVo.toString());
		
		return ResponseEntity.ok(sessionMemberInfo);
	}
	
	@ApiOperation(value = "購物網-會員-登出")
	@GetMapping(value = "/logout")
	public ResponseEntity<MemberInfo> logout() {
		
		logger.info("HttpSession logout:" + httpSession.getId());
		
		
		httpSession.removeAttribute("MemberInfo");
		MemberInfo memberInfo = (MemberInfo) httpSession.getAttribute("MemberInfo");
		
		// 判斷session是否還有登入資料
		sessionMemberInfo = memberInfo != null ? memberInfo : MemberInfo.builder().isLogin(false).build();
		
		return ResponseEntity.ok(sessionMemberInfo);
	}
	
	@ApiOperation(value = "商品加入購物車")
	@PostMapping(value = "/addCartGoods")
	public ResponseEntity<List<GoodsVo>> addCartGoods(@RequestBody GoodsVo goodsVo) {
		/*
			{
			  "goodsID": 28,
			  "goodsName": "Java Chip",
			  "description": "暢銷口味之一，以摩卡醬、乳品及可可碎片調製，加上細緻鮮奶油及摩卡醬，濃厚的巧克力風味。",
			  "imageName": "20130813154445805.jpg",
			  "price": 145,
			  "quantity": 17
			}

			{
			  "goodsID": 3,
			  "goodsName": "柳橙檸檬蜂蜜水",
			  "description": "廣受喜愛的蜂蜜水，搭配柳橙與檸檬汁，酸甜的好滋味，尾韻更帶有柑橘清香。",
			  "imageName": "2021110210202761.jpg",
			  "price": 20,
			  "quantity": 16
			}
		 */
		List<GoodsVo> shoppingCart = (List<GoodsVo>) httpSession.getAttribute("CartGoods");
		// 從session取得已購買商品
		cartGoods = shoppingCart != null ? shoppingCart : new ArrayList<GoodsVo>();
		
		cartGoods.add(goodsVo);
		
		httpSession.setAttribute("CartGoods", cartGoods);

		return ResponseEntity.ok(cartGoods);
	}
	
	@ApiOperation(value = "查尋購物車商品")
	@GetMapping(value = "/queryCartGoods")
	public ResponseEntity<List<GoodsVo>> queryCartGoods() {

		return ResponseEntity.ok(cartGoods);
	}
	
	@ApiOperation(value = "清空購物車商品")
	@DeleteMapping(value = "/clearCartGoods")
	public ResponseEntity<List<GoodsVo>> clearCartGoods() {
		
	httpSession.removeAttribute("CartGoods");
		
	// 判斷session是否還有購物車商品
	cartGoods = cartGoods != null ? cartGoods : new ArrayList<GoodsVo>();

		return ResponseEntity.ok(cartGoods);
	}
	
}
