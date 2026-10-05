package biblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "recurso")
@AllArgsConstructor
@NoArgsConstructor
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tipo", nullable = false) // Ej: "Proyector" o "Notebook"
    private String tipo;

    @Column(name = "activo")
    private boolean activo = true;

}