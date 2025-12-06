package com.czy.mapper;

import com.czy.pojo.Doctors;
import com.czy.pojo.ProfessionalTitles;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/9 下午2:23
 */
public interface ProfessionalTitlesMapper {
    /**
     * 查询所有职称信息
     * @return
     */
    List<ProfessionalTitles> selectAll();

    void addTitle(ProfessionalTitles professionalTitles);

    ProfessionalTitles selectTitlesById(Integer id);

    void updateTitlesById(ProfessionalTitles professionalTitle);

    void delTitlesById(Integer id);

    ProfessionalTitles selectTitlesByName(String titleName);

    List<Doctors> selectDoctorsByTitleId(Integer titleId);
}
