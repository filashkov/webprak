package com.filashkov.webprak.models;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
// @RequiredArgsConstructor
@Embeddable
public class ComplexId implements Serializable {

    @Getter
    @Setter
    public Long employee_id;

    @Getter
    @Setter
    public Long registered_service_id;
}
