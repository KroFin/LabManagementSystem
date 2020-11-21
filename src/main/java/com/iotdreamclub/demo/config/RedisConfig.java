package com.iotdreamclub.demo.config;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisConfig {
    private static StringRedisTemplate redisTemplate;
    static {
        // 创建StringRedisTemplate对象
        redisTemplate = new StringRedisTemplate();
        //  创建连接工厂并进行相关参数设置
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setUsePool(false);  // 不实用池技术
        factory.setClientName("medical_db");  // 连接的Redis数据库名称
        factory.setHostName("123.57.252.81");     // 连接的Redis数据库主机名，默认本机
        factory.setPassword("iot123456.");              // 连接的Redis数据库密码
        factory.setPort(6379);                // 连接的Redis数据库端口号
        // 完成工厂设置
        factory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(factory);
        // 完成Redis模板工具类设置
        redisTemplate.afterPropertiesSet();
    }
    // 对外提供方法获取Redis模板工具类
    public static RedisTemplate redisTemplate(){
        return redisTemplate;
    }

    public static StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public static void setRedisTemplate(StringRedisTemplate redisTemplate) {
        RedisConfig.redisTemplate = redisTemplate;
    }
}
