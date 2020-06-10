package com.uzykj.fpsso.service.impl;

import com.uzykj.fpcommon.utils.crypto.Md5;
import com.uzykj.fpsso.mapper.SsoUserMapper;
import com.uzykj.fpsso.pojo.SsoUser;
import com.uzykj.fpsso.pojo.SsoUserExample;
import com.uzykj.fpsso.service.SsoUserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author ghostxbh
 * @date 2020/5/21
 * @description impl
 */
@Service
public class SsoUserServiceImpl implements SsoUserService {
    private static final String defualtOrg = "00000000";
    private static final String defualtCreater = "00000000-0000-0000-0000-000000000000";

    @Value("${md5.secret}")
    private String secret;
    @Value("${md5.version}")
    private String version;

    @Autowired
    private SsoUserMapper userMapper;

    @Override
    public boolean checkUser(String username) {
        return this.login(username) != null;
    }

    @Override
    public SsoUser getUser(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public SsoUser register(SsoUser user) {
        user.setId(UUID.randomUUID().toString());
        String md5Pwd = Md5.md5(user.getPassword());
        // 加密盐
        String setPwd = md5Pwd + secret + version;
        user.setPassword(Md5.md5(setPwd));

        if (user.getOrgId() == null) user.setOrgId(defualtOrg);
        user.setCreater(defualtCreater);
        user.setCreateTime(new Date());
        userMapper.insert(user);
        user.setPassword(null);
        return user;
    }

    @Override
    public SsoUser login(String name) {
        SsoUserExample example = new SsoUserExample();
        example.createCriteria().andNameEqualTo(name);
        RowBounds rowBounds = new RowBounds(0, 1);
        List<SsoUser> ssoUsers = userMapper.selectByExampleWithRowbounds(example, rowBounds);
        return ssoUsers.size() > 0 ? ssoUsers.get(0) : null;
    }

    @Override
    public SsoUser setUser(SsoUser user) {
        userMapper.updateByPrimaryKeySelective(user);
        return user;
    }

    @Override
    public void setPassword(String userId, String newPwd) {
        SsoUser filter = new SsoUser();
        filter.setId(userId);
        SsoUserExample example = new SsoUserExample();
        example.createCriteria().andPasswordEqualTo(newPwd);
        userMapper.updateByExample(filter, example);
    }

    @Override
    public void del(String id) {
        // TODO 删除用户关联信息
        userMapper.deleteByPrimaryKey(id);
    }
}
