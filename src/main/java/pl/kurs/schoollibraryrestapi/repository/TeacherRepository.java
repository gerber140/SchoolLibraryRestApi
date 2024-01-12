package pl.kurs.schoollibraryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.schoollibraryrestapi.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
