package pl.kurs.schoollibraryrestapi.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.schoollibraryrestapi.command.CreateTeacherCommand;
import pl.kurs.schoollibraryrestapi.command.UpdateTeacherCommand;
import pl.kurs.schoollibraryrestapi.dto.StatusDTO;
import pl.kurs.schoollibraryrestapi.dto.TeacherDTO;
import pl.kurs.schoollibraryrestapi.model.Teacher;
import pl.kurs.schoollibraryrestapi.services.TeacherManagementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
@AllArgsConstructor
public class TeacherController {
    private TeacherManagementService teacherManagementService;
    private ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseEntity<TeacherDTO> addTeacher(@RequestBody @Valid CreateTeacherCommand command) {
        Teacher teacherForSave = modelMapper.map(command, Teacher.class);
        teacherForSave = teacherManagementService.add(teacherForSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(teacherForSave, TeacherDTO.class));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable("id") long id){
        return ResponseEntity.ok(modelMapper.map(teacherManagementService.get(id), TeacherDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getTeacher(){
        List<TeacherDTO> response = teacherManagementService.getAll().stream()
                .map(x -> modelMapper.map(x, TeacherDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<TeacherDTO> updateTeacherById(@RequestBody UpdateTeacherCommand command){
        Teacher teacherForUpdate = modelMapper.map(command, Teacher.class);
        teacherForUpdate = teacherManagementService.edit(teacherForUpdate);
        return ResponseEntity.ok(modelMapper.map(teacherForUpdate, TeacherDTO.class));

    }

    @DeleteMapping
    public ResponseEntity<StatusDTO> deleteTeacherById(@PathVariable("id") long id) {
        teacherManagementService.delete(id);
        return ResponseEntity.ok(new StatusDTO("Deleted: " + id));
    }
}
