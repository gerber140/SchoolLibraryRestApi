package pl.kurs.schoollibraryrestapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.kurs.schoollibraryrestapi.services.Identificationable;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "students")
@RequiredArgsConstructor
@Getter
@Setter
public class Student extends Person {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private Long id;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Grade> grades = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
}
