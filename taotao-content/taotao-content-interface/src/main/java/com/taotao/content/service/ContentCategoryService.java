/******************************************
项目名称：taotao-content-interface
文件：ContentCategoryService.java
作者：fab
描述：首页内容分类
创建日期：2018年6月19日 下午5:13:57
*******************************************/
package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

/**
 * @author fab
 *
 */
public interface ContentCategoryService {

	List<EasyUITreeNode> getContentCategoryList(long parentId);
	TaotaoResult addContentCategory(Long parentId, String name);
	TaotaoResult updateContentCategory(Long parentId, String name);
	TaotaoResult deleteContentCategory(Long parentId);
}
