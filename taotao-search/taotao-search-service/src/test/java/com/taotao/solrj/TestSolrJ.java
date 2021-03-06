/******************************************
项目名称：taotao-search-service
文件：TestSolrJ.java
作者：fab
描述：solr的junit测试
创建日期：2018年7月24日 下午3:02:49
*******************************************/
package com.taotao.solrj;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @author fab
 *
 */
public class TestSolrJ {
    @Test
    public void testSolrJAddDocument() throws Exception {
        // 创建一个SolrServer对象，即HttpSolrServer对象，需要指定solr服务的url
        // 如果有多个collection，则需要指定要操作哪个collection，如果只有一个，可以不指定
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.175:8080/solr");
        // 创建一个文档对象，即SolrInputDocument对象
        SolrInputDocument document = new SolrInputDocument();
        // 向文档中添加域，添加域这里面有一个要求，必须有一个id域，域必须在schema.xml中定义
        document.addField("id", "test001");
        document.addField("item_title", "海尔空调");
        document.addField("item_sell_point", "送电暖宝一个哟！");
        document.addField("item_price", 10000);
        document.addField("item_image", "http://www.123.ipg");
        document.addField("item_category_name", "电器");
        document.addField("item_desc", "这是一款最新的空调，质量好，值得您信赖！！");
        // 把文档写入索引库
        solrServer.add(document);
        // 提交
        solrServer.commit();
    }
    
    @Test
    public void testSolrJDeleteDocument() throws Exception {
        // 创建一个SolrServer对象，即HttpSolrServer对象，需要指定solr服务的url
        SolrServer solrServer = new HttpSolrServer("http://192.168.25.175:8080/solr");
        // 通过id来删除文档
        solrServer.deleteById("test001");
        // 提交
        solrServer.commit();
    }
	@Test
	public void searchDocumet() throws Exception {
		//创建一个SolrServer对象
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.175:8080/solr/collection1");
		//创建一个SolrQuery对象
		SolrQuery query = new SolrQuery();
		//设置查询条件、过滤条件、分页条件、排序条件、高亮
		//query.set("q", "*:*");
		query.setQuery("手机");
		//分页条件
		query.setStart(0);
		query.setRows(10);
		//设置默认搜索域
		query.set("df", "item_keywords");
		//设置高亮
		query.setHighlight(true);
		//高亮显示的域
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<div>");
		query.setHighlightSimplePost("</div>");
		//执行查询，得到一个Response对象
		QueryResponse response = solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		//取查询结果总记录数
		System.out.println("查询结果总记录数：" + solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			//取高亮显示
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String itemTitle = "";
			if (list != null && list.size() >0) {
				itemTitle = list.get(0);
			} else {
				itemTitle = (String) solrDocument.get("item_title");
			}
			System.out.println(itemTitle);
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
			System.out.println("=============================================");
		}
		
	}

}
