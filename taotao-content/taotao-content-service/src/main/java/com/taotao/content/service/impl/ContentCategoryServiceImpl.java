/******************************************
项目名称：taotao-content-service
文件：ContentCategoryServiceImpl.java
作者：fab
描述： 内容分类管理service
创建日期：2018年6月19日 下午5:19:01
*******************************************/
package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;

/**
 * 内容分类管理service
 * <p>Title: ContentCategoryServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		// 1、取查询参数id，parentId
		TbContentCategoryExample example=new TbContentCategoryExample();
		//设置查询条件
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 根据parentId查询tb_content_category，查询子节点列表。		
		//执行查询
		List<TbContentCategory>list=tbContentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for(TbContentCategory tbContentCategory:list){
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}	
		return resultList;
	}
	/**
	 * 新增内容分类
	 * <p>Title: ContentCategoryServiceImpl</p>
	 * <p>Description: </p>
	 * <p>Company: www.zl.cn</p> 
	 * @version 1.0
	 */
	@Override
	public TaotaoResult addContentCategory(Long parentId, String name) {
		//创建一个pojo对象
		TbContentCategory contentCategory=new  TbContentCategory();
		//补全对象的属性
		contentCategory.setParentId(parentId);
		//状态。可选值:1(正常),2(删除)
		contentCategory.setName(name);
		contentCategory.setStatus(1);
		//排序，默认为1
		contentCategory.setSortOrder(1);
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());		
		//插入到数据库
		tbContentCategoryMapper.insert(contentCategory);
		//判断父节点的状态
		TbContentCategory parent=tbContentCategoryMapper.selectByPrimaryKey(parentId);		
		if(!parent.getIsParent()){
			//如果父节点为叶子节点应该改为父节点
			parent.setIsParent(true);
			  //更新父节点
			tbContentCategoryMapper.updateByPrimaryKey(parent);
		}
		//返回结果		
		return TaotaoResult.ok(contentCategory);
	}
	/**
	 * 修改内容分类
	 * <p>Title: ContentCategoryServiceImpl</p>
	 * <p>Description: </p>
	 * <p>Company: www.zl.cn</p> 
	 * @version 1.0
	 */
	@Override
	public TaotaoResult updateContentCategory(Long parentId, String name) {
		//根据id获得内容分类
		TbContentCategory contentCategory=tbContentCategoryMapper.selectByPrimaryKey(parentId);	
		//设置更新值
		contentCategory.setName(name);
		//更新值
		tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
		return TaotaoResult.ok();
	}
	/**
	 * 删除内容分类
	 * <p>Title: ContentCategoryServiceImpl</p>
	 * <p>Description: </p>
	 * <p>Company: www.zl.cn</p> 
	 * @version 1.0
	 */
	@Override
	public TaotaoResult deleteContentCategory(Long parentId) {
	
		//根据id删除记录。
		//判断父节点下是否还有子节点，如果没有需要把父节点的isparent改为false
		//如果删除的是父节点，子节点要级联删除。
		///两种解决方案：
		//1）如果判断是父节点不允许删除。
		///2）递归删除。

		 try {
	            TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
	            if(tbContentCategory != null){
	                //删除当前节点
	                tbContentCategoryMapper.deleteByPrimaryKey(parentId);
	                //递归删除子节点
	                this.recursiveDeleteCategory(parentId);
	                //判断父节点状态，修改父节点状态
	                this.updateParentNodeStatus(tbContentCategory.getParentId());
	            }
	        }catch (Exception e){
	            return this.getReturnResult(500,"后台报错：数据库操作异常！",null);
	        }
	        return TaotaoResult.ok();
	}
	
    private void updateParentNodeStatus(Long parentId){
        //查询父id是否还有子节点
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        if(list.size()==0){
            //修改父节点状态为叶子节点（因为删除此节点后，父节点再无子节点）
            TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
            if(parent.getIsParent()){
                parent.setIsParent(false);
                //修改父节点状态
                this.updateParentStatus(parent);
            }
        }
    }
	
	 private void recursiveDeleteCategory(Long parentId){
	        TbContentCategoryExample example = new TbContentCategoryExample();
	        TbContentCategoryExample.Criteria criteria = example.createCriteria();
	        criteria.andParentIdEqualTo(parentId);
	        try {
	            List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
	            for(TbContentCategory contentCategory : list){
	                if(contentCategory.getIsParent()){
	                    tbContentCategoryMapper.deleteByPrimaryKey(contentCategory.getId());
	                    //递归循坏
	                    this.recursiveDeleteCategory(contentCategory.getId());
	                }else {
	                    //删除
	                    tbContentCategoryMapper.deleteByPrimaryKey(contentCategory.getId());
	                }
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }

	    }
	  private TaotaoResult getReturnResult(Integer status, String msg, Object obj){
		  TaotaoResult result = new TaotaoResult(status,msg,obj);
	        return result;
	    }
	  private void updateParentStatus(TbContentCategory parent){
	        tbContentCategoryMapper.updateByPrimaryKey(parent);
	    }

}
