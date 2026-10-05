package biblioteca.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import biblioteca.model.Modulo;
import biblioteca.model.Recurso;
import biblioteca.repository.ModuloRepo;
import biblioteca.repository.RecursoRepo;

@Configuration
public class DatosIniciales {

    @Bean
    public CommandLineRunner cargarDatos(RecursoRepo recursoRepo, ModuloRepo moduloRepo) {
        return args -> {
            if (recursoRepo.count() == 0) {
                recursoRepo.save(new Recurso(0L, "Proyector 1", "Proyector", true));
                recursoRepo.save(new Recurso(0L, "Proyector 2", "Proyector", true));
                recursoRepo.save(new Recurso(0L, "Notebook A", "Notebook", true));
                recursoRepo.save(new Recurso(0L, "Notebook B", "Notebook", true));
                recursoRepo.save(new Recurso(0L, "Notebook C", "Notebook", true));
                recursoRepo.save(new Recurso(0L, "Notebook D", "Notebook", true));
            }
            if (moduloRepo.count() == 0) {
                moduloRepo.save(new Modulo(0L, "Módulo 1", "08:00", "09:20"));
                moduloRepo.save(new Modulo(0L, "Módulo 2", "09:30", "10:50"));
                moduloRepo.save(new Modulo(0L, "Módulo 3", "11:00", "12:20"));
            }
        };
    }
}