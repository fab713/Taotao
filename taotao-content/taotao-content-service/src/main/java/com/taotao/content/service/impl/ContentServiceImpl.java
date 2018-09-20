/******************************************
项目名称：taotao-content-service
文件：ContentServiceImpl.java
作者：fab
描述：内容管理service
创建日期：2018年6月20日 下午1:41:58
*******************************************/
package com.taotao.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;

/**
 * 内容管理service
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;

	
	@Autowired
	private JedisClient jedisClient;
	
    @Value("${INDEX_CONTENT}")
	 private String INDEX_CONTENT; 
/**
 * 新增内容
 */
	@Override
	public TaotaoResult addContent(TbContent content) {
		//补全属性
		content.setCreated(new Date());
		content.setUpdated(new Date());
		//插入数据
		contentMapper.insert(content);
		 // 做缓存同步，清除redis中内容分类id对应的缓存信息
		jedisClient.hdel(INDEX_CONTENT, content.getCategoryId().toString());
		return TaotaoResult.ok();
	}
	/**
	 * 查询内容列表
	 */
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbContentExample example=new TbContentExample();
		List<TbContent>list=contentMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbContent> pageInfo=new PageInfo<> (list);
		//创建返回结果对象
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}
	/**
	 * 修改内容
	 */
	@Override
	public TaotaoResult updateContent(Long parentId, String name) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 删除内容
	 */
	@Override
	public TaotaoResult deleteContent(Long parentId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 首页轮播图展示
	 */
	@Override
	public List<TbContent> getContentList(long cid) {
		// 查询数据库之前，先查询缓存，并且添加缓存不能影响正常业务逻辑
		try{
			String json=jedisClient.hget(INDEX_CONTENT, cid+"");
			 // 判断是否命中缓存，判断json字符串是否为null或""
			if(StringUtils.isNotBlank(json)){
				 // 把这个json转换成List集合
				List<TbContent>list=JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//根据cid查询内容列表
		TbContentExample example=new TbContentExample();
		Criteria Criteria=example.createCriteria();
		//设置查询条件
		Criteria.andCategoryIdEqualTo(cid);
		//执行查询
		List<TbContent>list=contentMapper.selectByExample(example);	
		// 向缓存中保存结果，并且添加缓存不能影响正常业务逻辑
		try{
			jedisClient.hset(INDEX_CONTENT, cid+"", JsonUtils.objectToJson(list));
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * ad by fab 20180919
	 * 编辑内容时，先根据id，获取内容的content
	 */

	@Override
	public TaotaoResult getContent(Long id) {
		TbContent tbContent = contentMapper.selectByPrimaryKey(id);
		String content = tbContent.getContent();
		return TaotaoResult.ok(content);
	}
	/**
	 * add by fab 20180919
	 * 更新编辑的时间
	 */
	@Override
	public TaotaoResult updateContent(TbContent tbContent) {
		//更新编辑的时间
				tbContent.setUpdated(new Date());
				contentMapper.updateByPrimaryKey(tbContent);
				return TaotaoResult.ok();
	}
	/**add by fab 20180919
	 * 根据id批量删除
	 */
	@Override
	public TaotaoResult deleteContent(List<Long> ids) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			contentMapper.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok();
	}

}
