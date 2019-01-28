package com.yuanwj.mybatisdemo.archive.mapper;

import com.yuanwj.mybatisdemo.archive.model.ArchiveOrderBox;

import java.util.List;

/**
 * Created with Intellij IDEA
 * Author: xuziling
 * Date:  2019/1/28
 * Description:
 */
public interface ArchiveOrderBoxMapper {
    ArchiveOrderBox findById(Long id);

    List<ArchiveOrderBox> findAll();
}
