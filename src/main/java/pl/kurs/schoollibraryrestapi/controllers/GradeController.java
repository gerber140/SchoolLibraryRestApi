package pl.kurs.schoollibraryrestapi.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.schoollibraryrestapi.command.CreateGradeCommand;
import pl.kurs.schoollibraryrestapi.dto.GradeDTO;
import pl.kurs.schoollibraryrestapi.model.Grade;
import pl.kurs.schoollibraryrestapi.model.Student;
import pl.kurs.schoollibraryrestapi.model.Teacher;
import pl.kurs.schoollibraryrestapi.services.GradeManagementService;
import pl.kurs.schoollibraryrestapi.services.StudentManagementService;
import pl.kurs.schoollibraryrestapi.services.TeacherManagementService;

@RestController
@RequestMapping("/grades")
@AllArgsConstructor
public class GradeController {
    private GradeManagementService gradeManagementService;
    private ModelMapper modelMapper;
    private StudentManagementService studentManagementService;
    private TeacherManagementService teacherManagementService;

    @PostMapping
    @RequestMapping("/add")
    public ResponseEntity<GradeDTO> addGrade(@RequestBody CreateGradeCommand request) {
        Grade gradeForSave = modelMapper.map(request, Grade.class);
        Student chosenStudent = studentManagementService.get(request.getStudentNo());
        Teacher chosenTeacher = teacherManagementService.get(request.getTeacherNo());
        gradeForSave.setStudent(chosenStudent);
        gradeForSave.setTeacher(chosenTeacher);
        gradeForSave = gradeManagementService.add(gradeForSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(gradeForSave, GradeDTO.class));
    }

}
