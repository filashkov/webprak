package com.filashkov.webprak.DAO;

import lombok.Builder;
import lombok.Getter;
import com.filashkov.webprak.models.Services;

import java.util.List;

public interface ServicesDAO extends GenericDAO<Services, Long> {
    List<Services> getAllServicesByValue(String col_name, String serviceName);
    List<Services> getAllServicesByRange(String col_name, String lower_bound, String upper_bound);
    // Service getSingleByID(Long id);

//    @Builder
//    @Getter
//    class Filter {
//        private String name;
//    }
//
//    static Filter.FilterBuilder getFilterBuilder() {
//        return Filter.builder();
//    }
}
