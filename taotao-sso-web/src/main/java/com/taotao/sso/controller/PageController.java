/******************************************
项目名称：taotao-sso-web
文件：PageController.java
作者：fab
描述：TODO
创建日期：2018年8月30日 下午7:20:00
*******************************************/
package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示登录和注册页面的Controller
 * <p>Title: PageController</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Controller
public class PageController {
	@RequestMapping("/page/register")
	public String showRegister() {
		return "register";
	}
	@RequestMapping("/page/login")
	public String showLogin() {
		return "login";
	}

}
