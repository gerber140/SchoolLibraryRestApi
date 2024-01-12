package pl.kurs.schoollibraryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.schoollibraryrestapi.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
