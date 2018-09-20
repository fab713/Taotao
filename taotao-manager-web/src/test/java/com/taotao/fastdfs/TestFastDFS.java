/******************************************
项目名称：taotao-manager-web
文件：TestFastDFS.java
作者：fab
描述：TODO
创建日期：2018年6月15日 下午5:36:47
*******************************************/
package com.taotao.fastdfs;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.taotao.utils.FastDFSClient;


/**
 * @author fab
 *
 */
public class TestFastDFS {
	@Test
	public void uploadFile() throws Exception {
		//加载配置文件，配置文件中的内容就是tracker服务的地址。
		//配置文件内容：tracker_server=192.168.25.133:22122
		ClientGlobal.init("E:/TaoTaoWorkspace/taotao-manager-web/src/main/resources/resource/client.conf");
		//创建一个TrackerClient对象。直接new一个。
		TrackerClient trackerClient=new TrackerClient();
		//使用TrackerClient对象创建连接，获得一个TrackerServer对象。
		TrackerServer trackerServer=trackerClient.getConnection();
		//创建一个StorageServer的引用，值为null
		StorageServer storageServer=null;
		//创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
		StorageClient storageClient=new StorageClient(trackerServer,storageServer);
		//使用StorageClient对象上传图片。
		String []strings=storageClient.upload_file("C:/Users/Public/Pictures/Sample Pictures/考拉.jpg", "jpg", null);
		//返回数组。包含组名和图片的路径。
		for(String a:strings){
			System.out.println(a);
			
		}
	}
	
	@Test
	public void testFastDfsClient() throws Exception {
		FastDFSClient fastDFSClient = new FastDFSClient("E:/TaoTaoWorkspace/taotao-manager-web/src/main/resources/resource/client.con");
		String string = fastDFSClient.uploadFile("C:/Users/Public/Pictures/Sample Pictures/考拉.jpg");
		System.out.println(string);
	}



}
