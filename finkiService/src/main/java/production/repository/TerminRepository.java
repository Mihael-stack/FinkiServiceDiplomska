package production.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import production.model.Termin;

public interface TerminRepository extends JpaRepository<Termin, Long> {
}
