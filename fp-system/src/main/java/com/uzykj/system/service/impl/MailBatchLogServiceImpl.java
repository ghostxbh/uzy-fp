package com.uzykj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uzykj.system.constant.fields.MailBacthLogFields;
import com.uzykj.system.domain.MailBatchLog;
import com.uzykj.system.enums.MaillSendStatus;
import com.uzykj.system.mapper.MailBatchLogMapper;
import com.uzykj.system.service.MailBatchLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MailBatchLogServiceImpl
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
@Service
public class MailBatchLogServiceImpl implements MailBatchLogService {
    @Autowired
    private MailBatchLogMapper mailBatchLogMapper;

    @Override
    public int addBatchLog(MailBatchLog mailBatchLog) {
        mailBatchLog.setCreateTime(new Date());
        mailBatchLog.setUpdateTime(new Date());
        return mailBatchLogMapper.insert(mailBatchLog);
    }

    @Override
    public int updateStatus(Integer id, Integer status) {
        MailBatchLog build = MailBatchLog.builder()
                .id(id)
                .status(status)
                .updateTime(new Date())
                .build();
        return mailBatchLogMapper.updateById(build);
    }

    @Override
    public int deleteLogByIds(Integer[] ids) {
        return mailBatchLogMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int empty(Integer userId) {
        return mailBatchLogMapper.delete(new QueryWrapper<MailBatchLog>().eq(MailBacthLogFields.USER_ID, userId));
    }

    @Override
    public MailBatchLog selectOne(Integer id) {
        return mailBatchLogMapper.selectOne(new QueryWrapper<MailBatchLog>().eq(MailBacthLogFields.ID, id));
    }

    @Override
    public List<MailBatchLog> tobeList() {
        return mailBatchLogMapper.selectList(new QueryWrapper<MailBatchLog>().eq(MailBacthLogFields.STATUS, MaillSendStatus.TOBE.getCode()));
    }

    @Override
    public List<MailBatchLog> selectByUser(Integer userId) {
        return mailBatchLogMapper.selectList(new QueryWrapper<MailBatchLog>().eq(MailBacthLogFields.USER_ID, userId));
    }

    @Override
    public List<MailBatchLog> selectByCompany(Integer companyId) {
        return mailBatchLogMapper.selectList(new QueryWrapper<MailBatchLog>().eq(MailBacthLogFields.COMPANY_ID, companyId));
    }
}
