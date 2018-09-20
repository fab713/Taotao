/******************************************
项目名称：taotao-search-service
文件：TestSpringActiveMq.java
作者：hspcadmin
描述：TODO
创建日期：2018年8月3日 下午1:46:46
*******************************************/
package com.taotao.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author fab
 *
 */
public class TestSpringActiveMq {
	@Test
	public void testSpringActiveMq() throws Exception {
		//初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		//等待
		System.in.read();
	}

}
