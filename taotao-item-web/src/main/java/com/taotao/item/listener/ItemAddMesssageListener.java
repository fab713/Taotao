/******************************************
项目名称：taotao-item-web
文件：ItemAddMesssageListener.java
作者：fab
描述：监听商品添加时事件
创建日期：2018年8月28日 下午4:25:07
*******************************************/
package com.taotao.item.listener;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 监听商品添加时事件，然后生成商品静态页面
 * <p>Title: HtmlGenListener</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
public class ItemAddMesssageListener implements MessageListener {
	@Autowired
	private ItemService itemService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Value("${HTML_OUT_PATH}")
	private String HTML_OUT_PATH;
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try{
			System.out.println("{-------生成ftl开始-------}");
			//从消息中取商品id
			TextMessage textMessage = (TextMessage) message;
			String strId = textMessage.getText();
			Long itemId = Long.parseLong(strId);
		    // 3、根据商品id查询商品基本消息、商品描述。（即数据集已准备完毕）
            /*
             * 等待事务的提交，采用三次尝试的机会
             * 
             * 根据商品id查询商品基本信息，这里需要注意的是消息发送方法
             * 有可能还没有提交事务，因此这里是有可能取不到商品信息的，
             * 为了避免这种情况出现，我们最好等待事务提交，这里我采用3次
             * 尝试的方法，每尝试一次休眠一秒   
             */
            TbItem tbItem = null;
//            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000); // 休眠一秒
                tbItem = itemService.getItemById(itemId);
                // 如果获取到了商品基本信息，那就退出循环
//                if (tbItem != null) {
//                    break;
//                }
//            }
			Item item = new Item(tbItem);
			TbItemDesc itemDesc = itemService.getItemDescById(itemId);
			//使用freemarker生成静态页面
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			//1.创建模板
			//2.加载模板对象
			Template template = configuration.getTemplate("item.ftl");
			//3.准备模板需要的数据
			Map data = new HashMap<>();
			data.put("item", item);
			data.put("itemDesc", itemDesc);
			//4.指定输出的目录及文件名
			Writer out = new FileWriter(new File(HTML_OUT_PATH + strId + ".html"));
			//5.生成静态页面
			template.process(data, out);
			//关闭流
			out.close();
			System.out.println("{-------生成ftl结束-------}");
		}catch (Exception e){
			e.printStackTrace();
			
		}
	}

}
