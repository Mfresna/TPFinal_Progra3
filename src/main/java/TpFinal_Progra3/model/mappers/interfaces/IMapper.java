package TpFinal_Progra3.model.mappers.interfaces;

public interface IMapper <T,R>{
    //NO UTILIZADA
    R mapTo(T t);
    T mapFrom(R r);
}
