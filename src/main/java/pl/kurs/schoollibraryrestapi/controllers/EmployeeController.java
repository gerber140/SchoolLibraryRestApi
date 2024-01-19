package pl.kurs.schoollibraryrestapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.schoollibraryrestapi.command.CreateEmployeeCommand;
import pl.kurs.schoollibraryrestapi.command.UpdateEmployeeCommand;
import pl.kurs.schoollibraryrestapi.dto.EmployeeDTO;
import pl.kurs.schoollibraryrestapi.dto.StatusDTO;
import pl.kurs.schoollibraryrestapi.model.Employee;
import pl.kurs.schoollibraryrestapi.services.EmployeeManagementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeManagementService employeeManagementService;
    private ModelMapper modelMapper;

    @PostMapping("/add")
    @Operation(summary = "Endpoint to add Employee")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody CreateEmployeeCommand command) {
        Employee employeeForSave = modelMapper.map(command, Employee.class);
        employeeForSave = employeeManagementService.add(employeeForSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(employeeForSave, EmployeeDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") long id) {
        return ResponseEntity.ok(modelMapper.map(employeeManagementService.get(id), EmployeeDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        List<EmployeeDTO> response = employeeManagementService.getAll().stream()
                .map(x -> modelMapper.map(x, EmployeeDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody UpdateEmployeeCommand command) {
        Employee employeeForUpdate = modelMapper.map(command, Employee.class);
        employeeForUpdate = employeeManagementService.edit(employeeForUpdate);
        return ResponseEntity.ok(modelMapper.map(employeeForUpdate, EmployeeDTO.class));
    }

    @DeleteMapping
    public ResponseEntity<StatusDTO> deleteEmployeeById(@PathVariable("id") long id) {
        employeeManagementService.delete(id);
        return ResponseEntity.ok(new StatusDTO("Deleted: " + id));
    }
}
