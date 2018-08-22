package controlador.servicio;

import controlador.dao.CuentaDao;
import java.util.Date;
import java.util.List;
import modelo.Cuenta;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class CuentaServicio {

    private CuentaDao cuenta = new CuentaDao();

    public Cuenta obtenerCuenta() {
        return cuenta.getCuenta();
    }

    public Cuenta obtenerCuenta(Long id) {
        return cuenta.obtener(id);
    }

    public void fijarCuenta(Cuenta cuenta) {
        this.cuenta.setCuenta(cuenta);
    }

    public boolean guardar() {
        return cuenta.guardar();
    }

    public List<Cuenta> listar() {
        return cuenta.listar();
    }

    public Cuenta inicioSesion(String usuario, String clave) {
        return cuenta.inicioSesion(usuario, clave);
    }

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
            administrador.setClave("administrador");
            administrador.setCreadoEn(new Date());
            administrador.setModificadoEn(new Date());
            administrador.setPersona(persona.obtenerPersona());
            persona.obtenerPersona().setCuenta(administrador);
            persona.guardar();
        }
    }

    public void crearCuentaBibliotecario() {
        PersonaServicio persona = new PersonaServicio();
        persona.obtenerPersona().setNombres("Bibliotecario");
        persona.obtenerPersona().setApellidos("Bibliotecario");
        persona.obtenerPersona().setCorreo("bibliotecario@correo.com");
        persona.obtenerPersona().setDni("DNI");
        persona.obtenerPersona().setDireccion("Dirección");
        persona.obtenerPersona().setTelefono("Teléfono");
        persona.obtenerPersona().setRol(new RolServicio().buscarRol("Bibliotecario"));
        Cuenta bibliotecario = new Cuenta();
        bibliotecario.setUsuario("bibliotecario");
        bibliotecario.setClave("bibliotecario");
        bibliotecario.setCreadoEn(new Date());
        bibliotecario.setModificadoEn(new Date());
        bibliotecario.setPersona(persona.obtenerPersona());
        persona.obtenerPersona().setCuenta(bibliotecario);
        persona.guardar();
    }
}
