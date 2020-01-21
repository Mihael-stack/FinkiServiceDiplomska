package production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import production.model.Diplomska;


public interface DiplomskaRepository extends JpaRepository<Diplomska, Long> {
    Diplomska getById(Long id);

    Diplomska getAllByLevel(Integer level);

    Diplomska getByLevelAndId(Integer level, Long id);


    
}
