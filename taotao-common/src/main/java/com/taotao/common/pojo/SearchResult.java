/******************************************
项目名称：taotao-common
文件：SearchResult.java
作者：fab
描述：TODO
创建日期：2018年7月25日 上午10:19:21
*******************************************/
package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author fab
 *
 */
public class SearchResult implements Serializable{
	private long totalPages;
	private long recordCount;
	private List<SearchItem> itemList;
	public long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
	
}
