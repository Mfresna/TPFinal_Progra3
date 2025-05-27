package TpFinal_Progra3.security.model.entities;

import TpFinal_Progra3.security.model.enums.RolUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RolUsuario rol;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "permisos_rol",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id"))
    private final Set<Permiso> permisos = new HashSet<>();

    //METODOS
    public void agregarPermiso(Permiso permiso) {
        this.permisos.add(permiso);
    }

}
