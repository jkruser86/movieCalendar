package edu.matc.movieCalendar.persistence;

import edu.matc.movieCalendar.entity.UserRoles;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the database functionality for the userRoles table
 *
 * @author Jamie Kruser
 */
public class UserRolesDao {
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Return a list of all user roles
     *
     * @return All user roles
     */
    public List<UserRoles> getAllUserRoles() {
        List<UserRoles> userRoles = new ArrayList<UserRoles>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        userRoles = session.createCriteria(UserRoles.class).list();
        session.close();
        return userRoles;
    }

    /**
     * Return a user role
     * @param userName The username for the role
     * @return The UserRoles for the user
     */
    public UserRoles getUserRoles(String userName) {
        UserRoles userRoles = null;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            userRoles = (UserRoles) session.get(UserRoles.class, userName);
        } catch (HibernateException he) {
            log.error("Error retrieving username: " + userName, he);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return userRoles;
    }

    /**
     * Add a user roles
     *
     * @param userRoles The user roles to add
     */
    public void addUserRoles (UserRoles userRoles) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(userRoles);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error saving user " + userRoles, he);
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back save of user roles " + userRoles, he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}