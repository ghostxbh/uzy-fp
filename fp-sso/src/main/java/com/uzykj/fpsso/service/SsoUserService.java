package com.uzykj.fpsso.service;

import com.uzykj.fpsso.pojo.SsoUser;

/**
 * @author ghostxbh
 * @date 2020/5/21
 * @description 单点登录接口
 */
public interface SsoUserService {

    /**
     * 检查用户
     *
     * @param username 用户名
     * @return boolean
     */
    public boolean checkUser(String username);

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    public SsoUser getUser(String id);

    /**
     * 注册用户
     *
     * @param user
     * @return SsoUser
     */
    public SsoUser register(SsoUser user);

    /**
     * 登录
     *
     * @param name
     * @return
     */
    public SsoUser login(String name);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    public SsoUser setUser(SsoUser user);

    /**
     * 修改密码
     *
     * @param userId
     * @param newPwd
     */
    public void setPassword(String userId, String newPwd);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public void del(String id);
}
