package pl.kurs.schoollibraryrestapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDate birthDate;
}
