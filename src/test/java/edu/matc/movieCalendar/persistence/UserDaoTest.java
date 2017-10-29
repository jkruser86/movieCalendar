package edu.matc.movieCalendar.persistence;

import edu.matc.movieCalendar.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {

    UserDao dao;

    @Before
    public void setup() {
        dao = new UserDao();

        dao.deleteUser("testuser1");
        dao.deleteUser("testuser2");
        dao.deleteUser("testuser3");
        dao.deleteUser("testuser4");

        User insertedUser1 = new User();
        insertedUser1.setUserName("testuser1");
        insertedUser1.setUserPass("password1");
        insertedUser1.setUserEmail("test1@test.edu");

        User insertedUser2 = new User();
        insertedUser2.setUserName("testuser2");
        insertedUser2.setUserPass("password2");
        insertedUser2.setUserEmail("test2@test.edu");

        User insertedUser3 = new User();
        insertedUser3.setUserName("testuser3");
        insertedUser3.setUserPass("password3");
        insertedUser3.setUserEmail("test3@test.edu");

        dao.addUser(insertedUser1);
        dao.addUser(insertedUser2);
        dao.addUser(insertedUser3);
    }

    @Test
    public void getAllUsersTest() throws Exception {
        List<User> users = dao.getAllUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    public void getUserTest() throws Exception {
        User expectedUser = new User();
        expectedUser.setUserName("testuser2");
        expectedUser.setUserPass("password2");
        expectedUser.setUserEmail("test2@test.edu");

        User actualUser = dao.getUser("testuser2");
        assertTrue(expectedUser.equals(actualUser));
    }

    @Test
    public void addUserTest() throws Exception {
        User insertedUser = new User();
        insertedUser.setUserName("testuser4");
        insertedUser.setUserPass("password4");
        insertedUser.setUserEmail("test4@test.edu");

        dao.addUser(insertedUser);

        User returnedUser = dao.getUser("testuser4");
        assertTrue(insertedUser.equals(returnedUser));
    }

    @Test
    public void deleteUserTest() {
        List<User> beforeUsers = dao.getAllUsers();

        dao.deleteUser("testuser2");
        List<User> afterUsers = dao.getAllUsers();

        assertTrue(beforeUsers.size() > afterUsers.size());
    }

    @Test
    public void updateUserTest() {
        User beforeUser = dao.getUser("testuser1");
        User updatedUser = new User();

        updatedUser.setUserName("testuser1");
        updatedUser.setUserPass("updatepass1");
        updatedUser.setUserEmail("updatetest1@test.edu");

        dao.updateUser(updatedUser);

        User afterUser = dao.getUser("testuser1");

        assertTrue(beforeUser.getUserEmail() != afterUser.getUserEmail());
    }

}