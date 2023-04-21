package com.ecommerce.vo;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class MemberInfo {
	
	// 是否登入
	private Boolean isLogin;
	
	// 登入狀態訊息
	private String loginMessage;
	
	// 身份證號碼
	private String identificationNo;
	
	// 顧客姓名
	private String cusName;
	
	// 密碼
	private String cusPassword;
	
	// 登入時間
	private String loginDate;
	
	// 登出時間
	private String logoutDate;
	
	// 電話號碼
	private String phone;
	
	// 電子信箱
	private String email;
	
}
