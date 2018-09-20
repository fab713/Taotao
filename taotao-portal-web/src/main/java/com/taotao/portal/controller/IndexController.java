/******************************************
项目名称：taotao-portal-web
文件：IndexController.java
作者：fab
描述：首页展示Controller
创建日期：2018年6月19日 下午4:06:57
 *******************************************/
package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import taotao.taotao.portal.pojo.Ad1Node;

import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;

/**
 * 首页展示Controller
 * <p>Title: IndexController</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Controller
public class IndexController {

	@Value("${AD1_CONTENT_CID}")
	private Long AD1_CONTENT_CID;

	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;

	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;

	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;

	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B;
	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	public String showIndex(Model model) {
		// 取内容分类id，需要从属性文件中取
		// 根据内容分类id查询内容列表
		List<TbContent> list = contentService.getContentList(AD1_CONTENT_CID);
		List<Ad1Node> ad1NodeList = new ArrayList<Ad1Node>();
		for (TbContent content : list) {
			Ad1Node node = new Ad1Node();
			node.setAlt(content.getSubTitle());
			node.setHref(content.getUrl());
			node.setSrc(content.getPic());
			node.setSrcB(content.getPic2());
			node.setHeight(AD1_HEIGHT);
			node.setHeightB(AD1_HEIGHT_B);
			node.setWidth(AD1_WIDTH);
			node.setWidthB(AD1_WIDTH_B);
			ad1NodeList.add(node);
		}
		// 将List集合转成json字符串
		String json = JsonUtils.objectToJson(ad1NodeList);
		model.addAttribute("ad1", json);
		return "index";
	}

}
