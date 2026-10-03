package biblioteca.controller;

import biblioteca.dtos.SolicitudDto.SolicitudDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import biblioteca.service.BibliotecariaServicio;

import java.util.List;

@Controller
@RequestMapping("/biblioteca")
public class BibliotecariaControlador {

    @Autowired
    private BibliotecariaServicio bibliotecariaServicio;

    @GetMapping("/pendientes")
    public String verPendientes(Model modelo) {
        List<SolicitudDTO> pendientes = bibliotecariaServicio.obtenerSolicitudesPendientes();
        modelo.addAttribute("listaPendientes", pendientes);
        return "listaPendientes";
    }


    @PostMapping("/confirmar/{id}")
    public String confirmarReserva(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            bibliotecariaServicio.confirmarSolicitud(id);
            redirectAttributes.addFlashAttribute("mensajeExito", "RESERVADO");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        }
        return "redirect:/biblioteca/pendientes";
    }

    @PostMapping("/rechazar/{id}")
    public String rechazarReserva(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            bibliotecariaServicio.rechazarSolicitud(id);
            redirectAttributes.addFlashAttribute("mensajeInfo", "La solicitud fue rechazada exitosamente.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
        }
        return "redirect:/biblioteca/pendientes";
    }
}