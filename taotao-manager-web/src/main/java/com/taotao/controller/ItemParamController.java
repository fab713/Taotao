/******************************************
项目名称：taotao-manager-web
文件：ItemParamController.java
作者：fab
描述：商品规格参数模板
创建日期：2018年9月10日 下午2:30:36
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
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

/**
 *商品规格参数模板Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Controller
public class ItemParamController {
	
    @Autowired
    private ItemParamService itemParamService;
	/**
	 * 通过商品类目id获取规格参数模板
	 */
	@RequestMapping("/item/param/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult getItemCatByCid(@PathVariable Long cid) {
		//根据商品id查询商品信息
		TaotaoResult result=itemParamService.getItemParamByCid(cid);
		return result;
	}
	/**
	 * 获得规格参数模板列表
	 */
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows){
		//查询列表
		EasyUIDataGridResult result=itemParamService.getItemParamList(page, rows);
		return result;
	}
	/**
	 * 插入规格参数模板
	 */
	@RequestMapping(value="/item/param/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
		TaotaoResult result=itemParamService.insertItemParam(cid, paramData);
		return  result;
	}
	/**
	 * 规格参数删除
	  2018年9月19日
	 *TaotaoResult
	 */
		@RequestMapping("/item/param/delete")
		@ResponseBody
		public TaotaoResult deleteItemParam(@RequestParam(value="ids")List<Long> ids){
			TaotaoResult result = itemParamService.deleteItemParam(ids);
			return result;
		}
		/**
		 * add by fab 20180919
		 * 先根据id，获取参数的内容
		 * @param id
		 * @return
		 */
		@RequestMapping("/item/param/getItemParam")
		@ResponseBody
		public TaotaoResult ItemParam(@PathVariable String id) {
			System.out.println("{------"+id+"-------------}");
			//根据商品id查询商品信息
			TaotaoResult result=itemParamService.getItemParam(Long.valueOf(id));
			return result;
			}

		  /**
		   *  更新编辑的内容
		    20180919
		   *TaotaoResult
		   */
			@RequestMapping("/item/param/edit")
			@ResponseBody
			public TaotaoResult updateContentCategory(TbItemParam tbItemParam){
				System.out.println("4444444444444444");
				TaotaoResult result = itemParamService.updateItemParam(tbItemParam);
				return result;
			}
}
