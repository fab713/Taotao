/******************************************
项目名称：taotao-manager-web
文件：PictureController.java
作者：fab
描述：图片上传controller
创建日期：2018年6月16日 上午12:12:06
*******************************************/
package com.taotao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

import com.taotao.common.utils.JsonUtils;
import com.taotao.utils.FastDFSClient;

/**
 * 图片上传controller
 * <p>Title: PictureController</p>
 * <p>Description: </p>
 * <p>Company: www.zl.cn</p> 
 * @version 1.0
 */
@Controller
public class PictureController {
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE + "; charset=utf-8")
	@ResponseBody
	 public String picUpload(MultipartFile uploadFile){
		
		try {
			//接收上传的文件
			String originalFilename=uploadFile.getOriginalFilename();
			//取扩展名
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			System.out.println("{----扩展名-----"+extName+"------------}");
			//上传到图片服务器
			FastDFSClient fastDFSClient=new FastDFSClient("classpath:resource/client.conf");
			String url=fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			System.out.println("{----IMAGE_SERVER_URL=-----"+IMAGE_SERVER_URL+"------------}");
			System.out.println();
			url=IMAGE_SERVER_URL+url;
			System.out.println("{----响应上传图片的url-----"+url+"------------}");
			//http://192.168.25.133/group1/M00/00/00/wKgZhVsoeIuAHMTrAA1rIuRd3Es156.jpg
			///home/fastdfs/store_path0/data/00/00
			Map result = new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			//将java对象转化成json数据
			return JsonUtils.objectToJson(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			return JsonUtils.objectToJson(result);
		}
		
	 }

}
