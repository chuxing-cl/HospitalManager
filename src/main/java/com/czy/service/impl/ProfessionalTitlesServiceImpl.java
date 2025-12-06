package com.czy.service.impl;

import com.czy.mapper.ProfessionalTitlesMapper;
import com.czy.pojo.Doctors;
import com.czy.pojo.ProfessionalTitles;
import com.czy.service.ProfessionalTitlesService;
import com.czy.util.SqlSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/9 下午2:35
 */
public class ProfessionalTitlesServiceImpl implements ProfessionalTitlesService {
    @Override
    public List<ProfessionalTitles> selectAll() {
        try {
            // 创建Mapper对象
            ProfessionalTitlesMapper mapper = SqlSessionUtil.getMapper(ProfessionalTitlesMapper.class);
            // 调用查询的方法
            List<ProfessionalTitles> titleList = mapper.selectAll();
            // 返回查询结果
            return titleList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public PageInfo<ProfessionalTitles> selectTitlesAll(Integer pageNum, Integer pageSize) {
        try {
            // 创建Mapper对象
            ProfessionalTitlesMapper mapper = SqlSessionUtil.getMapper(ProfessionalTitlesMapper.class);
            // 调用PageHelper.startPage()设置页码
            PageHelper.startPage(pageNum, pageSize);
            // 调用查询的方法
            List<ProfessionalTitles> titleList = mapper.selectAll();
            // 创建PageInfo对象
            PageInfo<ProfessionalTitles> pageInfo = new PageInfo<>(titleList);
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
    public void addTitle(ProfessionalTitles professionalTitles) {
        try {
            // 创建Mapper对象
            ProfessionalTitlesMapper mapper = SqlSessionUtil.getMapper(ProfessionalTitlesMapper.class);
            // 调用add方法
            mapper.addTitle(professionalTitles);
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
    public ProfessionalTitles getTitlesById(Integer titleId) {
        try {
            // 创建Mapper对象
            ProfessionalTitlesMapper mapper = SqlSessionUtil.getMapper(ProfessionalTitlesMapper.class);
            // 调用查询的方法
            ProfessionalTitles professionalTitle = mapper.selectTitlesById(titleId);
            // 返回professionalTitle对象
            return professionalTitle;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public void updateTitlesById(ProfessionalTitles professionalTitle) {
        try {
            // 创建Mapper对象
            ProfessionalTitlesMapper mapper = SqlSessionUtil.getMapper(ProfessionalTitlesMapper.class);
            // 调用add方法
            mapper.updateTitlesById(professionalTitle);
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
    public void delTitlesById(Integer id) {
        try {
            // 创建Mapper对象
            ProfessionalTitlesMapper mapper = SqlSessionUtil.getMapper(ProfessionalTitlesMapper.class);
            // 调用add方法
            mapper.delTitlesById(id);
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
    public ProfessionalTitles getTitlesByName(String titleName) {
        try {
            // 创建Mapper对象
            ProfessionalTitlesMapper mapper = SqlSessionUtil.getMapper(ProfessionalTitlesMapper.class);
            // 调用查询的方法
            ProfessionalTitles professionalTitle = mapper.selectTitlesByName(titleName);
            // 返回professionalTitle对象
            return professionalTitle;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            SqlSessionUtil.closeSession();
        }
    }

    @Override
    public List<Doctors> selectDoctorsByTitleId(Integer titleId) {
        try {
            ProfessionalTitlesMapper mapper = SqlSessionUtil.getMapper(ProfessionalTitlesMapper.class);
            return mapper.selectDoctorsByTitleId(titleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            SqlSessionUtil.closeSession();
        }
    }
}
