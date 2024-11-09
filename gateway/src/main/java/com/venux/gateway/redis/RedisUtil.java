package com.venux.gateway.redis;

import lombok.extern.slf4j.Slf4j; // 导入日志记录的注解
import org.springframework.data.redis.core.RedisTemplate; // 导入 RedisTemplate 类，用于 Redis 操作
import org.springframework.stereotype.Component; // 导入 Spring 的 Component 注解

import javax.annotation.Resource; // 导入 Resource 注解，用于依赖注入
import java.util.Set; // 导入 Set 集合类
import java.util.concurrent.TimeUnit; // 导入时间单位枚举
import java.util.stream.Collectors; // 导入流处理中的 Collectors 工具类
import java.util.stream.Stream; // 导入流处理类 Stream

/**
 * RedisUtil工具类
 */
@Component // 声明为 Spring 组件，允许在应用中自动注入和使用
@Slf4j // 自动生成日志对象
public class RedisUtil {

    @Resource // 注入 RedisTemplate 对象，用于 Redis 操作
    private RedisTemplate redisTemplate;

    private static final String CACHE_KEY_SEPARATOR = "."; // 缓存 key 的分隔符

    /**
     * 构建缓存key
     */
    public String buildKey(String... strObjs) {
        // 使用 Stream 拼接字符串，以 CACHE_KEY_SEPARATOR 分隔
        return Stream.of(strObjs).collect(Collectors.joining(CACHE_KEY_SEPARATOR));
    }

    /**
     * 是否存在key
     */
    public boolean exist(String key) {
        // 检查 Redis 中是否存在指定 key
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除key
     */
    public boolean del(String key) {
        // 删除 Redis 中的指定 key，返回是否成功
        return redisTemplate.delete(key);
    }

    /**
     * set(不带过期)
     */
    public void set(String key, String value) {
        // 存储不带过期时间的 key-value 值
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * set(带过期)
     */
    public boolean setNx(String key, String value, Long time, TimeUnit timeUnit) {
        // 存储带过期时间的 key-value 值，如果 key 不存在则设置成功，返回是否成功
        return redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
    }

    /**
     * 获取string类型缓存
     */
    public String get(String key) {
        // 从 Redis 中获取指定 key 的值，返回为字符串类型
        return (String) redisTemplate.opsForValue().get(key);
    }

    public Boolean zAdd(String key, String value, Long score) {
        // 添加元素到有序集合中，带有 score（分数），返回是否成功
        return redisTemplate.opsForZSet().add(key, value, Double.valueOf(String.valueOf(score)));
    }

    public Long countZset(String key) {
        // 返回有序集合 key 的元素数量
        return redisTemplate.opsForZSet().size(key);
    }

    public Set<String> rangeZset(String key, long start, long end) {
        // 返回有序集合 key 中指定范围的元素集合
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public Long removeZset(String key, Object value) {
        // 从有序集合 key 中移除指定的元素
        return redisTemplate.opsForZSet().remove(key, value);
    }

    public void removeZsetList(String key, Set<String> value) {
        // 从有序集合 key 中移除多个指定的元素
        value.stream().forEach((val) -> redisTemplate.opsForZSet().remove(key, val));
    }

    public Double score(String key, Object value) {
        // 返回有序集合中指定元素的分数
        return redisTemplate.opsForZSet().score(key, value);
    }

    public Set<String> rangeByScore(String key, long start, long end) {
        // 获取有序集合中分数范围内的元素集合
        return redisTemplate.opsForZSet().rangeByScore(key, Double.valueOf(String.valueOf(start)), Double.valueOf(String.valueOf(end)));
    }

    public Object addScore(String key, Object obj, double score) {
        // 增加有序集合中指定元素的分数
        return redisTemplate.opsForZSet().incrementScore(key, obj, score);
    }

    public Object rank(String key, Object obj) {
        // 获取有序集合中指定元素的排名
        return redisTemplate.opsForZSet().rank(key, obj);
    }

}
