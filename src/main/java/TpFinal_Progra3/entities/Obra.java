package TpFinal_Progra3.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
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

    @OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
    private List<Imagen> imagenes;
}
