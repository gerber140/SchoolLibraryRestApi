package pl.kurs.schoollibraryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.schoollibraryrestapi.model.Grade;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("SELECT g FROM Grade g WHERE g.student.id = :id")
    List<Grade> findGradesByStudent(long id);
}
