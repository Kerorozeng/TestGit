package com.pd;

import org.junit.Test;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

public class JedisTest02 {

    //redis测试分片
    @Test
    public void test02(){
        //2.创建分片的连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(500);
        poolConfig.setMaxIdle(20);
        //3.准备redis的分片
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("192.168.218.201", 7000));
        shards.add(new JedisShardInfo("192.168.218.201", 7001));
        shards.add(new JedisShardInfo("192.168.218.201", 7002));
        //1.创建分片的对象
        ShardedJedisPool jedisPool =
                new ShardedJedisPool(poolConfig, shards);
        //获取jedis对象
        ShardedJedis shardedJedis = jedisPool.getResource();
        //5.redis的存取值操作
        for (int i = 0; i < 9; i++) {
            shardedJedis.set("n"+i,"我是分片操作"+i);
        }
    }
}
