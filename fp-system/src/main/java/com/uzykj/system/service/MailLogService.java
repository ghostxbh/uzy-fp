package com.uzykj.system.service;

import com.uzykj.system.domain.MailLog;

import java.util.List;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MailLogService
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/4
 * @Version 1.0
 */
public interface MailLogService {
    /**
     * 添加日志
     *
     * @param mailLog
     * @return
     */
    public int addLog(MailLog mailLog);

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
     * @param batchId
     * @return
     */
    public int empty(Integer batchId);

    /**
     * 列表
     *
     * @param batchId
     * @return
     */
    public List<MailLog> logList(Integer batchId);
}
