package com.czy.service.impl;

import com.czy.mapper.AnnouncementMapper;
import com.czy.pojo.Announcement;
import com.czy.service.AnnouncementService;
import com.czy.util.SqlSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class AnnouncementServiceImpl implements AnnouncementService {
    @Override
    public List<Announcement> selectAll() {
        try {
            // 创建Mapper对象
            AnnouncementMapper mapper = SqlSessionUtil.getMapper(AnnouncementMapper.class);
            // 调用查询的方法
            List<Announcement> announcementList = mapper.selectAll();
            // 返回查询结果
            return announcementList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public PageInfo<Announcement> selectAnnouncementsAll(Integer pageNum, Integer pageSize) {
        try {
            // 创建Mapper对象
            AnnouncementMapper mapper = SqlSessionUtil.getMapper(AnnouncementMapper.class);
            // 调用PageHelper.startPage()设置页码
            PageHelper.startPage(pageNum, pageSize);
            // 调用查询的方法
            List<Announcement> announcementList = mapper.selectAll();
            // 创建PageInfo对象
            PageInfo<Announcement> pageInfo = new PageInfo<>(announcementList);
            // 返回PageInfo对象
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public void addAnnouncement(Announcement announcement) {
        try {
            // 创建Mapper对象
            AnnouncementMapper mapper = SqlSessionUtil.getMapper(AnnouncementMapper.class);
            // 调用add方法
            mapper.addAnnouncement(announcement);
            // 提交事务
            SqlSessionUtil.commit();
        } catch (Exception e) {
            SqlSessionUtil.rollback();
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public Announcement getAnnouncementById(Integer id) {
        try {
            // 创建Mapper对象
            AnnouncementMapper mapper = SqlSessionUtil.getMapper(AnnouncementMapper.class);
            // 调用查询的方法
            Announcement announcement = mapper.selectAnnouncementById(id);
            // 返回announcement对象
            return announcement;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public void updateAnnouncementById(Announcement announcement) {
        try {
            // 创建Mapper对象
            AnnouncementMapper mapper = SqlSessionUtil.getMapper(AnnouncementMapper.class);
            // 调用add方法
            mapper.updateAnnouncementById(announcement);
            // 提交事务
            SqlSessionUtil.commit();
        } catch (Exception e) {
            SqlSessionUtil.rollback();
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public void delAnnouncementById(Integer id) {
        try {
            // 创建Mapper对象
            AnnouncementMapper mapper = SqlSessionUtil.getMapper(AnnouncementMapper.class);
            // 调用add方法
            mapper.delAnnouncementById(id);
            // 提交事务
            SqlSessionUtil.commit();
        } catch (Exception e) {
            SqlSessionUtil.rollback();
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }
}