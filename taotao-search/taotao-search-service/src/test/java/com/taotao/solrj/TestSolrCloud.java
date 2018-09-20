/******************************************
项目名称：taotao-search-service
文件：TestSolrCloud.java
作者：fab
描述：Solr集群测试
创建日期：2018年8月2日 上午11:21:33
*******************************************/
package com.taotao.solrj;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * @author fab
 *
 */
public class TestSolrCloud {
	@Test
    public void testSolrCloud() throws Exception {
        // 操作步骤：
        // 第一步，创建一个SolrServer对象，需要使用CloudSolrServer子类对象，它有一个构造方法，
        // 构造方法有一个参数，叫做zkHost，是一个字符串类型，也即zookeeper的地址列表
        CloudSolrServer solrServer = new CloudSolrServer("192.168.25.175:2181,192.168.25.175:2182,192.168.25.175:2183");
        // 第二步，需要指定默认的collection
        solrServer.setDefaultCollection("collection2");
        // 第三步，向索引库中添加文档，和单机版一致
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id", "1");
        document.setField("item_title", "测试商品");
        document.setField("item_price", "199");
        solrServer.add(document);
        // 第四步，提交
        solrServer.commit();
    }

}
