/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Chochitotf2
 */
@Getter
@Setter
public class Conexion {

    //private static EntityManagerFactory emf;
    private static EntityManagerFactory emf;
    private static final String NAME_EMPU = "Sistema_de_BibliotecaPU";
    private static EntityManager em;

    public Conexion() {
        this.setup();

    }

    private static void setup() {
        if (em == null) {
            Conexion.emf = Persistence.createEntityManagerFactory("Sistema_de_BibliotecaPU");
            Conexion.em = Conexion.emf.createEntityManager();
        }
    }

    public static void main(String[] args) {
        new Conexion();
    }
}
