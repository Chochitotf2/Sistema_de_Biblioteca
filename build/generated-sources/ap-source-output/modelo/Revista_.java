package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Revista.class)
public abstract class Revista_ extends modelo.Documento_ {

	public static volatile SingularAttribute<Revista, String> editorial;
	public static volatile SingularAttribute<Revista, String> issn;
	public static volatile SingularAttribute<Revista, Date> fechaPublicacion;

}

