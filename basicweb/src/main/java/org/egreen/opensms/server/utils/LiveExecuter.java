package org.egreen.opensms.server.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by pramoda-nf on 8/4/15.
 */

@Service
@EnableScheduling
public class LiveExecuter {





    /**
     * Send Email To User who Published
     * ad in some days before
     */
//    @Scheduled(cron = "0 00 00 ? * *")
//    public void cronTask() {
//
//        System.out.println();
//
//    }
//
//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//        System.out.println("The time is now "+new Date());
//    }
}
