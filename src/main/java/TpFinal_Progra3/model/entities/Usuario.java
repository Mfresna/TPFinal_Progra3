package TpFinal_Progra3.model.entities;

import TpFinal_Progra3.security.model.entities.Credencial;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    //@Email(message = "El correo debe tener un formato válido")
    //@NotBlank(message = "El correo no puede estar vacío")
    //@Size(max = 100)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credencial_id", referencedColumnName = "id", nullable = false, unique = true)
    private Credencial credencial;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imagen_id")
    private Imagen imagen;

    @Column(nullable = false, length = 20)
    //@Size(min = 1, max = 20, message = "El nombre es demasiado largo")
    //@NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @Column(nullable = false, length = 35)
    //@NotBlank(message = "El apellido no puede estar vacio")
    private String apellido;

    @Column(nullable = false)
    //@Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    private LocalDate fechaNacimiento;

    @Builder.Default //Hace que activo se defina true si no se especifica en builder
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

    //Tengo que sacar las credenciales pq al printearlo entra en bucle
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", isActivo=" + isActivo +
                '}';
    }
}