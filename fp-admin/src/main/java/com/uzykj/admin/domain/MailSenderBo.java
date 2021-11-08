package com.uzykj.admin.domain;

import com.google.common.collect.Maps;
import com.uzykj.common.mail.domain.MailSender;
import com.uzykj.system.domain.MailBatchLog;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MailSenderBo
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
public class MailSenderBo extends MailSender {
    public static MailSender toSender(MailBatchLog mailBatchLog, String mailKey, String fromAddress, String toAddress) {
        return MailSender.builder()
                .mailKey(mailKey)
                .batchId(mailBatchLog.getId())
                .userId(mailBatchLog.getUserId())
                .companyId(mailBatchLog.getCompanyId())
                .subject(mailBatchLog.getSubject())
                .content(mailBatchLog.getContent())
                .fromAddress(fromAddress)
                .toAddress(toAddress)
                .files(transferFiles(mailBatchLog.getFiles()))
                .sign(mailBatchLog.getSign())
                .remark(mailBatchLog.getRemark())
                .build();
    }

    public static Map<String, String> transferFiles(String files) {
        HashMap<String, String> fileMap = Maps.newHashMap();
        if (files.contains(";")) {
            for (String file : files.split(";")) {
                String[] fileSplit = file.split("@");
                fileMap.put(fileSplit[0], fileSplit[1]);
            }
        } else {
            String[] fileSplit = files.split("@");
            fileMap.put(fileSplit[0], fileSplit[1]);
        }
        return fileMap;
    }
}
