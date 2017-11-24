package edu.matc.movieCalendar.persistence;

import edu.matc.movieCalendar.entity.*;
import org.apache.log4j.Logger;
import org.hibernate.*;


import java.util.*;

/**
 * This class does the database functionality for the user table
 *
 * @author Jamie Kruser
 */
public class UserDao {
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Return a list of all users
     *
     * @return All users
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            users = session.createCriteria(User.class).list();
        } catch (HibernateException he) {
            log.error("Error retrieving all users", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return users;
    }

    /**
     * Gets a user from the database
     *
     * @param userName The username to find
     * @return a User
     */
    public User getUser(String userName) {
        User user = null;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            user = (User) session.get(User.class, userName);
        } catch (HibernateException he) {
            log.error("Error retrieving username: " + userName, he);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return user;
    }

    /**
     * Adds a user to the database
     *
     * @param user The user to add
     */
    public void addUser(User user) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error saving user " + user, he);
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back save of user " + user, he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * This paragraph deletes the User from the table
     *
     * @param userName The user to delete
     */
    public void deleteUser(String userName) {
        Session session = null;
        Transaction transaction = null;
        User user = new User();

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, userName);
            if (user != null) {
                session.delete(user);
                transaction.commit();
            }
        } catch (HibernateException he) {
            log.error("Error deleting username: " + userName, he);
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back after failed delete", he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Updates the user table with the passed in user
     *
     * @param user the user to update
     */
    public void updateUser(User user) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (HibernateException he) {
            log.error("Error saving update of user " + user, he);
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException he2) {
                    log.error("Error rolling back after failed update", he2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
