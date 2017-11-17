package edu.matc.movieCalendar.util;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ReminderJob implements org.quartz.Job {

    private final Logger log = Logger.getLogger(this.getClass());
    
    public ReminderJob() {
        
    }
    
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //log.info("ReminderJob is executing");
        System.out.println("ReminderJob is executing");
    }
}
