package edu.matc.movieCalendar.entity;

import javax.persistence.*;

@Entity
@Table(name = "search")
public class Search {
    private String userName;
    private String searchTerm;

    @Id
    @Column(name = "user_name", nullable = false, length = 15)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "search_term", nullable = false, length = 200)
    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Search search = (Search) o;

        if (userName != null ? !userName.equals(search.userName) : search.userName != null) return false;
        if (searchTerm != null ? !searchTerm.equals(search.searchTerm) : search.searchTerm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (searchTerm != null ? searchTerm.hashCode() : 0);
        return result;
    }
}
