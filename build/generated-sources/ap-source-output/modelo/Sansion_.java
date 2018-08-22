package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sansion.class)
public abstract class Sansion_ {

	public static volatile SingularAttribute<Sansion, Prestamo> prestamo;
	public static volatile SingularAttribute<Sansion, Double> monto;
	public static volatile SingularAttribute<Sansion, Date> fechaInicio;
	public static volatile SingularAttribute<Sansion, Long> id;
	public static volatile SingularAttribute<Sansion, Date> fechaFin;

}

