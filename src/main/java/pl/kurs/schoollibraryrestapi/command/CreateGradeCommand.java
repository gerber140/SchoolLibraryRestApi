package pl.kurs.schoollibraryrestapi.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import pl.kurs.schoollibraryrestapi.model.GradeLevel;
import pl.kurs.schoollibraryrestapi.validations.NonVulgar;

import java.time.LocalDate;

@Data
public class CreateGradeCommand {
    @NotNull
    private GradeLevel gradeLevel;
    @PastOrPresent
    private LocalDate date;
    @NotNull
    private Long studentNo;
    @NotBlank
    private String schoolSubject;
    @NotBlank
    @NonVulgar
    private String description;
    @NotNull
    private Long teacherNo;
}
