package pl.kurs.schoollibraryrestapi.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String username;
    private String email;

}
