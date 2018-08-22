package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Libro.class)
public abstract class Libro_ extends modelo.Documento_ {

	public static volatile SingularAttribute<Libro, String> editorial;
	public static volatile SingularAttribute<Libro, String> isbn;
	public static volatile SingularAttribute<Libro, String> autores;
	public static volatile SingularAttribute<Libro, String> anio;
	public static volatile SingularAttribute<Libro, String> edicion;

}

