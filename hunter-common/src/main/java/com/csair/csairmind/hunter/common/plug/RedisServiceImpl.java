package com.csair.csairmind.hunter.common.plug;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangcheng
 * redis服务实现类
 */

@Service
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean hset(final String key, final String value, final String value1) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.hSet(serializer.serialize(key), serializer.serialize(value), serializer.serialize(value1));
                return true;
            }
        });
        return result;
    }

    @Override
    public boolean set(final String key, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
        });
        return result;
    }

    public String get(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    @Override
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public <T> boolean setList(String key, List<T> list) {
        String value = JSON.toJSONString(list);
        return set(key, value);
    }

    @Override
    public <T> List<T> getList(String key, Class<T> clz) {
        String json = get(key);
        if (json != null) {
            List<T> list = JSON.parseArray(json, clz);
            return list;
        }
        return null;
    }

    @Override
    public Long lpush(String key, String jsonStr) {
        return redisTemplate.opsForList().rightPush(key, jsonStr);
    }

    @Override
    public long rpush(final String key, Object obj) {
        final String value = JSON.toJSONString(obj);
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));
                return count;
            }
        });
        return result;
    }

    @Override
    public String lpop(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] res = connection.lPop(serializer.serialize(key));
                return serializer.deserialize(res);
            }
        });
        return result;
    }

    @Override
    public String hget(final String key, final String value) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return serializer.deserialize(connection.hGet(serializer.serialize(key), serializer.serialize(value)));
            }
        });
        return result;
    }

    @Override
    public void hdel(String key, String... value) {
        redisTemplate.opsForHash().delete(key,value);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Map<String, String> hgetAll(final String key) {
        return redisTemplate.execute(new RedisCallback<Map<String, String>>() {
            @Override
            public Map<String, String> doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                Map<byte[], byte[]> data = connection.hGetAll(serializer.serialize(key));
                Map<String, String> results = new HashMap<String, String>();
                for (byte[] key1 : data.keySet()) {
                    results.put(serializer.deserialize(key1), serializer.deserialize(data.get(key1)));
                }
                return results;
            }
        });
    }

    @Override
    public boolean hexists(final String key, final String mkey) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                return connection.hExists(serializer.serialize(key), serializer.serialize(mkey));
            }
        });
        return result;
    }

}