package controlador.dao;

import it.sauronsoftware.base64.Base64;
import javax.persistence.Query;
import modelo.Cuenta;

/**
 * Clase mediante la cual se realiza las operaciones que sean requeridas (A
 * través del AdaptadorDao) entre un objeto Cuenta y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class CuentaDao extends AdaptadorDao<Cuenta> {

    private Cuenta cuenta;

    /**
     * Constructor por defecto que asigna la clase Cuenta al AdaptadorDao, para
     * asi poder realizar las operaciones que este contiene en sus métodos.
     */
    public CuentaDao() {
        super(Cuenta.class);
    }

    /**
     * Método que obtiene una instancia de Cuenta. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Cuenta.
     */
    public Cuenta getCuenta() {
        if (cuenta == null) {
            cuenta = new Cuenta();
        }
        return cuenta;
    }

    /**
     * Método que asigna un objeto de tipo Cuenta al campo de esta clase que es
     * del mismo tipo.
     *
     * @param cuenta Instancia Cuenta a asignarse.
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Método que guarda o modifica un objeto de tipo Cuenta en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (cuenta.getId() != null) {
                modificar(cuenta);
            } else {
                guardar(cuenta);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Cuenta: " + e);
        }
        return estado;
    }

    /**
     * Método que obtiene un objeto Cuenta que contenga en su atributo 'usuario'
     * el mismo valor que se le indica en el parámetro del método.
     *
     * @param usuario Valor a ser encontrado dentro de la consulta.
     * @return Devuelve un objeto Cuenta en caso de ser encontrado.
     */
    public Cuenta obtenerUsuarioCuenta(String usuario) {
        Cuenta cuentaAux = null;
        try {
            Query query = getManager().createQuery("SELECT p FROM Cuenta p where p.usuario = :dato");
            query.setParameter("dato", usuario);
            cuentaAux = (Cuenta) query.getSingleResult();
        } catch (Exception e) {
        }
        return cuentaAux;
    }

    /**
     * Método que obtiene un objeto Cuenta que contenga en sus atributos
     * 'usuario' y 'clave' el mismo valor que se le indica en los parámetros del
     * método con el fin de lograr un inicio de sesión válido.
     *
     * @param usuario Valor a ser encontrado dentro de la consulta.
     * @param clave Valor a ser encontrado dentro de la consulta.
     * @return Devuelve un objeto Cuenta en caso de ser encontrado.
     */
    public Cuenta inicioSesion(String usuario, String clave) {
        Cuenta cuentaAux = null;
        try {
            Query query = getManager().createQuery("SELECT c FROM Cuenta c WHERE c.usuario = :user");
            query.setParameter("user", usuario);
            Cuenta aux = (Cuenta) query.getSingleResult();
            if (aux != null && aux.getClave().equals(Base64.encode(clave))) {
                cuentaAux = aux;
            }
        } catch (Exception e) {
            System.out.println("No se ha podido Iniciar Sesión: " + e);
        }
        return cuentaAux;
    }
}
