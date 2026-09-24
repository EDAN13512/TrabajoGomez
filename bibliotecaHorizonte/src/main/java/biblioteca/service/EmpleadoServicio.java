package biblioteca.service;

import org.springframework.data.domain.Page;
import biblioteca.model.Empleado;

import java.util.List;

public interface EmpleadoServicio {
    List<Empleado> getEmpleados();
    void agregarEmpleado(Empleado empleado);
    Empleado getEmpleadoId(long id);
    void eliminarEmpleado(long id);
    Page<Empleado> encontrarPaginas(int paginaNo, int dimensionPagina, String ordenarCampo, String ordenarDireccion);
}
