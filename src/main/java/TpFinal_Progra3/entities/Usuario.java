package TpFinal_Progra3.entities;

import TpFinal_Progra3.enums.RolUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private RolUsuario rol;

    private String descripcion;

    @OneToOne(cascade = CascadeType.ALL)
    private Imagen imagen;

    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;

    private Boolean isActivo;
}