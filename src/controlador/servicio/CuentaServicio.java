package controlador.servicio;

import controlador.dao.CuentaDao;
import it.sauronsoftware.base64.Base64;
import java.util.Date;
import java.util.List;
import modelo.Cuenta;

/**
 * Clase que mediante una instancia de CuentaDao, obtiene todos los métodos de
 * la misma y junto a ello los del AdaptadorDao, de esta manera una instancia de
 * CuentaServicio bastará para realizar todas las operaciones entre un objeto
 * Cuenta y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class CuentaServicio {

    private CuentaDao cuenta = new CuentaDao();

    /**
     * Método que obtiene una instancia de Cuenta. En caso de no existir se
     * creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Cuenta.
     */
    public Cuenta obtenerCuenta() {
        return cuenta.getCuenta();
    }

    /**
     * Método que obtiene una instancia de Cuenta mediante un identificador o
     * clave primaria.
     *
     * @param id Valor del identificador o clave primaria.
     * @return Devuelve un objeto de tipo Cuenta.
     */
    public Cuenta obtenerCuenta(Long id) {
        return cuenta.obtener(id);
    }

    /**
     * Método que asigna un objeto de tipo Cuenta al campo de la clase CuentaDao
     * que es del mismo tipo.
     *
     * @param cuenta Instancia Cuenta a asignarse.
     */
    public void fijarCuenta(Cuenta cuenta) {
        this.cuenta.setCuenta(cuenta);
    }

    /**
     * Método que guarda o modifica un objeto de tipo Cuenta en la respectiva
     * Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        return cuenta.guardar();
    }

    /**
     * Método que realiza una consulta en la Base de datos y devuelve un listado
     * de objetos Cuenta.
     *
     * @return Devuelve una lista de Tipo Cuenta.
     */
    public List<Cuenta> listar() {
        return cuenta.listar();
    }

    /**
     * Método que obtiene un objeto Cuenta que contenga en su atributo 'usuario'
     * el mismo valor que se le indica en el parámetro del método.
     *
     * @param usuario Valor a ser encontrado dentro de la consulta.
     * @return Devuelve un objeto Cuenta en caso de ser encontrado.
     */
    public Cuenta obtenerUsuarioCuenta(String usuario) {
        return cuenta.obtenerUsuarioCuenta(usuario);
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
        return cuenta.inicioSesion(usuario, clave);
    }

    /**
     * Método necesario para poder crear una cuenta de Administración en el
     * sistema.
     */
    public void crearCuentaAdministrador() {
        if (listar().isEmpty()) {
            PersonaServicio persona = new PersonaServicio();
            persona.obtenerPersona().setNombres("Administrador");
            persona.obtenerPersona().setApellidos("Administrador");
            persona.obtenerPersona().setCorreo("administrador@correo.com");
            persona.obtenerPersona().setDni("DNI");
            persona.obtenerPersona().setDireccion("Dirección");
            persona.obtenerPersona().setTelefono("Teléfono");
            persona.obtenerPersona().setRol(new RolServicio().buscarRol("Administrador"));
            Cuenta administrador = new Cuenta();
            administrador.setUsuario("administrador");
            administrador.setClave(Base64.encode("administrador"));
            administrador.setCreadoEn(new Date());
            administrador.setModificadoEn(new Date());
            administrador.setPersona(persona.obtenerPersona());
            persona.obtenerPersona().setCuenta(administrador);
            persona.guardar();
        }
    }
}
