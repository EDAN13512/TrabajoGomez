package biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import biblioteca.model.Empleado;
import biblioteca.service.EmpleadoServicio;

import java.util.List;

@Controller
public class EmpleadoControlador {

    @Autowired
    EmpleadoServicio empleadoServicio;

    @GetMapping("/")
    public String verPaginaInicio(Model modelo){
        return pasarPagina(1, modelo, "nombre", "asc");
    }

    @GetMapping("/agregar")
    public String verEmpleadoNuevo(Model modelo){
        Empleado empleado = new Empleado();
        modelo.addAttribute("empleado", empleado);
        return "nuevoEmpleado";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute("empleado") Empleado empleado){
        empleadoServicio.agregarEmpleado(empleado);
        return "redirect:/";
    }

    @GetMapping("/verFormularioActualizar/{id}")
    public String actualizarEmpleadoForm(@PathVariable (value = "id") long id, Model modelo){
        Empleado empleado = empleadoServicio.getEmpleadoId(id);
        modelo.addAttribute("empleado", empleado);
        return "actualizarEmpleado";
    }

    @GetMapping("/eliminarEmpleado/{id}")
    public String borrarEmpleado(@PathVariable (value = "id") long id){
        this.empleadoServicio.eliminarEmpleado(id);
        return "redirect:/";
    }

    @GetMapping("/pagina/{paginaNo}")
    public String pasarPagina(@PathVariable (value = "paginaNo") int paginaNo, Model modelo, @RequestParam("ordenarCampo") String ordenarCampo, @RequestParam("ordenarDireccion") String ordenarDireccion){
        int dimensionPagina = 5;
        Page<Empleado> pagina = empleadoServicio.encontrarPaginas(paginaNo, dimensionPagina, ordenarCampo, ordenarDireccion);
        List<Empleado> empleados = pagina.getContent();
        modelo.addAttribute("pasarPagina", paginaNo);
        modelo.addAttribute("totalPaginas", pagina.getTotalPages());
        modelo.addAttribute("totalElementos", pagina.getTotalElements());
        modelo.addAttribute("ordenarCampo", ordenarCampo);
        modelo.addAttribute("ordenarDireccion", ordenarDireccion);
        modelo.addAttribute("revertirOrden", ordenarDireccion.equals("asc") ? "desc" : "asc");
        modelo.addAttribute("listaEmpleados", empleados);
        return "index";
    }
}
