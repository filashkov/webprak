package com.filashkov.webprak.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.filashkov.webprak.models.Staff;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class StaffDAOTest {
    @Autowired
    StaffDAO staffDAO;

    @Test
    void test() {
        List<Staff> res = staffDAO.getAllStaffByValue("employee_fullname", "Николаев Пётр Никифорович");
        assertEquals(res.size(), 1);
    }
}
