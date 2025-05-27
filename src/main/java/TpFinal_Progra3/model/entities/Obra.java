package TpFinal_Progra3.model.entities;

import TpFinal_Progra3.model.enums.CategoriaObra;
import TpFinal_Progra3.model.enums.EstadoObra;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "obras")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    //@NotBlank(message = "El nombre de la Obra no puede estar vacio.")
    //@Size(min =2)
    private String nombre;

    @Column(nullable = false)
    //@NotNull
    private Double latitud;

    @Column(nullable = false)
    //@NotNull
    private Double longitud;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoObra estado;

    private Integer anioEstado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaObra categoria;

    @Column(columnDefinition = "MEDIUMTEXT")
    //@NotBlank(message = "Debe ingresar una descripcion de la Obra.")
    //@Size(max = 16000000)
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
    //@NotNull(message = "El estudio asociado no puede ser nulo.")
    private EstudioArquitectura estudio;
}