package com.uzykj.common.core.task;

import com.uzykj.common.core.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName SchedulingTask
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/8
 * @Version 1.0
 */
@Slf4j
//@Component
public class SchedulingTask {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Value("${templateExport.Path}")
    private String attachPath;

    @Scheduled(cron = "${time.cron}")
    @Async("taskScheduler")
    public void checkClenFile() {
        log.info("======定时清理文件任务开始于：{}", sdf.format(new Date()));
        String filePath = attachPath;
        // 删除多少天之前文件
        int delCount = FileUtil.moveFileToReady(filePath, 7);
        if (delCount > 0) {
            log.info("======本次从：{}" + filePath + "下清理" + delCount + "份文件");
        } else {
            log.info("======暂时没有要清理的文件");
        }
        log.info("======定时清理文件任务结束于：{}", sdf.format(new Date()));
    }
}
