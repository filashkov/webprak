package com.filashkov.webprak.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ComplexId implements Serializable {
    public Long employee_id;
    public Long registered_service_id;
}
