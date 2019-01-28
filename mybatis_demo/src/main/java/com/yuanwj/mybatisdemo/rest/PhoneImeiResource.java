package com.yuanwj.mybatisdemo.rest;

import com.yuanwj.mybatisdemo.archive.model.ArchiveOrderBox;
import com.yuanwj.mybatisdemo.archive.service.ArchiveOrderBoxService;
import com.yuanwj.mybatisdemo.model.PhoneImei;
import com.yuanwj.mybatisdemo.service.PhoneImeiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/25
 * Description:
 */
@RestController
@RequestMapping("/api/v1/")
public class PhoneImeiResource {

    @Resource
    private PhoneImeiService phoneImeiService;

    @Resource
    private ArchiveOrderBoxService archiveOrderBoxService;

    @RequestMapping(value = "phoneImei/findAll",method = RequestMethod.GET)
    public List<PhoneImei> findAll() {
        List<PhoneImei> phoneImeis = phoneImeiService.findAll();
        return phoneImeis;
    }

    @RequestMapping(value = "phoneImei/findById",method = RequestMethod.GET)
    public PhoneImei findById(Long id) {
        PhoneImei phoneImei = phoneImeiService.findById(id);
        return phoneImei;
    }

    @RequestMapping(value = "orderBox/findAll",method = RequestMethod.GET)
    public List<ArchiveOrderBox> findAllBox() {
        return archiveOrderBoxService.findAll();
    }
}
