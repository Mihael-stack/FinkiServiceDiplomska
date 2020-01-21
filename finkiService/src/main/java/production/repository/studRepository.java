package production.repository;
import org.springframework.stereotype.Service;
import production.model.Student;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class studRepository {
    private Long counter = 0L;
    private List<Student> students = null;

    @PostConstruct
    public void postConstruct() {

        students = new ArrayList<>();
        Student s = new Student();
        s.setId(getNextId());
        s.setFirstName("Elena");
        s.setLastName("Naumova");
        students.add(s);

    }

    public List<Student> findAll() {
        return students;
    }

    private Long getNextId() {
        return counter++;
    }
}
