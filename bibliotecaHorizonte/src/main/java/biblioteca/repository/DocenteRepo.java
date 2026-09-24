package biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import biblioteca.model.Docente;

@Repository
public interface DocenteRepo extends JpaRepository<Docente, Long> {

}