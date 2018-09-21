/******************************************
项目名称：taotao-manager-pojo
文件：TbItemParamExtend.java
作者：fab
描述：规格参数扩展类
创建日期：2018年9月10日 下午1:48:48
*******************************************/
package com.taotao.pojo;

/**
 * @author fab
 *
 */
public class TbItemParamExtend extends TbItemParamItem {
	 private String  itemCatName;//商品类目名

	    public String getItemCatName() {
	        return itemCatName;
	    }

	    public void setItemCatName(String itemCatName) {
	        this.itemCatName = itemCatName;
	    }

}
