package TpFinal_Progra3.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank
    private String nombre;
    private String tipo; // Puede ser JPG, PNG, etc.

    @Column(unique = true, nullable = false)
    //@NotBlank
    private String url;
}