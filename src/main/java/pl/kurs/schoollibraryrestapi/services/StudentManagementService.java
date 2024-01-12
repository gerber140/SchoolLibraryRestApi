package pl.kurs.schoollibraryrestapi.services;

import org.springframework.stereotype.Service;
import pl.kurs.schoollibraryrestapi.model.Student;
import pl.kurs.schoollibraryrestapi.repository.StudentRepository;

@Service
public class StudentManagementService extends GenericManagementService<Student, StudentRepository> {
    public StudentManagementService(StudentRepository repository) {
        super(repository);
    }
}
