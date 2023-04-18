package com.ecommerce.vo;

import java.util.Set;

import lombok.Builder;
import net.minidev.json.annotate.JsonIgnore;

@Builder
public class GenericPageable {
	
	// 當前分頁
	private int currentPageNo;
	
	// 每頁商品數量
	private int pageDataSize;
	
	// 固定分頁數
	@Builder.Default
	private int pagesIconSize = 10;

	// 查尋到的資料筆數
	private int dataTotalSize;
	
	// 最後一頁分頁數
	private int endPageNo;
	
	// 計算出要顯示的分頁
	private Set<Integer> pagination;
	
	// 計算查詢分頁"起始"資料列
	@JsonIgnore
	private int rowStart;
	
	// 計算查詢分頁"結束"資料列
	@JsonIgnore
	private int rowEnd;

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getPageDataSize() {
		return pageDataSize;
	}

	public void setPageDataSize(int pageDataSize) {
		this.pageDataSize = pageDataSize;
	}

	public int getPagesIconSize() {
		return pagesIconSize;
	}

	public void setPagesIconSize(int pagesIconSize) {
		this.pagesIconSize = pagesIconSize;
	}

	public int getDataTotalSize() {
		return dataTotalSize;
	}

	public void setDataTotalSize(int dataTotalSize) {
		this.dataTotalSize = dataTotalSize;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}

	public Set<Integer> getPagination() {
		return pagination;
	}

	public void setPagination(Set<Integer> pagination) {
		this.pagination = pagination;
	}

	public int getRowStart() {
		return rowStart;
	}

	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	public int getRowEnd() {
		return rowEnd;
	}

	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}

	

	
	
	
}
