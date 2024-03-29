package pl.kurs.schoollibraryrestapi.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import pl.kurs.schoollibraryrestapi.validations.Pesel;

import java.time.LocalDate;

@Data
public class CreateStudentCommand {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Pesel
    private String pesel;
    @Past
    private LocalDate birthDate;
    @NotNull
    private Long teacherNo;
}
