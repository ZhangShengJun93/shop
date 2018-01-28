package cn.wolfcode.shop.cache;

import redis.clients.jedis.Jedis;

public interface RedisCallback {
    Object doWithRedis(Jedis var1);
}