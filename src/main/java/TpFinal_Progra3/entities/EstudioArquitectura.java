package TpFinal_Progra3.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "estudios_arq")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudioArquitectura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre del estudio no puede estar vacio.")
    private String nombre;

    @OneToMany(mappedBy = "estudio")
    private List<Obra> obras;

    @ManyToMany(mappedBy = "estudios")
    private List<Usuario> arquitectos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagen_id")
    private Imagen imagen;
}