/******************************************
项目名称：taotao-manager-web
文件：ContentController.java
作者：fab
描述：内容管理Controller
创建日期：2018年6月20日 下午4:38:24
*******************************************/
package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;

/**
 * 内容管理Controller
 * <p>Title: ContentController</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	/**
	 * 查询列表
	  2018年6月20日
	 *EasyUIDataGridResult
	 */
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows){
		//查询列表
		EasyUIDataGridResult result=contentService.getItemList(page, rows);
		return result;
	}
	/**
	 * 新增内容
	  2018年6月20日
	 *TaotaoResult
	 */
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult addContent(TbContent content){
		TaotaoResult result=contentService.addContent(content);
		return result;
	}
	/**
	 * add by fab 20180919
	 * 编辑内容时，先根据id，获取内容的content
	 * @param id
	 * @return
	 */
	@RequestMapping("/content/getContent")
	@ResponseBody
	public TaotaoResult getContent(Long id) {
		TaotaoResult result = contentService.getContent(id);
		return result;
		}

	  /**
	   *  更新编辑的内容
	    2018年6月20日
	   *TaotaoResult
	   */
		@RequestMapping("/content/edit")
		@ResponseBody
		public TaotaoResult updateContentCategory(TbContent tbContent){
			TaotaoResult result = contentService.updateContent(tbContent);
			return result;
		}

	
	/**
	 * 删除内容
	  2018年6月20日
	 *TaotaoResult
	 */
		@RequestMapping("/content/delete")
		@ResponseBody
		public TaotaoResult deleteContent(@RequestParam(value="ids")List<Long> ids){
			TaotaoResult result = contentService.deleteContent(ids);
			return result;
		}
	

}
