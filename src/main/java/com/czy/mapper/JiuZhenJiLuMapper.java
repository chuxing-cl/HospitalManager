package com.czy.mapper;

import com.czy.pojo.JiuZhenJiLu;
import com.czy.vo.JiuZhenSearchVo;

import java.util.List;

/**
 * @author 陈政缘
 * Date  2025/7/16 下午8:50
 */
public interface JiuZhenJiLuMapper {

    List<JiuZhenJiLu> selectJiuZhenJiLu(JiuZhenSearchVo searchVo);

    void delById(Integer consultationId);
}
