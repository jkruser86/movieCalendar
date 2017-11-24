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
import java.time.LocalDate;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * This servlet handles the starting of the Quartz scheduler for movieCalendar
 *
 * @author Jamie Kruser
 */
@WebServlet(
        name = "quartzStart",
        urlPatterns = {"/quartzStart"}
)
public class QuartzStartServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * The doGet for the Quartz start servlet
     *
     * @param req the request for the servlet
     * @param resp the response for the servlet
     * @throws ServletException  handles the ServletException
     * @throws IOException handles the IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            startQuartz();
        } catch (Exception e) {
            log.error("Error starting Quartz", e);
        }

        req.setAttribute("date", LocalDate.now());
        String url = "/quartz-success.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(req, resp);
    }

    /**
     * Starts the Quartz schedule to run every hour
     *
     * @throws Exception handles generic exception
     */
    private void startQuartz() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        JobDetail quartzJob = newJob(ReminderJob.class).withIdentity("trigger1", "group1").build();

        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInHours(1).repeatForever())
                .build();

        scheduler.scheduleJob(quartzJob, trigger);
    }
}
