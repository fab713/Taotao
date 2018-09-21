/******************************************
项目名称：taotao-search-interface
文件：SearchService.java
作者：fab
描述：商品搜索服务
创建日期：2018年7月25日 上午10:28:54
*******************************************/
package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;

/**
 * @author fab
 *
 */
public interface SearchService {
	SearchResult search(String queryString, int page, int rows) throws Exception;
}
