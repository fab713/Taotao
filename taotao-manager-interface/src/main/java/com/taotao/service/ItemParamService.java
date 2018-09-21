/******************************************
项目名称：taotao-manager-interface
文件：ItemParamService.java
作者：fab
描述：TODO
创建日期：2018年9月10日 下午1:30:30
*******************************************/
package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * @author fab
 *
 */
public interface ItemParamService {
	TaotaoResult getItemParamByCid(long cid);

	TaotaoResult insertItemParam(long cid, String paramData);

    EasyUIDataGridResult getItemParamList(int page, int rows);
    
    TaotaoResult deleteItemParam(List<Long> ids);
    
    TaotaoResult getItemParam(Long id);
    TaotaoResult updateItemParam(TbItemParam tbItemParam);
}
