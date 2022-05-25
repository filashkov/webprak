package com.filashkov.webprak.DAO;

import com.filashkov.webprak.models.Contracts;

import java.util.List;

public interface ContractsDAO extends GenericDAO<Contracts, Long> {
    List<Contracts> getAllContractsByColValue(String col_name, String value);
    List<Contracts> getAllContractsByColRange(String col_name, String lowerDateBound, String upperDateBound);
}
