/******************************************
项目名称：taotao-search-service
文件：SearchItemMapper.java
作者：fab
描述：TODO
创建日期：2018年7月24日 下午2:52:02
*******************************************/
package com.taotao.search.mapper;

import java.util.List;

import com.taotao.common.pojo.SearchItem;

/**
 * @author fab
 *
 */
public interface SearchItemMapper {
	List<SearchItem> getItemList();
	SearchItem getItemById(long itemId);
}
