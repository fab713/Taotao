/******************************************
项目名称：taotao-content-interface
文件：ContentService.java
作者：fab
描述：新增内容
创建日期：2018年6月19日 下午5:15:18
*******************************************/
package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * @author fab
 *
 */
public interface ContentService {
	
	EasyUIDataGridResult getItemList(int page, int rows);
	TaotaoResult addContent(TbContent content);
	TaotaoResult updateContent(Long parentId, String name);
	TaotaoResult deleteContent(Long parentId);
	public List<TbContent> getContentList(long cid);

   /**
	 * 获取content
	 * @param id
	 * @return
	 */
	TaotaoResult getContent(Long id);
	

/**
	 * 更新编辑的内容
	 * @param tbContent
	 */
	TaotaoResult updateContent(TbContent tbContent);
	
	/**
	 * 根据id批量删除
	 * @param ids
	 * @return
	 */
	TaotaoResult deleteContent(List<Long> ids);
	

}
