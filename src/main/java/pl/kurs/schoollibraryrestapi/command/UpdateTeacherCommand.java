package pl.kurs.schoollibraryrestapi.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class UpdateTeacherCommand {
    @NotNull
    private long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String pesel;
    @Past
    private LocalDate birthDate;
}
