package com.uzykj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uzykj.system.constant.fields.MailLogFields;
import com.uzykj.system.domain.MailLog;
import com.uzykj.system.mapper.MailLogMapper;
import com.uzykj.system.service.MailLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MailLogServiceImpl
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
@Service
public class MailLogServiceImpl implements MailLogService {
    @Autowired
    private MailLogMapper mailLogMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addLog(MailLog mailLog) {
        mailLog.setCreateTime(new Date());
        mailLog.setUpdateTime(new Date());
        return mailLogMapper.insert(mailLog);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateStatus(Integer id, Integer status) {
        MailLog build = MailLog.builder()
                .id(id)
                .status(status)
                .updateTime(new Date())
                .build();
        return mailLogMapper.updateById(build);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteLogByIds(Integer[] ids) {
        return mailLogMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int empty(Integer batchId) {
        return mailLogMapper.delete(new QueryWrapper<MailLog>().eq(MailLogFields.BATCH_ID, batchId));
    }

    @Override
    public List<MailLog> logList(Integer batchId) {
        return mailLogMapper.selectList(new QueryWrapper<MailLog>().eq(MailLogFields.BATCH_ID, batchId));
    }
}
