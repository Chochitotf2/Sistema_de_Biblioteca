package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tesis.class)
public abstract class Tesis_ extends modelo.Documento_ {

	public static volatile SingularAttribute<Tesis, String> asesores;
	public static volatile SingularAttribute<Tesis, String> autores;
	public static volatile SingularAttribute<Tesis, Date> fechaPublicacion;
	public static volatile SingularAttribute<Tesis, String> facultad;

}

