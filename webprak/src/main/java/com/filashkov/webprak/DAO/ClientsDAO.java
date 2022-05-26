package com.filashkov.webprak.DAO;

import com.filashkov.webprak.models.Clients;

import java.util.List;

public interface ClientsDAO extends GenericDAO<Clients, Long> {
    List<Clients> getAllClientsByValue(String col_name, String value);
}
