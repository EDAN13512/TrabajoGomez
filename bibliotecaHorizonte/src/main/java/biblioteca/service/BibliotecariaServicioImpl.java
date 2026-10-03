package biblioteca.service;

import biblioteca.dtos.SolicitudDto.SolicitudDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import biblioteca.model.Solicitud;
import biblioteca.repository.SolicitudRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BibliotecariaServicioImpl implements BibliotecariaServicio {

    @Autowired
    private SolicitudRepo solicitudRepo;

    @Override
    public List<SolicitudDTO> obtenerSolicitudesPendientes() {
        return solicitudRepo.findByEstado("PENDIENTE").stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    public void confirmarSolicitud(Long idSolicitud) {
        Solicitud solicitud = solicitudRepo.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        boolean existeConfirmada = solicitudRepo.existeLaSolicitud(
                solicitud.getRecurso().getId(),
                solicitud.getFecha(),
                solicitud.getModulo().getId(),
                "CONFIRMADA"
        );

        if (existeConfirmada) {
            throw new RuntimeException("Conflicto: El recurso ya posee una reserva CONFIRMADA para esa fecha y módulo.");
        }

        solicitud.setEstado("CONFIRMADA");
        solicitudRepo.save(solicitud);
    }

    @Override
    public void rechazarSolicitud(Long idSolicitud) {
        Solicitud solicitud = solicitudRepo.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        solicitud.setEstado("RECHAZADA");
        solicitudRepo.save(solicitud);
    }

    private SolicitudDTO mapearADTO(Solicitud solicitud) {
        SolicitudDTO dto = new SolicitudDTO();
        dto.setId(solicitud.getId());
        dto.setDocenteId(solicitud.getDocente().getId());
        dto.setNombreDocente(solicitud.getDocente().getNombre() + " " + solicitud.getDocente().getApellido());
        dto.setRecursoId(solicitud.getRecurso().getId());
        dto.setNombreRecurso(solicitud.getRecurso().getNombre());
        dto.setModuloId(solicitud.getModulo().getId());
        dto.setNombreModulo(solicitud.getModulo().getNombre());
        dto.setFecha(solicitud.getFecha());
        dto.setEstado(solicitud.getEstado());
        return dto;
    }
}