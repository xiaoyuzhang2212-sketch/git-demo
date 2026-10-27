package com.example.redis.util;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static final JedisPool JEDIS_POOL;

    static {
        JedisPoolConfig config = new JedisPoolConfig();

        // 最大连接数
        config.setMaxTotal(10);

        // 最大空闲连接数
        config.setMaxIdle(5);

        // 最小空闲连接数
        config.setMinIdle(1);

        // 获取连接时，连接池没有可用连接是否等待
        config.setBlockWhenExhausted(true);

        JEDIS_POOL = new JedisPool(
                config,
                "127.0.0.1",
                6379
        );
    }

    /**
     * 获取 Jedis 连接
     */
    public static Jedis getJedis() {
        return JEDIS_POOL.getResource();
    }

    /**
     * 关闭连接池
     */
    public static void closePool() {
        JEDIS_POOL.close();
    }


}
