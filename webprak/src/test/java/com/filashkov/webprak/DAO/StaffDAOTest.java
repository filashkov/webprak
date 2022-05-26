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
        assertEquals(1, res.size());
        Staff gt = new Staff(1L, "Николаев Пётр Никифорович", "df", 89150879678L, "niks@gmail.com", "niks", "dsfhgjhkjl", "Администратор", 1L);
        assertEquals(res.get(0), gt);
        res = staffDAO.getAllStaffByValue("employee_phone_number", "89150879678");
        assertEquals(1, res.size());
        assertEquals(res.get(0), gt);
    }
}
