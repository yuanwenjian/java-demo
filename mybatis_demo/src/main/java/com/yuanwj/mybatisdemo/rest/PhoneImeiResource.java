package com.yuanwj.mybatisdemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuanwj.mybatisdemo.archive.model.ArchiveOrderBox;
import com.yuanwj.mybatisdemo.archive.service.ArchiveOrderBoxService;
import com.yuanwj.mybatisdemo.model.PhoneImei;
import com.yuanwj.mybatisdemo.service.PhoneImeiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.data.redis.hash.DecoratingStringHashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/25
 * Description:
 */
@RestController
@RequestMapping("/api/v1/")
public class PhoneImeiResource {

    @Value("${server.port}")
    private String port;

    @Resource
    private PhoneImeiService phoneImeiService;

    @Resource
    private ArchiveOrderBoxService archiveOrderBoxService;

    @Resource
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "phoneImei/findAll",method = RequestMethod.GET)
    public List<PhoneImei> findAll() {


        List<PhoneImei> phoneImeis = phoneImeiService.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        HashOperations hashOperations = redisTemplate.opsForHash();
        ListOperations<String,PhoneImei> listOperations = redisTemplate.opsForList();

        redisTemplate.delete("phoneImeis");
//        listOperations.leftPushAll("phoneImeis", phoneImeis);
        Jackson2HashMapper hashMapper = new Jackson2HashMapper(false);
        for (PhoneImei phoneImei : phoneImeis) {
//            Map entries = hashOperations.entries("yuanwj:phoneImei:" + phoneImei.getImeiId());
//            PhoneImei imei = (PhoneImei) hashMapper.fromHash(entries);
//            System.out.println(imei);
//            redisTemplate.delete("yuanwj:phoneImei:" + phoneImei.getImeiId());
//            Map<String,Object> map = hashMapper.toHash(phoneImei);
//            hashOperations.putAll("yuanwj:phoneImei:"+phoneImei.getImeiId(),map);
        }

        return phoneImeis;
//        return port;
    }

    @RequestMapping(value = "phoneImei/findById",method = RequestMethod.GET)
    public PhoneImei findById(Long id) {

//        PhoneImei phoneImei = phoneImeiService.findById(id);
        String redisKey = "yuanwj:phoneImei:" + id;
        BoundHashOperations hashOperations = redisTemplate.boundHashOps(redisKey);
        Map entries = hashOperations.entries();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Jackson2HashMapper hashMapper = new Jackson2HashMapper(false);
        PhoneImei imei = (PhoneImei) hashMapper.fromHash(entries);
        return imei;
//        return phoneImei;
    }

    @RequestMapping(value = "orderBox/findAll",method = RequestMethod.GET)
    public List<ArchiveOrderBox> findAllBox() {
        return archiveOrderBoxService.findAll();
    }
}
