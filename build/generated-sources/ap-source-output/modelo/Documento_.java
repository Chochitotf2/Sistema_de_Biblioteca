package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.enums.TipoDocumento;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Documento.class)
public abstract class Documento_ {

	public static volatile SingularAttribute<Documento, TipoDocumento> tipoDocumento;
	public static volatile SingularAttribute<Documento, String> codigo;
	public static volatile SingularAttribute<Documento, Boolean> estado;
	public static volatile SingularAttribute<Documento, Long> id;
	public static volatile SingularAttribute<Documento, String> Titulo;
	public static volatile ListAttribute<Documento, Prestamo> listaPrestamo;

}

