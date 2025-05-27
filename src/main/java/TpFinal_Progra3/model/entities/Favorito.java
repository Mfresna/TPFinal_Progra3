package TpFinal_Progra3.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "favoritos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "El nombre de la lista no puede estar vacio")
    //@Size(max = 100)
    private String nombreLista;

    @ManyToMany
    @JoinTable(
            name = "obras_favorito",
            joinColumns = @JoinColumn(name = "favorito_id"),
            inverseJoinColumns = @JoinColumn(name = "obra_id")
    )
    private List<Obra> obras;

    private LocalDateTime fechaCreacion;
}
