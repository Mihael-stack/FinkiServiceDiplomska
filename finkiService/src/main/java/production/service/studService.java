package production.service;

import production.model.Student;
import java.util.List;
import java.util.Optional;

public interface studService {
    List<Student> getAll();
    Optional<Student> getOne(Long id);
}
