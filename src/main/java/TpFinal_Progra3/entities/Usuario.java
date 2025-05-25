package TpFinal_Progra3.entities;

import TpFinal_Progra3.enums.RolUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @Column(nullable = false, unique = true, length = 100)
    @Email(message = "El correo debe tener un formato válido")
    @NotBlank(message = "El correo no puede estar vacío")
    @Size(max = 100)
    private String email;

    @Column(nullable = false, length = 100)
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolUsuario rol;

    private String descripcion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagen_id") // FK en la tabla Usuario
    private Imagen imagen;

    @Column(nullable = false, length = 20)
    @Size(min = 1, max = 20, message = "El nombre es demasiado largo")
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    private String apellido;
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private Boolean isActivo = true;

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