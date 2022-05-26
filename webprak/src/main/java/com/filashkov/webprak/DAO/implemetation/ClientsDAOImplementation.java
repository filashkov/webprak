package com.filashkov.webprak.DAO.implemetation;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.filashkov.webprak.DAO.ClientsDAO;
import com.filashkov.webprak.models.Clients;

import java.util.List;
import java.util.Objects;

@Repository
public class ClientsDAOImplementation extends GenericDAOImplementation<Clients, Long> implements ClientsDAO {

    public ClientsDAOImplementation() {
        super(Clients.class);
    }

    @Override
    public List<Clients> getAllClientsByValue(String col_name, String value) {
        try (Session session = sessionFactory.openSession()) {
            String my_query_string = "FROM Clients WHERE " + col_name + " = :v";
            Query<Clients> query = session.createQuery(my_query_string, Clients.class);

            if ((Objects.equals(col_name, "client_id")) || (Objects.equals(col_name, "client_contact_phone"))) {
                query.setParameter("v", Long.parseLong(value, 10));
            } else {
                query.setParameter("v", value);
            }
            return query.getResultList();
        }
    }
}
