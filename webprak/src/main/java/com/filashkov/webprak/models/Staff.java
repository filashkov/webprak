package com.filashkov.webprak.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "staff")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Staff implements GenericEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "employee_id")
    private Long id;

    @Column(nullable = false, name = "employee_fullname")
    @NonNull
    private String employee_fullname;

    @Column(name = "employee_address")
    private String employee_address;

    @Column(name = "employee_phone_number")
    private Long employee_phone_number;

    @Column(name = "employee_email")
    private String employee_email;

    @Column(nullable = false, name = "employee_login")
    @NonNull
    private String employee_login;

    @Column(nullable = false, name = "employee_password")
    @NonNull
    private String employee_password;

    @Column(name = "employee_post")
    private String employee_post;

    @Column(name = "employee_is_admin")
    private Long employee_is_admin;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Staff other = (Staff) o;

        return Objects.equals(id, other.id)
                && employee_fullname.equals(other.employee_fullname)
                && employee_address.equals(other.employee_address)
                && Objects.equals(employee_phone_number, other.employee_phone_number)
                && employee_email.equals(other.employee_email)
                && employee_login.equals(other.employee_login)
                && employee_password.equals(other.employee_password)
                && employee_post.equals(other.employee_post)
                && Objects.equals(employee_is_admin, other.employee_is_admin);
    }
}
