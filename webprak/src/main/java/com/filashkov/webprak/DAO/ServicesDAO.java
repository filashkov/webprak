package com.filashkov.webprak.DAO;

import lombok.Builder;
import lombok.Getter;
import com.filashkov.webprak.models.Services;

import java.util.List;

public interface ServicesDAO extends GenericDAO<Services, Long> {
    List<Services> getAllServicesByName(String serviceName);

    Services getSingleServiceByName(String serviceName);
    // Service getSingleByID(Long id);

    @Builder
    @Getter
    class Filter {
        private String name;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }
}
