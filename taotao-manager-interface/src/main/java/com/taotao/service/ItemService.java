/******************************************
项目名称：taotao-manager-interface
文件：ItemService.java
作者：fab
描述：TODO
创建日期：2018年6月13日 下午3:20:23
*******************************************/
package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;


public interface ItemService {
	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page, int rows);
	TaotaoResult addItem(TbItem item, String desc);
	TbItemDesc getItemDescById(long itemId);
	TaotaoResult updateItemStatus(List<Long> ids, String method);
    TaotaoResult updateItem(TbItem item, String desc);
    TaotaoResult getItemDesc(Long id);

}
