package biblioteca.service;


import biblioteca.dtos.SolicitudDto.SolicitudDTO;

import java.util.List;

public interface BibliotecariaServicio {
    List<SolicitudDTO> obtenerSolicitudesPendientes();
    void confirmarSolicitud(Long idSolicitud);
    void rechazarSolicitud(Long idSolicitud);
}