package TpFinal_Progra3.model.mappers.interfaces;

public interface IMapper <T,R>{
    R mapTo(T t);
    T mapFrom(R r);
}
