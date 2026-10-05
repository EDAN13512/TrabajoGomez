package biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import biblioteca.dtos.SolicitudDto.SolicitudDTO;
import biblioteca.service.ReservaServicio;

@Controller
@RequestMapping("/reservas")
public class ReservaControlador {

    @Autowired
    private ReservaServicio reservaServicio;

    @GetMapping("/nueva")
    public String mostrarFormulario(Model modelo) {
        modelo.addAttribute("solicitud", new SolicitudDTO());
        modelo.addAttribute("listaDocentes", reservaServicio.obtenerDocentes());
        modelo.addAttribute("listaRecursos", reservaServicio.obtenerRecursos());
        modelo.addAttribute("listaModulos", reservaServicio.obtenerModulos());
        return "nuevaSolicitud";
    }

    @PostMapping("/guardar")
    public String guardarSolicitud(@ModelAttribute("solicitud") SolicitudDTO solicitudDTO, RedirectAttributes redirectAttributes) {
        reservaServicio.guardarSolicitud(solicitudDTO);
        redirectAttributes.addFlashAttribute("mensajeExito", "Solicitud registrada como PENDIENTE. Aguarde la confirmación de la bibliotecaria.");
        return "redirect:/reservas/nueva";
    }

    @GetMapping({"", "/"})
    public String verListaReservas(Model modelo) {
        modelo.addAttribute("listaReservas", reservaServicio.obtenerTodasLasSolicitudes());
        return "listaReservas";
    }
}