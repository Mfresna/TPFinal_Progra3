package TpFinal_Progra3.security.model.enums;

public enum RolUsuario {
    ROL_ADMINISTRADOR,
        //puede cargar obras y gestionar solicitudes de usuarios
    ROL_ARQUITECTO,
        //puede cargar sus propias obras y dar de alta un estudio de arquitectura
    ROL_USUARIO
        //solo puede visualizar y agregar a favoritos las obras
}
