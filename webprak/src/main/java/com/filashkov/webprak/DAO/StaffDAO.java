package com.filashkov.webprak.DAO;

import com.filashkov.webprak.models.Staff;

import java.util.List;

public interface StaffDAO extends GenericDAO<Staff, Long> {
    List<Staff> getAllStaffByValue(String col_name, String value);
}
