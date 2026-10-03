package biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import biblioteca.dtos.docenteDto.DocenteDTO;
import biblioteca.service.DocenteServicio;

import java.util.List;

@Controller
@RequestMapping("/docentes")
public class DocenteControlador {

    @Autowired
    private DocenteServicio docenteServicio;

    @GetMapping({"", "/"})
    public String verPaginaInicio(Model modelo) {
        return pasarPagina(1, modelo, "nombre", "asc");
    }

    @GetMapping("/agregar")
    public String verDocenteNuevo(Model modelo) {
        DocenteDTO docenteDTO = new DocenteDTO();
        modelo.addAttribute("docente", docenteDTO);
        return "nuevoDocente";
    }

    @PostMapping("/guardar")
    public String guardarDocente(@ModelAttribute("docente") DocenteDTO docenteDTO) {
        if (docenteDTO.getId() == 0) {
            docenteServicio.agregarDocente(docenteDTO);
        } else {
            docenteServicio.actualizarDocente(docenteDTO.getId(), docenteDTO);
        }
        return "redirect:/docentes";
    }

    @GetMapping("/actualizar/{id}")
    public String actualizarDocenteForm(@PathVariable(value = "id") long id, Model modelo) {
        DocenteDTO docenteDTO = docenteServicio.getDocenteId(id);
        modelo.addAttribute("docente", docenteDTO);
        return "actualizarDocente";
    }

    @GetMapping("/eliminar/{id}")
    public String borrarDocente(@PathVariable(value = "id") long id) {
        docenteServicio.eliminarDocente(id);
        return "redirect:/docentes";
    }

    @GetMapping("/pagina/{paginaNo}")
    public String pasarPagina(@PathVariable(value = "paginaNo") int paginaNo, Model modelo, @RequestParam("ordenarCampo") String ordenarCampo, @RequestParam("ordenarDireccion") String ordenarDireccion) {

        int dimensionPagina = 5;

        Page<DocenteDTO> pagina = docenteServicio.encontrarPaginas(paginaNo, dimensionPagina, ordenarCampo, ordenarDireccion);
        List<DocenteDTO> docentes = pagina.getContent();

        modelo.addAttribute("pasarPagina", paginaNo);
        modelo.addAttribute("totalPaginas", pagina.getTotalPages());
        modelo.addAttribute("totalElementos", pagina.getTotalElements());
        modelo.addAttribute("ordenarCampo", ordenarCampo);
        modelo.addAttribute("ordenarDireccion", ordenarDireccion);
        modelo.addAttribute("revertirOrden", ordenarDireccion.equals("asc") ? "desc" : "asc");
        modelo.addAttribute("listaDocentes", docentes);

        return "indexDocentes";
    }
}