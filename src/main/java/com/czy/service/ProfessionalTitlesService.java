package com.czy.service;

import com.czy.pojo.Doctors;
import com.czy.pojo.ProfessionalTitles;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/9 下午2:25
 */
public interface ProfessionalTitlesService {
    /**
     * 查询所有职称信息
     * @return
     */
    List<ProfessionalTitles> selectAll();

    PageInfo<ProfessionalTitles> selectTitlesAll(Integer pageNum, Integer pageSize);

    void addTitle(ProfessionalTitles professionalTitles);

    ProfessionalTitles getTitlesById(Integer titleId);

    void updateTitlesById(ProfessionalTitles professionalTitle);

    void delTitlesById(Integer id);

    ProfessionalTitles getTitlesByName(String titleName);

    List<Doctors> selectDoctorsByTitleId(Integer titleId);
}
