package biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import biblioteca.model.Solicitud;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SolicitudRepo extends JpaRepository<Solicitud, Long> {

    List<Solicitud> findByEstado(String estado);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Solicitud s WHERE s.recurso.id = :recursoId AND s.fecha = :fecha AND s.modulo.id = :moduloId AND s.estado = :estado")
    boolean existeLaSolicitud(@Param("recursoId") Long recursoId, @Param("fecha") LocalDate fecha, @Param("moduloId") Long moduloId, @Param("estado") String estado);
}