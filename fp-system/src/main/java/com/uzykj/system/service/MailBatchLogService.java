package com.uzykj.system.service;

import com.uzykj.system.domain.MailBatchLog;

import java.util.List;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MailBatchLogService
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/4
 * @Version 1.0
 */
public interface MailBatchLogService {
    /**
     * 添加批量日志
     *
     * @param mailBatchLog
     * @return
     */
    public int addBatchLog(MailBatchLog mailBatchLog);

    /**
     * 修改状态
     *
     * @param id
     * @param status
     * @return
     */
    public int updateStatus(Integer id, Integer status);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public int deleteLogByIds(Integer[] ids);

    /**
     * 清空
     *
     * @param userId
     * @return
     */
    public int empty(Integer userId);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    public MailBatchLog selectOne(Integer id);

    /**
     * 待发送列表
     *
     * @return
     */
    public List<MailBatchLog> tobeList();


    /**
     * 获取用户下的配置
     *
     *
     * @param userId
     * @return
     */
    public List<MailBatchLog> selectByUser(Integer userId);

    /**
     * 获取企业下的批量日志
     *
     * @param companyId
     * @return
     */
    public List<MailBatchLog> selectByCompany(Integer companyId);
}
