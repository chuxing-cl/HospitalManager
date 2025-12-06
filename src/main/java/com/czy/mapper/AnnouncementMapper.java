package com.czy.mapper;

import com.czy.pojo.Announcement;

import java.util.List;

public interface AnnouncementMapper {
    /**
     * 查询所有公告信息
     * @return
     */
    List<Announcement> selectAll();

    void addAnnouncement(Announcement announcement);

    Announcement selectAnnouncementById(Integer id);

    void updateAnnouncementById(Announcement announcement);

    void delAnnouncementById(Integer id);
}