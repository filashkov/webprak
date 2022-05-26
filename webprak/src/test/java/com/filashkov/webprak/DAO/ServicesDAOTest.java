package com.filashkov.webprak.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.filashkov.webprak.models.Services;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class ServicesDAOTest {
    @Autowired
    private ServicesDAO servicesDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void test_selection() {
        List<Services> services_for_200 = servicesDAO.getAllServicesByRange("service_approximate_cost", "150", "400");
        Services gt_service_for_200 = new Services(3L, "Цифровое право", "Защита в области информационных технологий", 200L);
        assertEquals(1, services_for_200.size());
        assertEquals(gt_service_for_200, services_for_200.get(0));

        services_for_200 = servicesDAO.getAllServicesByValue("service_approximate_cost", "200");
        assertEquals(1, services_for_200.size());
        assertEquals(gt_service_for_200, services_for_200.get(0));

        List<Services> services_for_property = servicesDAO.getAllServicesByValue("service_type_name", "Недвижимость");
        Services property_service = new Services(2L, "Недвижимость", "Всё, что связано с недвижимостью", 100L);
        assertEquals(1, services_for_property.size());
        assertEquals(property_service, services_for_property.get(0));

        services_for_property = servicesDAO.getAllServicesByRange("service_type_name", "М", "О");
        assertEquals(1, services_for_property.size());
        assertEquals(property_service, services_for_property.get(0));
    }
}
