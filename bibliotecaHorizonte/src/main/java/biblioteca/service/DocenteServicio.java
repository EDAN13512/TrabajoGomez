package biblioteca.service;

import org.springframework.data.domain.Page;
import biblioteca.dtos.docenteDto.DocenteDTO;

import java.util.List;

public interface DocenteServicio {
    List<DocenteDTO> getDocentes();
    void agregarDocente(DocenteDTO docenteDTO);
    DocenteDTO getDocenteId(Long id);
    void eliminarDocente(Long id);
    Page<DocenteDTO> encontrarPaginas(int paginaNo, int dimensionPagina, String ordenarCampo, String ordenarDireccion);
    void actualizarDocente(Long id, DocenteDTO docenteDTO);
}