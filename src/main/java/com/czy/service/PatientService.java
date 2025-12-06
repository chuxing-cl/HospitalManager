package com.czy.service;

import com.czy.pojo.Patient;

/**
 * 患者（用户）业务接口
 * Date 2025/7/8
 */
public interface PatientService {

    /**
     * 根据登录名（如身份证号）和密码进行验证
     *
     * @param username 登录名
     * @param password 密码
     * @return 验证成功返回对应的 Patient 对象，否则返回 null
     */
    Patient login(String username, String password);

    /**
     * 根据用户 ID 查询完整用户信息
     *
     * @param patientId 患者 ID
     * @return 对应的 Patient 对象
     */
    Patient getById(int patientId);

    /**
     * 更新用户的个人信息
     *
     * @param patient 包含更新后的 Patient 对象
     * @return 更新成功返回 true，否则返回 false
     */
    boolean update(Patient patient);
    /**
     * 新增注册
     */
    boolean register(Patient patient);

}
