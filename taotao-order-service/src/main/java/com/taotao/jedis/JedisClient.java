/******************************************
项目名称：taotao-content-service
文件：JedisClient.java
作者：fab
描述：JedisClient接口
创建日期：2018年6月26日 下午3:29:32
*******************************************/
package com.taotao.jedis;

/**
 * @author fab
 *
 */
public interface JedisClient {
	
	String set(String key, String value);
	String get(String key);
	Boolean exists(String key);
	Long expire(String key, int seconds);
	Long ttl(String key);
	Long incr(String key);
	Long hset(String key, String field, String value);
	String hget(String key, String field);
	Long hdel(String key, String... field);

}
