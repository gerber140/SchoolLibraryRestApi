package pl.kurs.schoollibraryrestapi.dto;

import lombok.Data;

@Data
public class TeacherSimpleDTO {
    private Long id;
    private String firstName;
    private String lastName;
}
