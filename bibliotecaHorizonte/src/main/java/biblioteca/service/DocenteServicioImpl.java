package biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import biblioteca.model.Docente;
import biblioteca.repository.DocenteRepo;
import biblioteca.dtos.docenteDto.DocenteDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocenteServicioImpl implements DocenteServicio {

    @Autowired
    private DocenteRepo docenteRepo;

    @Override
    public List<DocenteDTO> getDocentes() {
        return docenteRepo.findAll().stream()
                .map(this::mapearADTO)
                .collect(Collectors.toList());
    }

    @Override
    public void agregarDocente(DocenteDTO docenteDTO) {
        Docente docente = mapearAEntidad(docenteDTO);
        docenteRepo.save(docente);
    }

    @Override
    public DocenteDTO getDocenteId(long id) {
        Docente docente = docenteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("DOCENTE CON EL ID " + id + " NO ENCONTRADO"));
        return mapearADTO(docente);
    }

    @Override
    public void actualizarDocente(long id, DocenteDTO docenteDTO) {
        Docente docenteExistente = docenteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("DOCENTE CON EL ID " + id + " NO ENCONTRADO"));

        docenteExistente.setNombre(docenteDTO.getNombre());
        docenteExistente.setApellido(docenteDTO.getApellido());
        docenteExistente.setEmail(docenteDTO.getEmail());

        docenteRepo.save(docenteExistente);
    }

    @Override
    public void eliminarDocente(long id) {
        docenteRepo.deleteById(id);
    }

    @Override
    public Page<DocenteDTO> encontrarPaginas(int paginaNo, int dimensionPagina, String ordenarCampo, String ordenarDireccion) {
        Sort ordenado = ordenarDireccion.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(ordenarCampo).ascending()
                : Sort.by(ordenarCampo).descending();

        Pageable paginado = PageRequest.of(paginaNo - 1, dimensionPagina, ordenado);
        Page<Docente> paginaEntidad = docenteRepo.findAll(paginado);

        return paginaEntidad.map(this::mapearADTO);
    }


    private DocenteDTO mapearADTO(Docente docente) {
        DocenteDTO dto = new DocenteDTO();
        dto.setId(docente.getId());
        dto.setNombre(docente.getNombre());
        dto.setApellido(docente.getApellido());
        dto.setEmail(docente.getEmail());
        return dto;
    }

    private Docente mapearAEntidad(DocenteDTO dto) {
        Docente docente = new Docente();
        docente.setNombre(dto.getNombre());
        docente.setApellido(dto.getApellido());
        docente.setEmail(dto.getEmail());
        return docente;
    }
}