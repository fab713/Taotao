/******************************************
项目名称：taotao-common
文件：EasyUITreeNode.java
作者：fab
描述：tree的节点信息
创建日期：2018年6月15日 下午4:01:20
*******************************************/
package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * @author fab
 *
 */
public class EasyUITreeNode implements Serializable {
	private long id;
	private String text;
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
