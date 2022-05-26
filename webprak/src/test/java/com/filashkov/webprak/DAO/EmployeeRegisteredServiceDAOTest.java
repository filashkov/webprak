package com.filashkov.webprak.DAO;

import com.filashkov.webprak.models.ComplexId;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.filashkov.webprak.models.EmployeeRegisteredService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class EmployeeRegisteredServiceDAOTest {

    @Autowired
    private EmployeeRegisteredServiceDAO employeeRegisteredServiceDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void test() {
        ComplexId my_complex_id = new ComplexId(3L, 2L);

        List<EmployeeRegisteredService> gt = new ArrayList<>();
        gt.add(new EmployeeRegisteredService(my_complex_id));
        gt.add(new EmployeeRegisteredService(new ComplexId(3L, 1L)));

        List<EmployeeRegisteredService> res = employeeRegisteredServiceDAO.getEmployeeRegisteredServiceByValue("employee_id", 3L);
        assertTrue(res.size() == gt.size() && res.containsAll(gt) && gt.containsAll(res));

        EmployeeRegisteredService another_res = employeeRegisteredServiceDAO.getById(my_complex_id);
        assertEquals(gt.get(0), another_res);
    }
}
