package com.filashkov.webprak.DAO.implemetation;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.filashkov.webprak.DAO.ContractsDAO;
import com.filashkov.webprak.models.Contracts;

import java.sql.Date;
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
        try (Session session = sessionFactory.openSession()) {
            Query<Contracts> query = session.createQuery
                            ("FROM Contracts WHERE " + col_name + " = :v", Contracts.class);

            if ((Objects.equals(col_name, "beginning_date")) || (Objects.equals(col_name, "date_of_completion"))) {
                query.setParameter("v", Date.valueOf(value));
            } else if (Objects.equals(col_name, "contract_description")) {
                query.setParameter("v", value);
            } else {
                query.setParameter("v", Long.parseLong(value, 10));
            }
            return query.getResultList();
        }
    }

    @Override
    public List<Contracts> getAllContractsByColRange(String col_name, String lowerDateBound, String upperDateBound) {
        try (Session session = sessionFactory.openSession()) {
            Query<Contracts> query = session.createQuery
                            ("FROM Contracts WHERE " + col_name + " BETWEEN :lowerd and :upperd", Contracts.class);

            if ((Objects.equals(col_name, "beginning_date")) || (Objects.equals(col_name, "date_of_completion"))) {
                query.setParameter("lowerd", Date.valueOf(lowerDateBound));
                query.setParameter("upperd", Date.valueOf(upperDateBound));
            } else if (Objects.equals(col_name, "contract_description")) {
                query.setParameter("lowerd", lowerDateBound);
                query.setParameter("upperd", upperDateBound);
            } else {
                query.setParameter("lowerd", Long.parseLong(lowerDateBound, 10));
                query.setParameter("upperd", Long.parseLong(upperDateBound, 10));
            }
            return query.getResultList();
        }
    }
}
