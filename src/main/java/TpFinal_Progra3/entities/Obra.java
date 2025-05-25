package TpFinal_Progra3.entities;

import TpFinal_Progra3.enums.CategoriaObra;
import TpFinal_Progra3.enums.EstadoObra;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "obras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double latitud;
    private Double longitud;

    @Enumerated(EnumType.STRING)
    private EstadoObra estado;

    private Integer anioEstado;

    @Enumerated(EnumType.STRING)
    private CategoriaObra categoria;

    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "imagenes_obra",
            joinColumns = @JoinColumn(name = "imagen_id"),
            inverseJoinColumns = @JoinColumn(name = "obra_id")
    )
    private List<Imagen> imagenes;

    @ManyToOne
    @JoinColumn(name = "estudioarq_id") // Clave foránea en la tabla Obra
    private EstudioArquitectura estudio;
}