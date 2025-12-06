package com.czy.service;

import com.czy.pojo.Announcement;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AnnouncementService {
    /**
     * 查询所有公告信息
     * @return
     */
    List<Announcement> selectAll();

    PageInfo<Announcement> selectAnnouncementsAll(Integer pageNum, Integer pageSize);

    void addAnnouncement(Announcement announcement);

    Announcement getAnnouncementById(Integer id);

    void updateAnnouncementById(Announcement announcement);

    void delAnnouncementById(Integer id);
}