package com.filashkov.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;


@Entity
@Table(name = "contracts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Contracts implements GenericEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "contract_id")
    private Long id;

    @Column(name = "client_id")
    private Long client_id;

    @Column(name = "service_type_id")
    private Long service_type_id;

    @Column(nullable = false, name = "beginning_date")
    @NonNull
    private Date beginning_date;

    @Column(name = "date_of_completion")
    private Date date_of_completion;

    @Column(name = "contract_description")
    private String contract_description;

    @Column(name = "real_cost")
    private Long real_cost;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contracts other = (Contracts) o;

        return Objects.equals(id, other.id)
                && Objects.equals(client_id, other.client_id)
                && Objects.equals(service_type_id, other.service_type_id)
                && beginning_date.equals(other.beginning_date)
                && date_of_completion.equals(other.date_of_completion)
                && contract_description.equals(other.contract_description)
                && Objects.equals(real_cost, other.real_cost);
    }
}
