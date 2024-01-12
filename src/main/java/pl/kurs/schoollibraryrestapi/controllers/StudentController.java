package pl.kurs.schoollibraryrestapi.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.schoollibraryrestapi.command.CreateStudentCommand;
import pl.kurs.schoollibraryrestapi.dto.StudentDTO;
import pl.kurs.schoollibraryrestapi.model.Student;
import pl.kurs.schoollibraryrestapi.model.Teacher;
import pl.kurs.schoollibraryrestapi.services.StudentManagementService;
import pl.kurs.schoollibraryrestapi.services.TeacherManagementService;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private StudentManagementService studentManagementService;
    private TeacherManagementService teacherManagementService;
    private ModelMapper modelMapper;

    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody CreateStudentCommand command) {
        Student studentForSave = modelMapper.map(command, Student.class);
        Teacher chosenTeacher = teacherManagementService.get(command.getTeacherNo());
        studentForSave.setTeacher(chosenTeacher);
        studentForSave = studentManagementService.add(studentForSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(studentForSave, StudentDTO.class));
    }
}
