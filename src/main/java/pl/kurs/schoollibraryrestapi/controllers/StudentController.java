package pl.kurs.schoollibraryrestapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.schoollibraryrestapi.command.CreateStudentCommand;
import pl.kurs.schoollibraryrestapi.command.UpdateStudentCommand;
import pl.kurs.schoollibraryrestapi.dto.StatusDTO;
import pl.kurs.schoollibraryrestapi.dto.StudentDTO;
import pl.kurs.schoollibraryrestapi.model.Student;
import pl.kurs.schoollibraryrestapi.model.Teacher;
import pl.kurs.schoollibraryrestapi.services.StudentManagementService;
import pl.kurs.schoollibraryrestapi.services.TeacherManagementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private StudentManagementService studentManagementService;
    private TeacherManagementService teacherManagementService;
    private ModelMapper modelMapper;

    @PostMapping("/add")
    @Operation(summary = "Endpoint to add student")
    public ResponseEntity<StudentDTO> addStudent(@RequestBody CreateStudentCommand command) {
        Student studentForSave = modelMapper.map(command, Student.class);
        Teacher chosenTeacher = teacherManagementService.get(command.getTeacherNo());
        studentForSave.setTeacher(chosenTeacher);
        studentForSave = studentManagementService.add(studentForSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(studentForSave, StudentDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") long id) {
        return ResponseEntity.ok(
          modelMapper.map(studentManagementService.get(id), StudentDTO.class)
        );
    }
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getStudents() {
        List<StudentDTO> response = studentManagementService.getAll().stream()
                .map(x -> modelMapper.map(x, StudentDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    @PutMapping
    public ResponseEntity<StudentDTO> updateStudentById(@RequestBody UpdateStudentCommand command) {
        Student studentForUpdate = modelMapper.map(command, Student.class);
        studentForUpdate = studentManagementService.edit(studentForUpdate);
        return ResponseEntity.ok(modelMapper.map(studentForUpdate, StudentDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StatusDTO> deleteStudentById(@PathVariable("id") long id) {
        studentManagementService.delete(id);
        return ResponseEntity.ok(new StatusDTO("Deleted: " +id));
    }
}
