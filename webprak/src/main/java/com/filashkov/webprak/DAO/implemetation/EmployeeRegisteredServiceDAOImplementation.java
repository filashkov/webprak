package com.filashkov.webprak.DAO.implemetation;

import com.filashkov.webprak.DAO.EmployeeRegisteredServiceDAO;
import com.filashkov.webprak.models.ComplexId;
import com.filashkov.webprak.models.EmployeeRegisteredService;
import org.hibernate.Query;
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
    public List<EmployeeRegisteredService> getAllEmployeeRegisteredService(String col_name, String value) {
        try (Session session = sessionFactory.openSession()) {
            Query<EmployeeRegisteredService> query = session.createQuery
                            ("FROM employee_registered_service WHERE :col = :v", EmployeeRegisteredService.class)
                    .setParameter("col", col_name)
                    .setParameter("v", value);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }
}
