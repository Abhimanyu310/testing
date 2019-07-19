package com.unit.testing.db;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import com.unit.testing.models.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(MockitoExtension.class)
class UserDaoTest {


    private static User user;
    private static UserDao dao;
    @Mock
    private static UserDao dao1;


    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        dao = new UserDao();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        user = new User();
        user.setUsername("ana20");
        user.setId(12);
    }

    @AfterEach
    void tearDown() throws Exception {
        user = null;
    }

    @Test
    void testGetUserById_whenUserIdNull_returnsUserNotFound() {
        Map<String, Object> results = dao.getUserById(null);
        String status = (String) results.get("status");
        assertEquals("Data Invalid", status);
    }

    @Test
    void testGetUserById_whenUserId_returnsUser() {
        Map<String, Object> results = dao.getUserById(user.getId());
        String status = (String) results.get("status");
        assertEquals("OK", status);
        User returnedUser = (User) results.get("user");
        assertEquals(user.getId(), returnedUser.getId());
        assertNotEquals("ana20", returnedUser.getUsername());
    }

}






