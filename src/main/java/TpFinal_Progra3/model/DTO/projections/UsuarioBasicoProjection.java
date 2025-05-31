package TpFinal_Progra3.model.DTO.projections;

public interface UsuarioBasicoProjection {
    Long getId();
    String getEmail();
    String getNombre();
    String getApellido();
    Boolean getIsActivo();
}
