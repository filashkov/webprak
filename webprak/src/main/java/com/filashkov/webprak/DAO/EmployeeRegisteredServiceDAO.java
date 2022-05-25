package com.filashkov.webprak.DAO;

import com.filashkov.webprak.models.ComplexId;
import com.filashkov.webprak.models.EmployeeRegisteredService;

import java.util.List;

public interface EmployeeRegisteredServiceDAO extends GenericDAO<EmployeeRegisteredService, ComplexId> {
    List<EmployeeRegisteredService> getAllEmployeeRegisteredService(String col_name, String value);
}
