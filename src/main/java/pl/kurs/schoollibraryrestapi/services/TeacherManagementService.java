package pl.kurs.schoollibraryrestapi.services;

import org.springframework.stereotype.Service;
import pl.kurs.schoollibraryrestapi.model.Teacher;
import pl.kurs.schoollibraryrestapi.repository.TeacherRepository;

@Service
public class TeacherManagementService extends GenericManagementService<Teacher, TeacherRepository>{
    public TeacherManagementService(TeacherRepository repository) {
        super(repository);
    }
}
