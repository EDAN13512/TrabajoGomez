package biblioteca.dtos.SolicitudDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDTO {
    private long id;
    private long docenteId;
    private String nombreDocente;
    private long recursoId;
    private String nombreRecurso;
    private long moduloId;
    private String nombreModulo;
    private LocalDate fecha;
    private String estado;
}