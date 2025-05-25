package TpFinal_Progra3.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo; // Puede ser JPG, PNG, etc.

    @Column(unique = true)
    private String url;
}