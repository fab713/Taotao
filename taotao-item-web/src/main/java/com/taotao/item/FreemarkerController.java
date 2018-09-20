/******************************************
项目名称：taotao-item-web
文件：FreemarkerController.java
作者：fab
描述：网页静态处理Controller
创建日期：2018年8月28日 下午3:13:30
*******************************************/
package com.taotao.item;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 网页静态处理Controller
 * <p>Title: HtmlGenController</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Controller
public class FreemarkerController {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@RequestMapping("/genhtml")
	@ResponseBody
	public String genHtml() throws Exception {
		//生成静态页面
		Configuration configuration=freeMarkerConfigurer.getConfiguration();
		Template template=configuration.getTemplate("hello.ftl");
		Map data=new HashMap();
	    FileWriter writer = new FileWriter(new File("E:/Freemarker/hello.htm"));		
		template.process(data, writer);
		writer.close();
		//返回结果
		return "OK";
		
	}
}
