package biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import biblioteca.model.Empleado;
import biblioteca.repository.EmpleadoRepo;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicioImpl implements EmpleadoServicio {

    @Autowired
    private EmpleadoRepo empleadoRepo;

    @Override
    public List<Empleado> getEmpleados(){
        return empleadoRepo.findAll();
    }

    @Override
    public void agregarEmpleado(Empleado empleado) {
        this.empleadoRepo.save(empleado);
    }

    @Override
    public Empleado getEmpleadoId(long id) {
        Optional<Empleado> optional = empleadoRepo.findById(id);
        Empleado empleado = null;
        if(optional.isPresent()){
            empleado = optional.get();
        }else{
            throw new RuntimeException("EMPLEADO CON EL ID " + id + " NO ENCONTRADO");
        }
        return empleado;
    }

    @Override
    public void eliminarEmpleado(long id) {
        this.empleadoRepo.deleteById(id);
    }

    @Override
    public Page<Empleado> encontrarPaginas(int paginaNo, int dimensionPagina, String ordenarCampo, String ordenarDireccion) {
        Sort ordenado = ordenarDireccion.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarCampo).ascending() : Sort.by(ordenarCampo).descending();
        Pageable paginado = PageRequest.of(paginaNo - 1, dimensionPagina, ordenado);
        return this.empleadoRepo.findAll(paginado);
    }
}
