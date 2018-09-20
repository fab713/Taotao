/******************************************
项目名称：taotao-manager-web
文件：ContentCategoryController.java
作者：fab
描述：内容分类管理Controller
创建日期：2018年6月19日 下午5:34:10
*******************************************/
package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;

/**
 * 内容分类管理Controller
 * <p>Title: ContentCategoryController</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Controller
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);
		return list;
		
	}
	/**
	 * 新增内容分类
	  2018年6月20日
	 *TaotaoResult
	 */
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult addContentCategory(Long parentId, String name){
		TaotaoResult result=contentCategoryService.addContentCategory(parentId, name);
		return result;
	}
  /**
   * 修改内容分类
    2018年6月20日
   *TaotaoResult
   */
	@RequestMapping("/content/category/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(@RequestParam(value="id")Long id, @RequestParam(value="name")String name){
		//System.out.println("{------------"+id+"-----------}");  id是页面保持一致
		//System.out.println("{------------"+name+"-----------}");
		TaotaoResult result=contentCategoryService.updateContentCategory(id, name);
		return result;
	}
/**
 * 删除内容分类
  2018年6月20日
 *TaotaoResult
 */
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(@RequestParam(value="id") Long id){
		//System.out.println("{------------"+id+"-----------}");
		TaotaoResult result = contentCategoryService.deleteContentCategory(id);
		return result;
	}

}
