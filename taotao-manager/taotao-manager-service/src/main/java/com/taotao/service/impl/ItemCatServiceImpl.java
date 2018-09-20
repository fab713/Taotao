/******************************************
项目名称：taotao-manager-service
文件：ItemCatServiceImpl.java
作者：fabb
描述：商品分类管理Service
创建日期：2018年6月15日 下午4:12:59
 *******************************************/
package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * @author fab
 * 
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		// 根据父节点id查询子节点列表
		TbItemCatExample itemCatExample = new TbItemCatExample();
		// 设置查询条件
		Criteria criteria = itemCatExample.createCriteria();
		// 设置parentid
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = tbItemCatMapper.selectByExample(itemCatExample);
		// 转换成EasyUITreeNode列表
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			// 如果节点下有子节点“closed”，如果没有子节点“open”
			node.setState(tbItemCat.getIsParent() ? "closed" : "open");
			// 添加到节点列表
			resultList.add(node);
		}
		return resultList;
	}

}
