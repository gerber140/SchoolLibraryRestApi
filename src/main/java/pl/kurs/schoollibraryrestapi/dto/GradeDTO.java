package pl.kurs.schoollibraryrestapi.dto;

import lombok.Data;
import pl.kurs.schoollibraryrestapi.model.GradeLevel;

import java.time.LocalDate;

@Data
public class GradeDTO {
    private Long id;
    private GradeLevel gradeLevel;
    private LocalDate date;
    private StudentSimpleDTO student;
    private String schoolSubject;
    private String description;
    private TeacherSimpleDTO teacher;
}
