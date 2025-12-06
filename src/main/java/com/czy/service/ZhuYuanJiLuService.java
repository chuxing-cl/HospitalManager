package com.czy.service;

import com.czy.pojo.Hospitalization;
import com.github.pagehelper.PageInfo;

/**
 * @author 陈政缘
 * Date  2025/7/16 下午10:22
 */
public interface ZhuYuanJiLuService {

    PageInfo<Hospitalization> getZhuYuanJiLu(Integer pageNum, Integer pageSize);

    void delZY();
}
