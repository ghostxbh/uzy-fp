package com.uzykj.common.core.task;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName ThreadPoolTaskScheduler
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/8
 * @Version 1.0
 */
//@Component
public class ThreadPoolTask {
    @Bean("taskScheduler")
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        return threadPoolTaskScheduler;
    }
}
