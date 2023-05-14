package com.ecommerce.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.ecommerce.vo.GoodsVo;
import com.ecommerce.vo.MemberInfo;
import com.ecommerce.vo.MemberInfoVo;

@Configuration
public class MemberConfig {
	
	@Bean
	@SessionScope
	public MemberInfo SessionMemberInfo(){
	      return MemberInfo.builder().isLogin(false).build();
	}
	
	@Bean
	@SessionScope
	public MemberInfoVo SessionMemberInfoVo(){
	      return MemberInfoVo.builder().build();
	}
	
	@Bean
	@SessionScope
	public List<GoodsVo> SessionCartGoods(){
	      return new ArrayList<GoodsVo>();
	}
	
}
