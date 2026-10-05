package biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import biblioteca.dtos.SolicitudDto.SolicitudDTO;
import biblioteca.model.Solicitud;
import biblioteca.repository.DocenteRepo;
import biblioteca.repository.ModuloRepo;
import biblioteca.repository.RecursoRepo;
import biblioteca.repository.SolicitudRepo;

import java.util.List;

@Service
public class ReservaServicioImpl implements ReservaServicio {

    @Autowired private DocenteRepo docenteRepo;
    @Autowired private RecursoRepo recursoRepo;
    @Autowired private ModuloRepo moduloRepo;
    @Autowired private SolicitudRepo solicitudRepo;

    @Override
    public List<biblioteca.model.Docente> obtenerDocentes() { return docenteRepo.findAll(); }

    @Override
    public List<biblioteca.model.Recurso> obtenerRecursos() { return recursoRepo.findAll(); }

    @Override
    public List<biblioteca.model.Modulo> obtenerModulos() { return moduloRepo.findAll(); }

    @Override
    public void guardarSolicitud(SolicitudDTO dto) {
        Solicitud solicitud = new Solicitud();
        solicitud.setDocente(docenteRepo.findById(dto.getDocenteId()).orElseThrow());
        solicitud.setRecurso(recursoRepo.findById(dto.getRecursoId()).orElseThrow());
        solicitud.setModulo(moduloRepo.findById(dto.getModuloId()).orElseThrow());
        solicitud.setFecha(dto.getFecha());
        solicitud.setEstado("PENDIENTE");

        solicitudRepo.save(solicitud);
    }

    @Override
    public List<SolicitudDTO> obtenerTodasLasSolicitudes() {
        return solicitudRepo.findAll().stream()
                .map(solicitud -> {
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
                })
                .collect(java.util.stream.Collectors.toList());
    }
}