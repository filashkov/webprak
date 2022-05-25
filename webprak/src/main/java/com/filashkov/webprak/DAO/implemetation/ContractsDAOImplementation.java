package com.filashkov.webprak.DAO.implemetation;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.filashkov.webprak.DAO.ContractsDAO;
import com.filashkov.webprak.models.Contracts;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public class ContractsDAOImplementation extends GenericDAOImplementation<Contracts, Long> implements ContractsDAO {
    public ContractsDAOImplementation () {
        super(Contracts.class);
    }

    @Override
    public List<Contracts> getAllContractsByColValue(String col_name, String value) {
        if ((Objects.equals(col_name, "beginning_date")) || (Objects.equals(col_name, "date_of_completion"))) {
            try (Session session = sessionFactory.openSession()) {
                Query<Contracts> query = session.createQuery
                                ("FROM Contracts WHERE :col = :v", Contracts.class)
                        .setParameter("col", col_name)
                        .setParameter("v", LocalDate.parse(value));
                return query.getResultList();
            }
        } else if (Objects.equals(col_name, "contract_description")) {
            try (Session session = sessionFactory.openSession()) {
                Query<Contracts> query = session.createQuery
                                ("FROM Contracts WHERE :col LIKE :v", Contracts.class)
                        .setParameter("col", col_name)
                        .setParameter("v", likeExpr(value));
                return query.getResultList();
            }
        } else {
            try (Session session = sessionFactory.openSession()) {
                Query<Contracts> query = session.createQuery
                                ("FROM Contracts WHERE :col = :v", Contracts.class)
                        .setParameter("col", col_name)
                        .setParameter("v", Long.parseLong(value, 10));
                return query.getResultList();
            }
        }
    }

    @Override
    public List<Contracts> getAllContractsByColRange(String col_name, String lowerDateBound, String upperDateBound) {
        if ((Objects.equals(col_name, "beginning_date")) || (Objects.equals(col_name, "date_of_completion"))) {
            try (Session session = sessionFactory.openSession()) {
                Query<Contracts> query = session.createQuery
                                ("FROM Contracts WHERE :col BETWEEN :lowerd and :upperd", Contracts.class)
                        .setParameter("col", col_name)
                        .setParameter("lowerd", LocalDate.parse(lowerDateBound))
                        .setParameter("upperd", LocalDate.parse(upperDateBound));
                return query.getResultList();
            }
        } else if (Objects.equals(col_name, "contract_description")) {
            try (Session session = sessionFactory.openSession()) {
                Query<Contracts> query = session.createQuery
                                ("FROM Contracts WHERE :col BETWEEN :lowerd and :upperd", Contracts.class)
                        .setParameter("col", col_name)
                        .setParameter("lowerd", likeExpr(lowerDateBound))
                        .setParameter("upperd", likeExpr(upperDateBound));
                return query.getResultList();
            }
        } else {
            try (Session session = sessionFactory.openSession()) {
                Query<Contracts> query = session.createQuery
                                ("FROM Contracts WHERE :col BETWEEN :lowerd and :upperd", Contracts.class)
                        .setParameter("col", col_name)
                        .setParameter("lowerd", Long.parseLong(lowerDateBound, 10))
                        .setParameter("upperd", Long.parseLong(upperDateBound, 10));
                return query.getResultList();
            }
        }
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}
