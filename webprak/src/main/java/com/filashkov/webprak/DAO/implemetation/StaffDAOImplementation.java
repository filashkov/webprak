package com.filashkov.webprak.DAO.implemetation;
;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.filashkov.webprak.DAO.StaffDAO;
import com.filashkov.webprak.models.Staff;

import java.util.List;
import java.util.Objects;

@Repository
public class StaffDAOImplementation extends GenericDAOImplementation<Staff, Long> implements StaffDAO {
    public StaffDAOImplementation() {
        super(Staff.class);
    }

    @Override
    public List<Staff> getAllStaffByValue(String col_name, String value) {
        try (Session session = sessionFactory.openSession()) {
            Query<Staff> query = session.createQuery
                            ("FROM Staff WHERE " + col_name + " = :v", Staff.class);
            if ((Objects.equals(col_name, "employee_id"))
                    || (Objects.equals(col_name, "employee_phone_number"))
                    || (Objects.equals(col_name, "employee_is_admin"))) {
                query.setParameter("v", Long.parseLong(value));
            } else {
                query.setParameter("v", value);
            }
            return query.getResultList();
        }
    }
}

