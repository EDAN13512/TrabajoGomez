package biblioteca.service;

import biblioteca.dtos.SolicitudDto.SolicitudDTO;
import biblioteca.model.Docente;
import biblioteca.model.Modulo;
import biblioteca.model.Recurso;
import java.util.List;

public interface ReservaServicio {
    List<Docente> obtenerDocentes();
    List<Recurso> obtenerRecursos();
    List<Modulo> obtenerModulos();
    void guardarSolicitud(SolicitudDTO dto);
    List<SolicitudDTO> obtenerTodasLasSolicitudes();
}