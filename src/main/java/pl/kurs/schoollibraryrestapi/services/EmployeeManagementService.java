package pl.kurs.schoollibraryrestapi.services;

import org.springframework.stereotype.Service;
import pl.kurs.schoollibraryrestapi.model.Employee;
import pl.kurs.schoollibraryrestapi.repository.EmployeeRepository;

@Service
public class EmployeeManagementService extends GenericManagementService<Employee, EmployeeRepository>{
    public EmployeeManagementService(EmployeeRepository repository){super(repository);}
}
