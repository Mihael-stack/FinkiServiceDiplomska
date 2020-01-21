package production.service;

import io.swagger.models.Model;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import production.model.Student;
import production.repository.studRepository;

import java.util.List;
import java.util.Optional;

@Service
public class studServiceImpl implements studService {

    private studRepository repo;

   public studServiceImpl(studRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Student> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Student> getOne(Long id) {
        return getAll().stream().filter(v -> id.equals(v.getId())).findAny();
    }
}