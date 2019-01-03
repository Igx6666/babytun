package com.gx.babytun;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 谷鑫 G x
 * @Classname Test
 * @Describe:
 * @date 2018/11/14 15:34
 */
public class Test {

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(20);
        jedisPoolConfig.setMaxWaitMillis(2000);
        JedisPool pool = new JedisPool(jedisPoolConfig, "47.104.92.87", 6379);



        Jedis jedis = pool.getResource();
        String result = jedis.set("c", "a", "nx", "px", 5000);
        System.out.println("result="+result);

        jedisPoolConfig.clone();
        pool.close();
        jedis.close();

    }
}
