package com.filashkov.webprak.DAO.implemetation;
;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.filashkov.webprak.DAO.StaffDAO;
import com.filashkov.webprak.models.Staff;

import java.util.List;

@Repository
public class StaffDAOImplementation extends GenericDAOImplementation<Staff, Long> implements StaffDAO {
    public StaffDAOImplementation() {
        super(Staff.class);
    }

    @Override
    public List<Staff> getAllStaffByValue(String col_name, String value) {
        try (Session session = sessionFactory.openSession()) {
            Query<Staff> query = session.createQuery
                            ("FROM Staff WHERE :col = :v", Staff.class)
                    .setParameter("col", col_name)
                    .setParameter("v", value);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    /*
    private String likeExpr(String param) {
        return "%" + param + "%";
    }
    */
}

