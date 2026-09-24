package biblioteca.service;

import org.springframework.data.domain.Page;
import biblioteca.dtos.docenteDTO.DocenteDTO;

import java.util.List;

public interface DocenteServicio {
    List<DocenteDTO> getDocentes();
    void agregarDocente(DocenteDTO docenteDTO);
    DocenteDTO getDocenteId(long id);
    void eliminarDocente(long id);
    Page<DocenteDTO> encontrarPaginas(int paginaNo, int dimensionPagina, String ordenarCampo, String ordenarDireccion);
    void actualizarDocente(long id, DocenteDTO docenteDTO);
}