package pl.kurs.schoollibraryrestapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "employees")
@RequiredArgsConstructor
@Getter
@Setter
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;

    //TODO zadanie do zrobienia w podsumowanie.txt napisane

}
