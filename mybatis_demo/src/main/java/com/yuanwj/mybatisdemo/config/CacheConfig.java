package com.yuanwj.mybatisdemo.config;

import com.yuanwj.mybatisdemo.model.PhoneImei;
import com.yuanwj.mybatisdemo.service.PhoneImeiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(CacheConfig.class);

    @Resource
    private PhoneImeiService phoneImeiService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationEvent) {
        LOG.warn("event类型为{}",applicationEvent.getClass());
        HashOperations hashOperations = redisTemplate.opsForHash();
        List<PhoneImei> phoneImeiList = phoneImeiService.findAll();
        Jackson2HashMapper hashMapper = new Jackson2HashMapper(false);
        for (PhoneImei phoneImei : phoneImeiList) {
            Map map = hashMapper.toHash(phoneImei);
            hashOperations.putAll("yuanwj:phoneImei:" + phoneImei.getImeiId(), map);
        }
        LOG.warn("缓存构建保存成功+=========================");
    }
}
