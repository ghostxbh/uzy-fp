package com.uzykj.common.core.utils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName FileUtil
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/8
 * @Version 1.0
 */
public class FileUtil {
    public static Integer moveFileToReady(String fromDir,int howDays ) {
        File srcDir = new File(fromDir);
        if (!srcDir.exists()) {
            return 0;
        }
        File[] files = srcDir.listFiles();
        if (files == null || files.length <= 0) {
            return 0;
        }
        // 删除文件总数
        int delTotal = 0;
        Date today = new Date();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                try {
                    File ff = files[i];
                    long time = ff.lastModified();
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(time);
                    Date lastModified = cal.getTime();
                    //(int)(today.getTime() - lastModified.getTime())/86400000;
                    long days = getDistDates(today, lastModified);
                    // 删除多少天前之前文件
                    if (days >= howDays) {
                        files[i].delete();
                        delTotal++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return delTotal;
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     */
    public static long getDistDates(Date startDate, Date endDate) {
        long totalDate = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        long timestart = calendar.getTimeInMillis();
        calendar.setTime(endDate);
        long timeend = calendar.getTimeInMillis();
        totalDate = Math.abs((timeend - timestart)) / (1000 * 60 * 60 * 24);
        return totalDate;
    }
}
