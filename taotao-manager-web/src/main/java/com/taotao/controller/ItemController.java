/******************************************
项目名称：taotao-manager-web
文件：ItemController.java
作者：fab
描述：商品管理
创建日期：2018年6月13日 下午3:46:13
*******************************************/
package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * 商品管理Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		//根据商品id查询商品信息
		TbItem tbItem=itemService.getItemById(itemId);
		return tbItem;
	}
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows){
		//查询列表
		EasyUIDataGridResult result=itemService.getItemList(page, rows);
		return result;
	}
	/**
	 * 新增商品
	  2018年6月19日
	 *TaotaoResult
	 */
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult addItem(TbItem item, String desc) {
		TaotaoResult result=itemService.addItem(item, desc);
		return  result;
	}
	/**
	 * 实现商品的上架、下架、删除
	 * 更新商品状态
	 * TaotaoResult
	 */
	@RequestMapping("/rest/item/{method}")
	@ResponseBody
	public TaotaoResult updateItemStatus(@RequestParam(value="ids") List<Long> ids, @PathVariable String method) {
		for(Long id: ids){
			System.out.println("{--------控制层------------"+id+"---------------------}");
		}
		TaotaoResult result=itemService.updateItemStatus(ids, method);
		return  result;
	}
	/**
	 * 实现商品的编辑
	 * TaotaoResult
	 */
	@RequestMapping("/rest/item/update")
	@ResponseBody
	public TaotaoResult updateItem( TbItem item, String desc) {

		TaotaoResult result=itemService.updateItem(item, desc);
		return  result;
	}
	/**
	*   根据id获取商品描述
	 */
	@RequestMapping(value="/rest/item/desc/{id}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable("id") Long id) {
		TaotaoResult result = itemService.getItemDesc(id);
		return result;
	}
}
