/******************************************
项目名称：taotao-item-web
文件：Item.java
作者：hspcadmin
描述：TODO
创建日期：2018年8月6日 下午1:51:48
*******************************************/
package com.taotao.item.pojo;

import com.taotao.pojo.TbItem;

/**
 * @author fab
 *
 */
public class Item extends TbItem {
	public Item(TbItem tbItem) {
		//初始化属性
		this.setId(tbItem.getId());
		this.setTitle(tbItem.getTitle());
		this.setSellPoint(tbItem.getSellPoint());
		this.setPrice(tbItem.getPrice());
		this.setNum(tbItem.getNum());
		this.setBarcode(tbItem.getBarcode());
		this.setImage(tbItem.getImage());
		this.setCid(tbItem.getCid());
		this.setStatus(tbItem.getStatus());
		this.setCreated(tbItem.getCreated());
		this.setUpdated(tbItem.getUpdated());
	}
	
	public String[] getImages() {
		if (this.getImage()!=null && !"".equals(this.getImage())) {
			String image2 = this.getImage();
			String[] strings = image2.split(",");
			return strings;
		}
		return null;
	}
}
