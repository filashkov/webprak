package com.filashkov.webprak.DAO.implemetation;

import com.filashkov.webprak.DAO.EmployeeRegisteredServiceDAO;
import com.filashkov.webprak.models.ComplexId;
import com.filashkov.webprak.models.EmployeeRegisteredService;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Repository
public class EmployeeRegisteredServiceDAOImplementation extends GenericDAOImplementation<EmployeeRegisteredService, ComplexId> implements EmployeeRegisteredServiceDAO {

    public EmployeeRegisteredServiceDAOImplementation () {
        super(EmployeeRegisteredService.class);
    }

    @Override
    public List<EmployeeRegisteredService> getEmployeeRegisteredServiceByValue(String col_name, Long value) {
        try (Session session = sessionFactory.openSession()) {
            Query<EmployeeRegisteredService> query = session.createQuery
                            ("FROM EmployeeRegisteredService WHERE " + col_name + " = :v", EmployeeRegisteredService.class)
                    .setParameter("v", value);
            return query.getResultList();
        }
    }
}
