package com.filashkov.webprak.DAO.implemetation;

import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.filashkov.webprak.DAO.ServicesDAO;
import com.filashkov.webprak.models.Services;

import java.util.List;

@Repository
public class ServicesDAOImplementation extends GenericDAOImplementation<Services, Long> implements ServicesDAO {
    public ServicesDAOImplementation() {
        super(Services.class);
    }

    @Override
    public List<Services> getAllServicesByName(String serviceName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Services> query = session.createQuery("FROM services WHERE name LIKE :gotName", Services.class)
                    .setParameter("gotName", likeExpr(serviceName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Services getSingleServiceByName(String serviceName) {
        List<Services> candidates = this.getAllServicesByName(serviceName);
        return candidates == null ? null :
                candidates.size() == 1 ? candidates.get(0) : null;
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}
