package TpFinal_Progra3.model.enums;

import lombok.Getter;

@Getter
public enum CategoriaObra {

    ARQ_RESIDENCIAL("Arquitectura Residencial"),
    ARQ_COMERCIAL("Arquitectura Comercial"),
    ARQ_EDUCACIONAL("Arquitectura Educacional"),
    ARQ_CULTURAL("Arquitectura Cultural"),
    ARQ_SALUD("Arquitectura de Salud"),
    ARQ_PUBLICA_INSTIT("Arquitectura Pública e Institucional"),
    ARQ_INDUSTRIAL("Arquitectura Industrial"),
    ARQ_DEPORTIVA("Arquitectura Deportiva"),
    ARQ_RELIGIOSA("Arquitectura Religiosa"),
    PAISAJISMO_URBANISMO("Paisajismo y Urbanismo"),
    ARQ_PAT_RESTAUR("Arquitectura Patrimonial y de Restauración"),
    ARQ_TEMPORARIA("Arquitectura Temporaria (Pabellones, Instalaciones, Stands)");

    private final String descripcion;

    CategoriaObra(String descripcion) {
            this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
