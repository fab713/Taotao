/******************************************
项目名称：taotao-sso-interface
文件：UserService.java
作者：fab
描述：TODO
创建日期：2018年8月30日 下午4:14:08
*******************************************/
package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * @author fab
 *
 */
public interface UserService {
	TaotaoResult checkData(String data, int type);
	TaotaoResult register(TbUser user);
	TaotaoResult login(String username, String password);
	TaotaoResult getUserByToken(String token);

}
