/******************************************
项目名称：taotao-content-service
文件：TestJedis.java
作者：fab
描述：测试
创建日期：2018年6月26日 下午2:45:14
*******************************************/
package com.taotao.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * @author fab
 *
 */

public class TestJedis {
	@Test
	public void testJedis() throws Exception {
		//创建一个jedis对象，需要指定服务的ip和端口号
		Jedis jedis = new Jedis("192.168.25.175", 6380);
		//直接操作数据库
		jedis.set("jedis-key", "1234");
		String result = jedis.get("jedis-key");
		System.out.println(result);
		//关闭jedis
		jedis.close();
	}
	
	@Test
	public void testJedisCluster() throws Exception {
		// 第一步：使用JedisCluster对象。需要一个Set<HostAndPort>参数。Redis节点的列表。
		Set<HostAndPort>nodes=new HashSet<>();
		nodes.add(new HostAndPort("192.168.25,175",7001));
		nodes.add(new HostAndPort("192.168.25,175",7002));
		nodes.add(new HostAndPort("192.168.25,175",7003));
		nodes.add(new HostAndPort("192.168.25,175",7004));
		nodes.add(new HostAndPort("192.168.25,175",7005));
		nodes.add(new HostAndPort("192.168.25,175",7006));
		JedisCluster jedisCluster=new JedisCluster(nodes);
		// 第二步：直接使用JedisCluster对象操作redis。在系统中单例存在。
		jedisCluster.set("hello", "100");
		String result = jedisCluster.get("hello");
		// 第三步：打印结果
		System.out.println(result);
		// 第四步：系统关闭前，关闭JedisCluster对象。
		jedisCluster.close();		
	}

}
