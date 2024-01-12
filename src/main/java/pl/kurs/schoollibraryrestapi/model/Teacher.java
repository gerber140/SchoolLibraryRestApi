package pl.kurs.schoollibraryrestapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
@RequiredArgsConstructor
@Getter
@Setter
public class Teacher extends Person {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_teacher")
    private Long id;


    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Set<Grade> grades = new HashSet<>();
    @Override
    public Long getId() {
        return id;
    }
}
