/******************************************
项目名称：taotao-manager-service
文件：ItemParamServiceImpl.java
作者：fab
描述：商品规格参数模板服务层接口实现
创建日期：2018年9月10日 下午1:35:15
*******************************************/
package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;













import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamExtendMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExtend;
import com.taotao.service.ItemParamService;


/**
 * 商品规格参数模板服务层接口实现
 * <p>Title: ItemParamServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper itemParamMapper;
    @Autowired
    private TbItemParamExtendMapper itemParamExtendMapper;
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		  //根据cid查询规格参数模板
		TbItemParamExample example=new TbItemParamExample();
		TbItemParamExample.Criteria criteria=example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		 //执行查询
		List <TbItemParam>list=itemParamMapper.selectByExampleWithBLOBs(example);
		//System.out.println("{---------"+list.size()+"-------------}");
		  //判断是否查询到结果
		if(list!=null&& list.size()>0){
			TbItemParam itemParam = list.get(0);
			return TaotaoResult.ok(itemParam);
		}
		return TaotaoResult.ok();
	}
/**
 * 添加商品规格参数
 */
	@Override
	public TaotaoResult insertItemParam(long cid, String paramData) {
		//创建一个pojo
		TbItemParam itemParam=new TbItemParam();
		//设置基本数据
		itemParam.setItemCatId(cid);
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		itemParam.setParamData(paramData);
		 //插入记录
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

	/**
	 * 	商品规格参数查询分页
	 */
	@Override
	public EasyUIDataGridResult getItemParamList(int page, int rows) {
		 //设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		List <TbItemParamExtend>list=itemParamExtendMapper.selectItemParamExtendList();
		//取分页信息
		PageInfo <TbItemParamExtend>pageInfo=new  PageInfo<>(list);
		//创建返回结果对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}
	/**add by fab 20180919
	 * 根据id批量删除
	 */
	@Override
	public TaotaoResult deleteItemParam(List<Long> ids) {
		for (Long id : ids) {
			itemParamMapper.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok();
	}
	
	@Override
	public TaotaoResult getItemParam(Long id) {
		TbItemParam tbItemParam = itemParamMapper.selectByPrimaryKey(id);
		String paramData=tbItemParam.getParamData();
		return TaotaoResult.ok(tbItemParam);
	}
	
	/**
	 * add by fab 20180919
	 * 更新编辑的时间
	 */
	@Override
	public TaotaoResult updateItemParam(TbItemParam tbItemParam) {
		//更新编辑的时间
		tbItemParam.setUpdated(new Date());
		itemParamMapper.updateByPrimaryKey(tbItemParam);
	  return TaotaoResult.ok();
	}

}
