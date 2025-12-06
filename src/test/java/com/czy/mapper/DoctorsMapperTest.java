package com.czy.mapper;

import com.czy.pojo.Doctors;
import com.czy.util.SqlSessionUtil;
import com.czy.vo.DoctorSearchVo;
import org.junit.Test;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/9 下午4:06
 */
public class DoctorsMapperTest {
    @Test
    public void selectDoctor() {
        // 获取mapper对象
        DoctorsMapper mapper = SqlSessionUtil.getMapper(DoctorsMapper.class);
        // 调用查询方法
        DoctorSearchVo searchVo = new DoctorSearchVo();
        searchVo.setDepartmentId(6);
        searchVo.setTitleId(2);
        List<Doctors> doctorsList = mapper.selectDoctorAll(searchVo);
        // 显示查询结果
        for (Doctors doctors : doctorsList) {
            System.out.println(doctors);
        }
    }
}