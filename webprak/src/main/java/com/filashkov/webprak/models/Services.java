package com.filashkov.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "services")
@Getter
@Setter
@ToString
// @NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Services implements GenericEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "service_id")
    private Long id;

    @Column(name = "service_type_name")
    private String service_type_name;

    @Column(name = "service_description")
    private String service_description;

    @Column(name = "service_approximate_cost")
    private Long service_approximate_cost;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Services other = (Services) o;

        return Objects.equals(id, other.id)
                && service_type_name.equals(other.service_type_name)
                && service_description.equals(other.service_description)
                && Objects.equals(service_approximate_cost, other.service_approximate_cost);
    }
}
