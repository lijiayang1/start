package com.omy.demo.redis;

import redis.clients.jedis.Jedis;

/**
 * @author: lijiayang
 * @date: 2019-01-23 16:54
 * @description:
 */

public class Client {
    private static Jedis client;

    public static Jedis getClient(){
        client = new Jedis("127.0.0.1");
        client.select(0);
        return client;
    }
}
