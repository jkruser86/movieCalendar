package edu.matc.movieCalendar.persistence;

import edu.matc.movieCalendar.entity.Search;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class SearchDao {
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * Return a list of all searches
     *
     * @return All searches
     */
    public List<Search> getAllSearches() {
        List<Search> searches = new ArrayList<Search>();
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            searches = session.createCriteria(Search.class).list();
        } catch (HibernateException he) {
            log.error("Error retrieving all searches", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return searches;
    }

    /**
     public Search getSearch(String userName, String searchText) {
     Search search = null;
     Session session = null;

     try {
     session = SessionFactoryProvider.getSessionFactory().openSession();
     search = (Search)
     }
     } */
}
