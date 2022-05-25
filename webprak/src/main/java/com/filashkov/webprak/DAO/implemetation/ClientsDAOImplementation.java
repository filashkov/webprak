package com.filashkov.webprak.DAO.implemetation;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.filashkov.webprak.DAO.ClientsDAO;
import com.filashkov.webprak.models.Clients;

import java.util.List;

@Repository
public class ClientsDAOImplementation extends GenericDAOImplementation<Clients, Long> implements ClientsDAO {

    public ClientsDAOImplementation() {
        super(Clients.class);
    }

    @Override
    public List<Clients> getAllClientsByValue(String col_name, String value) {
        try (Session session = sessionFactory.openSession()) {
            Query<Clients> query = session.createQuery("FROM clients WHERE :col = :v", Clients.class)
                    .setParameter("col", col_name)
                    .setParameter("v", value);
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }
}
