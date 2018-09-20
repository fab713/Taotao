/******************************************
项目名称：taotao-manager-service
文件：ItemServiceImpl.java
作者：hspcadmin
描述：TODO
创建日期：2018年6月13日 下午3:23:18
 *******************************************/
package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

/**
 * 
 * @author fab
 * 
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource(name = "itemAddtopic")
	private Destination destination;

	@Autowired
	private JedisClient jedisClient;

	@Value("${ITEM_INFO}")
	private String ITEM_INFO;
	@Value("${TIEM_EXPIRE}")
	private Integer TIEM_EXPIRE;

	/**
	 * 根据商品id查询商品信息
	 */
	@Override
	public TbItem getItemById(long itemId) {
		// TODO Auto-generated method stub
		// 查询数据库之前先查询缓存
		try {
			String json = jedisClient.get(ITEM_INFO + ":" + itemId + ":BASE");
			if (StringUtils.isNotBlank(json)) {
				// 把json数据转换成pojo
				TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
				return tbItem;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		// 缓存中没有查询数据库
		TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
		try {
			// 把查询结果添加到缓存
			jedisClient.set(ITEM_INFO + ":" + itemId + ":BASE",
					JsonUtils.objectToJson(tbItem));
			// 设置过期时间，提高缓存的利用率
			jedisClient.expire(ITEM_INFO + ":" + itemId + ":BASE", TIEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tbItem;
	}

	/**
	 * 查询所有商品列表，要进行分页处理
	 */
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// TODO Auto-generated method stub
		// 设置分页信息
		PageHelper.startPage(page, rows);
		// 执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		// 取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		// 创建返回结果对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	/**
	 * 添加商品
	 */
	@Override
	public TaotaoResult addItem(TbItem item, String desc) {
		// 1、生成商品id
		// long id=IDUtils.genItemId();//152939035715290
		final long itemId = IDUtils.genItemId();
		// 2、补全TbItem对象的属性
		// 商品状态，1-正常，2-下架，3-删除
		item.setId(itemId);
		item.setStatus((byte) 1);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		// 3、向商品表插入数据
		tbItemMapper.insert(item);
		// 4、创建一个TbItemDesc对象
		TbItemDesc itemDesc = new TbItemDesc();
		// 5、补全TbItemDesc的属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		// 6、向商品描述表插入数据
		tbItemDescMapper.insert(itemDesc);
		// 商品添加完成后发送一个MQ消息，为了添加solr的索引库 add by fab 20180803
		jmsTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// 创建一个消息对象
				// 要在匿名内部类访问局部变量itemId，itemId需要用final修饰
				TextMessage textMessage = session
						.createTextMessage(itemId + "");
				return textMessage;
			}

		});

		return TaotaoResult.ok();
	}

	@Override
	public TbItemDesc getItemDescById(long itemId) {
		// TODO Auto-generated method stub

		// 查询数据库之前先查询缓存
		try {
			String json = jedisClient.get(ITEM_INFO + ":" + itemId + ":DESC");
			if (StringUtils.isNotBlank(json)) {
				// 把json数据转换成pojo
				TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json,
						TbItemDesc.class);
				return tbItemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 缓存中没有查询数据库
		TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		try {
			// 把查询结果添加到缓存
			jedisClient.set(ITEM_INFO + ":" + itemId + ":DESC",
					JsonUtils.objectToJson(itemDesc));
			// 设置过期时间，提高缓存的利用率
			jedisClient.expire(ITEM_INFO + ":" + itemId + ":DESC", TIEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}

	/**
	 * 实现商品的上架、下架、删除 根据id，更新商品状态1-正常，2-下架，3-删除 更新商品状态 TaotaoResult
	 */
	@Override
	public TaotaoResult updateItemStatus(List<Long> ids, String method) {
		TbItem item = new TbItem();
		if ("reshelf".equals(method)) {
			item.setStatus((byte) 1);
			item.setUpdated(new Date());
		} else if ("instock".equals(method)) {
			item.setStatus((byte) 2);
			item.setUpdated(new Date());
		} else if ("delete".equals(method)) {
			item.setStatus((byte) 3);
			item.setUpdated(new Date());
		}
		for(Long id: ids){
			System.out.println("{--------服务层------------"+id+"---------------------}");
		}
		for(Long id: ids){
			// 创建查询条件，根据id更新
			TbItemExample example=new TbItemExample();
			TbItemExample.Criteria	criteria=example.createCriteria();
			criteria.andIdEqualTo(id);
			// 第一个参数 是要修改的部分值组成的对象，其中有些属性为null则表示该项不修改。
		   // 第二个参数 是一个对应的查询条件的类， 通过这个类可以实现 order by 和一部分的where 条件。
			tbItemMapper.updateByExampleSelective(item, example);
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateItem(TbItem item, String desc) {
		// 1.根据商品id更新商品表
		Long id = item.getId();
		// 创建查询条件，根据id更新商品表
		TbItemExample tbItemExample = new TbItemExample();
		Criteria criteria = tbItemExample.createCriteria();
		criteria.andIdEqualTo(id);
		tbItemMapper.updateByExampleSelective(item, tbItemExample);
			
		// 2.根据商品id更新商品描述表
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		TbItemDescExample tbItemDescExample = new TbItemDescExample();
		com.taotao.pojo.TbItemDescExample.Criteria createCriteria = tbItemDescExample.createCriteria();
		createCriteria.andItemIdEqualTo(id);
		tbItemDescMapper.updateByExampleSelective(itemDesc, tbItemDescExample);
		return TaotaoResult.ok();
	}
/**
 * 根据id查询商品描述，封装到TaotaoResult中
 */
	@Override
	public TaotaoResult getItemDesc(Long id) {
		// TODO Auto-generated method stub
		TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(id);
		return TaotaoResult.ok(itemDesc);
	}
}
