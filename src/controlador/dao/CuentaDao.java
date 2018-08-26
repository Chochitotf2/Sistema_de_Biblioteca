package controlador.dao;

import it.sauronsoftware.base64.Base64;
import javax.persistence.Query;
import modelo.Cuenta;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class CuentaDao extends AdaptadorDao<Cuenta> {

    private Cuenta cuenta;

    public CuentaDao() {
        super(Cuenta.class);
    }

    public Cuenta getCuenta() {
        if (cuenta == null) {
            cuenta = new Cuenta();
        }
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

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
