/******************************************
项目名称：taotao-manager-web
文件：IndexManagerController.java
作者：fab
描述：索引库维护
创建日期：2018年7月24日 下午3:51:38
*******************************************/
package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.SearchItemService;

/**
 * 索引库维护Controller
 * <p>Title: IndexManagerController</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Controller
public class IndexManagerController {
	@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/index/import")
	@ResponseBody
	public TaotaoResult importAllItems() {
		try {
			TaotaoResult taotaoResult = searchItemService.importItemsToIndex();
			return taotaoResult;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, "导入数据失败");
		}
	}

}
