package com.filashkov.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Clients implements GenericEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "client_id")
    private Long id;

    @Column(nullable = false, name = "client_fullname")
    @NonNull
    private String client_fullname;

    @Column(name = "client_contact_phone")
    private Long client_contact_phone;

    @Column(name = "client_email")
    private String client_email;

    @Column(nullable = false, name = "client_login")
    @NonNull
    private String client_login;

    @Column(nullable = false, name = "client_password")
    @NonNull
    private String client_password;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Clients other = (Clients) o;

        return Objects.equals(id, other.id)
                && client_fullname.equals(other.client_fullname)
                && Objects.equals(client_contact_phone, other.client_contact_phone)
                && client_email.equals(other.client_email)
                && client_login.equals(other.client_login)
                && client_password.equals(other.client_password);
    }
}
