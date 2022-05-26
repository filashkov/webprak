package com.filashkov.webprak.DAO.implemetation;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.filashkov.webprak.DAO.ServicesDAO;
import com.filashkov.webprak.models.Services;

import java.util.List;
import java.util.Objects;

@Repository
public class ServicesDAOImplementation extends GenericDAOImplementation<Services, Long> implements ServicesDAO {
    public ServicesDAOImplementation() {
        super(Services.class);
    }

    @Override
    public List<Services> getAllServicesByValue(String col_name, String value) {
        try (Session session = sessionFactory.openSession()) {
            Query<Services> query = session.createQuery("FROM Services WHERE " + col_name + " = :v", Services.class);
            if ((Objects.equals(col_name, "service_type_name")) || (Objects.equals(col_name, "service_description"))) {
                query.setParameter("v", value);
            } else {
                query.setParameter("v", Long.parseLong(value, 10));
            }
            return query.getResultList();
        }
    }

    @Override
    public List<Services> getAllServicesByRange(String col_name, String lower_bound, String upper_bound) {
        try (Session session = sessionFactory.openSession()) {
            Query<Services> query = session.createQuery("FROM Services WHERE " + col_name + " BETWEEN :l AND :u", Services.class);
            if ((Objects.equals(col_name, "service_type_name")) || (Objects.equals(col_name, "service_description"))) {
                query.setParameter("l", lower_bound);
                query.setParameter("u", upper_bound);
            } else {
                query.setParameter("l", Long.parseLong(lower_bound, 10));
                query.setParameter("u", Long.parseLong(upper_bound, 10));
            }
            return query.getResultList();
        }
    }
}
