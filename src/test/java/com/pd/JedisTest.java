package com.pd;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    @Test    //完成单实例链接,修改代码中ip地址
    public void jedis(){
        Jedis jedis = new Jedis("192.168.218.201", 7000);
        //jedis.auth("123456");
        jedis.set("name", "tony");	//调用redis命令set
        String s = jedis.get("name");
        System.out.println(s);
        jedis.close();
    }
}
