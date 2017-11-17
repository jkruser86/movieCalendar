package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.util.ReminderJob;
import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@WebServlet(
        name = "quartzStart",
        urlPatterns = {"/quartzStart"}
)
public class QuartzStartServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            startQuartz();
        } catch (Exception e) {
            log.error("Error starting Quartz", e);
        }

        String url = "/quartz-success.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(req, resp);
    }

    protected void startQuartz() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        JobDetail quartzJob = newJob(ReminderJob.class).withIdentity("trigger1", "group1").build();

        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever())
                .build();

        scheduler.scheduleJob(quartzJob, trigger);
    }
}
