/******************************************
项目名称：taotao-manager-interface
文件：ItemCatService.java
作者：fab
描述：获得商品信息
创建日期：2018年6月15日 下午4:04:39
*******************************************/
package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;

/**
 * @author fab
 *
 */
public interface ItemCatService {
	//根据parentId查询节点列表
	List<EasyUITreeNode>  getItemCatList(long parentId);
}
