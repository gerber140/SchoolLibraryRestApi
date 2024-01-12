package pl.kurs.schoollibraryrestapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.kurs.schoollibraryrestapi.services.Identificationable;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@RequiredArgsConstructor
@Getter
@Setter
public abstract class Person implements Serializable, Identificationable {

    @Column(nullable = false)
    protected String firstName;

    @Column(nullable = false)
    protected String lastName;

    @Column(unique = true, nullable = false)
    protected String pesel;

    @Column(nullable = false)
    protected LocalDate birthDate;
}
