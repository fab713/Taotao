/******************************************
项目名称：taotao-common
文件：EasyUIDataGridResult.java
作者：fab
描述：TODO
创建日期：2018年6月13日 下午5:14:46
*******************************************/
package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author fab
 *
 */
public class EasyUIDataGridResult implements Serializable {
	private long total; 

	private List rows;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}

}
