package edu.matc.movieCalendar.persistence;

import edu.matc.movieCalendar.entity.Reminders;
import edu.matc.movieCalendar.entity.User;
import edu.matc.movieCalendar.entity.UserRoles;
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

        UserRoles userRoles1 = new UserRoles();
        userRoles1.setRoleName("admin");
        userRoles1.setUser(insertedUser1);

        Reminders reminders1 = new Reminders();
        reminders1.setMovieId(12345);
        reminders1.setTheaterDaysBefore(1);
        reminders1.setDigitalDaysBefore(5);
        reminders1.setPhysicalDaysBefore(-1);
        reminders1.setUser(insertedUser1);

        insertedUser1.getUserRoles().add(userRoles1);
        insertedUser1.getReminders().add(reminders1);


        User insertedUser2 = new User();
        insertedUser2.setUserName("testuser2");
        insertedUser2.setUserPass("password2");
        insertedUser2.setUserEmail("test2@test.edu");

        UserRoles userRoles2 = new UserRoles();
        userRoles2.setRoleName("user");
        userRoles2.setUser(insertedUser2);

        Reminders reminders2 = new Reminders();
        reminders2.setMovieId(00001);
        reminders2.setTheaterDaysBefore(-1);
        reminders2.setDigitalDaysBefore(-1);
        reminders2.setPhysicalDaysBefore(10);
        reminders2.setUser(insertedUser2);

        Reminders reminders21 = new Reminders();

        reminders21.setMovieId(98765);
        reminders21.setTheaterDaysBefore(10);
        reminders21.setDigitalDaysBefore(-1);
        reminders21.setPhysicalDaysBefore(-1);
        reminders21.setUser(insertedUser2);

        insertedUser2.getUserRoles().add(userRoles2);
        insertedUser2.getReminders().add(reminders2);
        insertedUser2.getReminders().add(reminders21);


        User insertedUser3 = new User();
        insertedUser3.setUserName("testuser3");
        insertedUser3.setUserPass("password3");
        insertedUser3.setUserEmail("test3@test.edu");

        UserRoles userRoles3 = new UserRoles();
        userRoles3.setRoleName("user");
        userRoles3.setUser(insertedUser3);

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

        UserRoles userRoles = new UserRoles();
        userRoles.setRoleName("user");
        userRoles.setUser(expectedUser);

        Reminders reminders = new Reminders();
        reminders.setMovieId(00001);
        reminders.setTheaterDaysBefore(-1);
        reminders.setDigitalDaysBefore(-1);
        reminders.setPhysicalDaysBefore(10);
        reminders.setUser(expectedUser);

        Reminders reminders2 = new Reminders();

        reminders2.setMovieId(98765);
        reminders2.setTheaterDaysBefore(10);
        reminders2.setDigitalDaysBefore(-1);
        reminders2.setPhysicalDaysBefore(-1);
        reminders2.setUser(expectedUser);

        expectedUser.getUserRoles().add(userRoles);
        expectedUser.getReminders().add(reminders);
        expectedUser.getReminders().add(reminders2);

        User actualUser = dao.getUser("testuser2");
        assertTrue(expectedUser.equals(actualUser));
    }

    @Test
    public void addUserTest() throws Exception {
        User insertedUser = new User();
        insertedUser.setUserName("testuser4");
        insertedUser.setUserPass("password4");
        insertedUser.setUserEmail("test4@test.edu");

        UserRoles userRoles = new UserRoles();
        userRoles.setRoleName("user");
        userRoles.setUser(insertedUser);

        Reminders reminders = new Reminders();
        reminders.setMovieId(98765);
        reminders.setTheaterDaysBefore(10);
        reminders.setDigitalDaysBefore(10);
        reminders.setPhysicalDaysBefore(10);
        reminders.setUser(insertedUser);

        insertedUser.getUserRoles().add(userRoles);
        insertedUser.getReminders().add(reminders);

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