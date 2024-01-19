package pl.kurs.schoollibraryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kurs.schoollibraryrestapi.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
