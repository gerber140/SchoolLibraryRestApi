package pl.kurs.schoollibraryrestapi.services;

import org.springframework.stereotype.Service;
import pl.kurs.schoollibraryrestapi.repository.GradeRepository;
import pl.kurs.schoollibraryrestapi.model.Grade;

import java.util.List;

@Service
public class GradeManagementService extends GenericManagementService<Grade, GradeRepository>{

    public GradeManagementService(GradeRepository repository) {
        super(repository);
    }

    public List<Grade> getGradesByStudentId(long id) {
        return repository.findGradesByStudent(id);
    }
}
