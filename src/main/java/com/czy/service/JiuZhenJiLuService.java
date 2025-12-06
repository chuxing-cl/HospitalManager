package com.czy.service;

import com.czy.pojo.JiuZhenJiLu;
import com.czy.vo.DoctorSearchVo;
import com.czy.vo.JiuZhenSearchVo;
import com.github.pagehelper.PageInfo;

/**
 * @author 陈政缘
 * Date  2025/7/16 下午8:23
 */
public interface JiuZhenJiLuService {

    PageInfo<JiuZhenJiLu> getJiuZhenJiLu(JiuZhenSearchVo searchVo, Integer pageNum, Integer pageSize);

    void delById(Integer consultationId);
}
