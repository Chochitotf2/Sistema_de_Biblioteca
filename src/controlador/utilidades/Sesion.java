package controlador.utilidades;

import modelo.Cuenta;

/**
 *
 * @author Víctor Andrés Rojas
 */
public class Sesion {

    private static Cuenta cuenta;

    public static Cuenta getCuenta() {
        return cuenta;
    }

    public static void setCuenta(Cuenta cuenta) {
        Sesion.cuenta = cuenta;
    }
}
