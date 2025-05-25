package TpFinal_Progra3.entities;

import TpFinal_Progra3.enums.RolUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol;

    private String descripcion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagen_id") // FK en la tabla Usuario
    private Imagen imagen;

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;

    private Boolean isActivo;

    @ManyToMany
    @JoinTable(
            name = "usuarios_estudio",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "estudio_id")
    )
    private List<EstudioArquitectura> estudios; //Un usr puede pertenecer a muchos estudios y un estudio tener muchos usr

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "usuario_id")
    private List<Favorito> listaFavoritos;
}