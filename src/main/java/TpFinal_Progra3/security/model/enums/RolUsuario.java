package TpFinal_Progra3.security.model.enums;

public enum RolUsuario {
    ROLE_ADMINISTRADOR,
        //puede cargar obras y gestionar solicitudes de usuarios
    ROLE_ARQUITECTO,
        //puede cargar sus propias obras y dar de alta un estudio de arquitectura
    ROLE_USUARIO
        //solo puede visualizar y agregar a favoritos las obras
}
