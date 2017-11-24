package edu.matc.movieCalendar.persistence;

import edu.matc.movieCalendar.entity.Reminders;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the database functionality for the reminders table
 *
 * @author Jamie Kruser
 */
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

    /**public Reminders getReminder(String userName) {

    }*/

    /**
     * Adds a reminder to the database
     *
     * @param reminder The reminder to add
     */
    public void addReminder(Reminders reminder) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(reminder);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error saving reminder " + reminder, he);
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back save of reminder " + reminder, he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
