package com.uzykj.system.service;

import com.uzykj.system.domain.MailProperties;

import java.util.List;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MailPropertiesService
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/4
 * @Version 1.0
 */
public interface MailPropertiesService {
    /**
     * 添加配置
     *
     * @param mailProperties
     * @return
     */
    public int addProperties(MailProperties mailProperties);

    /**
     * 修改配置
     *
     * @param mailProperties
     * @return
     */
    public int updateProperties(MailProperties mailProperties);

    /**
     * 批量删除配置
     *
     * @param ids
     * @return
     */
    public int deletePropertiesByIds(Integer[] ids);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    public MailProperties selectOne(Integer id);

    /**
     * 获取用户下的配置
     *
     * @param userId
     * @return
     */
    public List<MailProperties> selectByUser(Integer userId);

    /**
     * 获取企业下的配置
     *
     * @param companyId
     * @return
     */
    public List<MailProperties> selectByCompany(Integer companyId);

    /**
     * 获取业务下的配置
     *
     * @param businessId
     * @return
     */
    public List<MailProperties> selectByBusiness(Integer businessId);
}
