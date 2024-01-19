package pl.kurs.schoollibraryrestapi.command;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UpdateStudentCommand {
    @NotNull
    private long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String pesel;
    @Past
    private LocalDate birthDate;
    @NotNull
    private Long teacherNo;
}
