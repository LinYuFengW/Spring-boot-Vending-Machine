<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.math.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-tw">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- Required meta tags -->
<meta charset="utf-8">


<title>販賣機-後臺</title>

	<script type="text/javascript">
	$(document).ready(function(){		
		$.ajax({
			  url: '<%=basePath%>/restfulJpaController/JpafindAllGoods', // 指定要進行呼叫的位址
			  type: "POST", // 請求方式 POST/GET
			  // data: fromData, // 傳送至 Server的請求資料(物件型式則為 Key/Value pairs)
			  dataType : 'json', // Server回傳的資料類型
			  async : true, // 是否同部請求
			  cache : false, // 從瀏覽器中抓 cache
			  success: function(goodsList) { // 請求成功時執行函式					  	
			  	loadgoodsList(goodsList);
				alert("載入成功!");
			  }, 
			  error: function() { // 請求發生錯誤時執行函式
			    alert("載入失敗!");
			  }
		});	
	});
	</script>
	
	<script type="text/javascript">
	function loadgoodsList(goodsList){		
		$.each(goodsList.loadgoods, function(index, goods){
			var NO = index+1
			var goodsEach = "<tr>"								
				goodsEach += "<td>" + goods.goodsID + "</td>"
				goodsEach += "<td>" + goods.goodsName + "</td>"
				goodsEach += "<td>" + goods.goodsPrice + "</td>"
				goodsEach += "<td>" + goods.goodsQuantity + "</td>"
				goodsEach += "<td>" + goods.status + "</td>"
				goodsEach += "</tr>"				
			$("#goodsListForEach").append(goodsEach);
	  	});
	}
	</script>

</head>
<body>
	<%@ include file="VM_Backend_FunMenu.jsp" %>
	<br/>
		
	<h4 class="text-center" style="color:#FF7878;">商品列表</h4>
	
	<table class="table table-striped">
	  <thead>
	    <tr>
	      <th scope="col">商品編號</th>
	      <th scope="col">商品名稱</th>	      
	      <th class="text-center" scope="col">商品價格</th>
	      <th class="text-center" scope="col">現有庫存</th>
	      <th class="text-center" scope="col">商品狀態</th>
	    </tr>
	  </thead>	  	  
	  
	  <tbody id="goodsListForEach"></tbody>
	</table>
	
</body>
</html>