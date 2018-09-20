/******************************************
项目名称：taotao-item-web
文件：TestFreeMarker.java
作者：fab
描述：FreeMaker测试
创建日期：2018年8月28日 下午2:16:32
*******************************************/
package com.taotao.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author fab
 *
 */
public class TestFreeMarker {
	@Test
	public void testFreeMarker()throws Exception{
		// 创建一个Configuration对象
		Configuration configuration=new Configuration();
		  // 设置模板所在的目录
		configuration.setDirectoryForTemplateLoading(new File("E:/TaoTaoWorkspace/taotao-item-web/src/main/resources/ftl"));
		// 设置模板字符集
		configuration.setDefaultEncoding("UTF-8");
		 // 加载模板文件
		Template template=configuration.getTemplate("first.htm");
		// 创建一个数据集
		Map data=new HashMap();
		data.put("title", "Hello Freemarker!!!");
		data.put("stu", new Student(1, "小明", 18, "温都水城"));
		List <Student>list=new ArrayList<Student>();
		list.add(new Student(1, "小明1", 18, "温都水城"));
        list.add(new Student(2, "小明2", 17, "温都水城"));
        list.add(new Student(3, "小明3", 16, "温都水城"));
        list.add(new Student(4, "小明4", 15, "温都水城"));
        list.add(new Student(5, "小明5", 14, "温都水城"));
        list.add(new Student(6, "小明6", 13, "温都水城"));
        list.add(new Student(7, "小明7", 12, "温都水城"));
        data.put("stuList", list);
        data.put("date",new Date());
		// 设置模板输出的目录及输出的文件名
	       FileWriter writer = new FileWriter(new File("E:/Freemarker/first.htm"));		
		 // 生成文件
	       template.process(data, writer);
		 // 关闭流
	       writer.close();
		
		
	}
	

}
