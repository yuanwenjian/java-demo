package com.yuanwj.mybatisdemo.archive.service.impl;

import com.yuanwj.mybatisdemo.archive.mapper.ArchiveOrderBoxMapper;
import com.yuanwj.mybatisdemo.archive.model.ArchiveOrderBox;
import com.yuanwj.mybatisdemo.archive.service.ArchiveOrderBoxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/28
 * Description:
 */
@Service
@Transactional
public class ArchiveOrderBoxServiceImpl implements ArchiveOrderBoxService {

    @Resource
    private ArchiveOrderBoxMapper archiveOrderBoxMapper;

    @Override
    public List<ArchiveOrderBox> findAll() {
        return archiveOrderBoxMapper.findAll();
    }
}
