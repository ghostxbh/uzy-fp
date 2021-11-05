package com.uzykj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uzykj.system.constant.fields.MailPropertiesFields;
import com.uzykj.system.domain.MailProperties;
import com.uzykj.system.enums.MaillEnable;
import com.uzykj.system.mapper.MailPropertiesMapper;
import com.uzykj.system.service.MailPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MailPropertiesServiceImpl
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
@Service
public class MailPropertiesServiceImpl implements MailPropertiesService {
    @Autowired
    private MailPropertiesMapper mailPropertiesMapper;

    @Override
    public int addProperties(MailProperties mailProperties) {
        mailProperties.setIsEnable(MaillEnable.ENABLE.getCode());
        mailProperties.setCreateTime(new Date());
        mailProperties.setUpdateTime(new Date());
        return mailPropertiesMapper.insert(mailProperties);
    }

    @Override
    public int updateProperties(MailProperties mailProperties) {
        mailProperties.setUpdateTime(new Date());
        return mailPropertiesMapper.updateById(mailProperties);
    }

    @Override
    public int deletePropertiesByIds(Integer[] ids) {
        return mailPropertiesMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public MailProperties selectOne(Integer id) {
        return mailPropertiesMapper.selectOne(new QueryWrapper<MailProperties>().eq(MailPropertiesFields.ID, id));
    }

    @Override
    public MailProperties selectOneByKey(String key) {
        return mailPropertiesMapper.selectOne(new QueryWrapper<MailProperties>().eq(MailPropertiesFields.KEY, key));
    }

    @Override
    public List<MailProperties> selectByUser(Integer userId) {
        return mailPropertiesMapper.selectList(new QueryWrapper<MailProperties>().eq(MailPropertiesFields.USER_ID, userId));
    }

    @Override
    public List<MailProperties> selectByCompany(Integer companyId) {
        return mailPropertiesMapper.selectList(new QueryWrapper<MailProperties>().eq(MailPropertiesFields.COMPANY_ID, companyId));
    }

    @Override
    public List<MailProperties> selectByBusiness(Integer businessId) {
        return mailPropertiesMapper.selectList(new QueryWrapper<MailProperties>().eq(MailPropertiesFields.BUSINESS_ID, businessId));
    }
}
