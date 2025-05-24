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
public class EstudioArquitectura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "estudio")
    private List<Obra> obras;

    @ManyToMany
    private List<Usuario> arquitectos;

    @OneToMany(mappedBy = "estudio", cascade = CascadeType.ALL)
    private List<Imagen> imagenes;

}
