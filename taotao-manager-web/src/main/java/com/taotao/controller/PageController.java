/******************************************
项目名称：taotao-manager-web
文件：PageController.java
作者：fab
描述：TODO
创建日期：2018年6月13日 下午4:51:43
 *******************************************/
package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页的跳转Controller
 * <p>Title: PageController</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Controller
public class PageController {
	/**
	 * 首页
	  2018年9月12日
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
/**
 * 其他页面
 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}


}
