package cn.wolfcode.shop.util;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class MybatisRedisCache implements Cache{

    private String id;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    RedisTemplate<Object,Object> redisTemplate = ApplicationContextHolder.getBean("redisTemplate");

    public MybatisRedisCache(String id){
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        HashMap<Object,Object> map = new HashMap();
        map.put(key,value);
        redisTemplate.opsForValue().set(id,map);
    }

    @Override
    public Object getObject(Object key) {
        Map<Object,Object> map = (Map)redisTemplate.opsForValue().get(id);
        if(map == null){
            return null;
        }else{
            boolean b = map.containsKey(key.toString());
            if(map.containsKey(key.toString())){
                
                return  map.get(key);
            }else {
                return null;
            }
        }
    }

    @Override
    public Object removeObject(Object key) {
        Map<Object,Object> map = (Map)redisTemplate.opsForValue().get(id);
        if(map == null){
            return null;
        }else{
            if(map.containsKey(key.toString())){
                return  map.remove(key.toString());
            }else {
                return null;
            }
        }
    }

    @Override
    public void clear() {
        redisTemplate.delete(id);
    }

    @Override
    public int getSize() {
        Map<Object,Object> map = (Map)redisTemplate.opsForValue().get(id);
        return map == null ? 0 : map.size();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
