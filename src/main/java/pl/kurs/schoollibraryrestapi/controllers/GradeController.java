package pl.kurs.schoollibraryrestapi.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.schoollibraryrestapi.command.CreateGradeCommand;
import pl.kurs.schoollibraryrestapi.dto.GradeDTO;
import pl.kurs.schoollibraryrestapi.dto.StatusDTO;
import pl.kurs.schoollibraryrestapi.model.Grade;
import pl.kurs.schoollibraryrestapi.model.Student;
import pl.kurs.schoollibraryrestapi.model.Teacher;
import pl.kurs.schoollibraryrestapi.services.GradeManagementService;
import pl.kurs.schoollibraryrestapi.services.StudentManagementService;
import pl.kurs.schoollibraryrestapi.services.TeacherManagementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grades")
@AllArgsConstructor
public class GradeController {
    private GradeManagementService gradeManagementService;
    private ModelMapper modelMapper;
    private StudentManagementService studentManagementService;
    private TeacherManagementService teacherManagementService;

    @PostMapping("/add")
    public ResponseEntity<GradeDTO> addGrade(@RequestBody @Valid CreateGradeCommand request) {
        Grade gradeForSave = modelMapper.map(request, Grade.class);
        Student chosenStudent = studentManagementService.get(request.getStudentNo());
        Teacher chosenTeacher = teacherManagementService.get(request.getTeacherNo());
        gradeForSave.setStudent(chosenStudent);
        gradeForSave.setTeacher(chosenTeacher);
        gradeForSave = gradeManagementService.add(gradeForSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(gradeForSave, GradeDTO.class));
    }
    @GetMapping("/{id}")
    public ResponseEntity<GradeDTO> getGradeById(@PathVariable("id") long id){
        return ResponseEntity.ok(modelMapper.map(gradeManagementService.get(id), GradeDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<GradeDTO>> getGrades(){
        List<GradeDTO> response = gradeManagementService.getAll().stream()
                .map(x -> modelMapper.map(x, GradeDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @DeleteMapping
    public ResponseEntity<StatusDTO> deleteGradeById(@PathVariable("id") long id){
        teacherManagementService.delete(id);
        return ResponseEntity.ok(new StatusDTO("Deleted: " + id));
    }

}
