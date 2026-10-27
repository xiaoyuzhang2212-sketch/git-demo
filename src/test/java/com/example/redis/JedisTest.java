package com.example.redis;

import com.example.redis.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class JedisTest {
    private Jedis jedis;


    @BeforeEach
    void setup(){
        jedis = JedisConnectionFactory.getJedis();
        //jedis.auth("1234");
        jedis.select(0);
    }

    @Test
    void testString(){
        String result = jedis.set("name", "虎哥");
        System.out.println("result=" + result);

        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testHash(){
        jedis.hset("user:1","name","Jack");

        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);
    }

    @AfterEach
    void tearDown(){
        if(jedis != null){
            jedis.close();
        }
    }
}
