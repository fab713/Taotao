/******************************************
项目名称：taotao-search-service
文件：MyMessageListener.java
作者：fab
描述：接收Activemq发送的消息
创建日期：2018年8月3日 下午1:41:39
*******************************************/
package com.taotao.search.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 接收Activemq发送的消息
 * <p>Title: MyMessageListener</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
public class MyMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			//接收到消息
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			System.out.println(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
