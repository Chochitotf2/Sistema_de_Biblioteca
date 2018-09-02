package controlador.utilidades;

import modelo.Cuenta;

/**
 * Clase mediante la cual se realiza el manejo de sesiones del sistema
 * Bibliotecario.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class Sesion {

    private static Cuenta cuenta;

    /**
     * Método que obtiene la Cuenta de Persona que se encuentra iniciando
     * sesión.
     *
     * @return Devuelve un objeto de tipo Cuenta.
     */
    public static Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * Método que asigna una Cuenta para que la Persona pueda ingresar al
     * sistema.
     *
     * @param cuenta Datos de la cuenta para la Persona que solicita el inicio
     * de sesión.
     */
    public static void setCuenta(Cuenta cuenta) {
        Sesion.cuenta = cuenta;
    }
}
