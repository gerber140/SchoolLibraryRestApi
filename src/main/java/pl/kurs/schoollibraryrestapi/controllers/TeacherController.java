package pl.kurs.schoollibraryrestapi.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.schoollibraryrestapi.command.CreateTeacherCommand;
import pl.kurs.schoollibraryrestapi.dto.TeacherDTO;
import pl.kurs.schoollibraryrestapi.model.Teacher;
import pl.kurs.schoollibraryrestapi.services.TeacherManagementService;

@RestController
@RequestMapping("/teachers")
@AllArgsConstructor
public class TeacherController {
    private TeacherManagementService teacherManagementService;
    private ModelMapper modelMapper;

    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<TeacherDTO> addTeacher(@RequestBody @Valid CreateTeacherCommand command) {
        Teacher teacherForSave = modelMapper.map(command, Teacher.class);
        teacherForSave = teacherManagementService.add(teacherForSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(teacherForSave, TeacherDTO.class));
    }
}
