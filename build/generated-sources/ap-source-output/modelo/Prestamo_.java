package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Prestamo.class)
public abstract class Prestamo_ {

	public static volatile SingularAttribute<Prestamo, String> tipoPrestamo;
	public static volatile SingularAttribute<Prestamo, Boolean> estado;
	public static volatile ListAttribute<Prestamo, Sansion> listaSansion;
	public static volatile SingularAttribute<Prestamo, Date> fechaDevolucion;
	public static volatile SingularAttribute<Prestamo, Date> fechaEntrega;
	public static volatile SingularAttribute<Prestamo, Documento> documento;
	public static volatile SingularAttribute<Prestamo, Long> id;

}

