package biblioteca.dtos.SolicitudDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDTO {
    private Long id;
    private Long docenteId;
    private String nombreDocente;
    private Long recursoId;
    private String nombreRecurso;
    private Long moduloId;
    private String nombreModulo;
    private LocalDate fecha;
    private String estado;
}