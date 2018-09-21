/******************************************
项目名称：taotao-order-interface
文件：OrderService.java
作者：fab
描述：TODO
创建日期：2018年9月3日 下午1:59:37
*******************************************/
package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.OrderInfo;

/**
 * @author fab
 *
 */
public interface OrderService {
	
	TaotaoResult createOrder(OrderInfo orderInfo);

}
