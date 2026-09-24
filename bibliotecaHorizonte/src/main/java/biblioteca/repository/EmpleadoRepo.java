package biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import biblioteca.model.Empleado;

@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado, Long> {

}