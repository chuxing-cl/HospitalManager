package com.czy.mapper;

import com.czy.pojo.Hospitalization;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/16 下午10:29
 */
public interface ZhuYuanJiLuMapper {

    List<Hospitalization> selectZhuYuanJiLu();

    void delZY();
}
