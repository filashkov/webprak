package com.filashkov.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "employee_registered_service")
@Getter
@Setter
@ToString
@NoArgsConstructor
// @RequiredArgsConstructor
@AllArgsConstructor
public class EmployeeRegisteredService implements GenericEntity<ComplexId> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false, name = "employee_id")
//    private Long employee_id;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false, name = "registered_service_id")
//    private Long registered_service_id;

    @EmbeddedId
    ComplexId id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeeRegisteredService other = (EmployeeRegisteredService) o;

        return Objects.equals(id.employee_id, other.id.employee_id)
                && Objects.equals(id.registered_service_id, other.id.registered_service_id);
    }
}

