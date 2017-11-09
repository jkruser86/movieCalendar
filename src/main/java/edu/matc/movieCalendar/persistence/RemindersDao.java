package edu.matc.movieCalendar.persistence;

import edu.matc.movieCalendar.entity.Reminders;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class RemindersDao {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Return a list of all reminders
     *
     * @return All reminders
     */
    public List<Reminders> getAllReminders() {
        List<Reminders> reminders = new ArrayList<Reminders>();
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            reminders = session.createCriteria(Reminders.class).list();
        } catch (HibernateException he) {
            log.error("Error retrieving all reminders", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return reminders;
    }

    public Reminders getReminder(String userName) {

    }
}
