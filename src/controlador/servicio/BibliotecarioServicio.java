package controlador.servicio;

import controlador.dao.BibliotecarioDao;
import java.util.List;
import modelo.Bibliotecario;

/**
 * Clase que mediante una instancia de BibliotecarioDao, obtiene todos los
 * métodos de la misma y junto a ello los del AdaptadorDao, de esta manera una
 * instancia de BibliotecarioServicio bastará para realizar todas las
 * operaciones entre un objeto Bibliotecario y la Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 */
public class BibliotecarioServicio {

    private BibliotecarioDao bibliotecario = new BibliotecarioDao();

    /**
     * Método que obtiene una instancia de Bibliotecario. En caso de no existir
     * se creará un nuevo objeto.
     *
     * @return Devuelve un objeto de tipo Bibliotecario.
     */
    public Bibliotecario obtenerBibliotecario() {
        return bibliotecario.getBibliotecario();
    }

    /**
     * Método que obtiene una instancia de Bibliotecario mediante un
     * identificador o clave primaria.
     *
     * @param id Valor del identificador o clave primaria.
     * @return Devuelve un objeto de tipo Bibliotecario.
     */
    public Bibliotecario obtenerBibliotecario(Long id) {
        return bibliotecario.obtener(id);
    }

    /**
     * Método que asigna un objeto de tipo Bibliotecario al campo de la clase
     * BibliotecarioDao que es del mismo tipo.
     *
     * @param bibliotecario Instancia Bibliotecario a asignarse.
     */
    public void fijarBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario.setBibliotecario(bibliotecario);
    }

    /**
     * Método que guarda o modifica un objeto de tipo Bibliotecario en la
     * respectiva Base de datos de uso.
     *
     * @return Devuelve un resultado Booleano, en caso de ser verdadero quiere
     * decir que la operación se realizó correctamente.
     */
    public boolean guardar() {
        return bibliotecario.guardar();
    }

    /**
     * Método que realiza una consulta en la Base de datos y devuelve un listado
     * de objetos Bibliotecario.
     *
     * @return Devuelve una lista de Tipo Bibliotecario.
     */
    public List<Bibliotecario> listar() {
        return bibliotecario.listar();
    }

    /**
     * Método que retorna una Lista de objetos de Tipo Bibliotecario. Esta lista
     * se obtiene a través de una consulta (Query) tomando como referencia un
     * String busqueda.
     *
     * @param busqueda Palabra clave a ser encontrada.
     * @return Devuelve una lista de objetos de tipo Bibliotecario.
     */
    public List<Bibliotecario> listarBibliotecarioLike(String busqueda) {
        return bibliotecario.listarBibliotecarioLike(busqueda);
    }

    /**
     * Método que obtiene un objeto de tipo Bibliotecario que contenga en su
     * atributo 'cedula' el mismo valor que se le indica en el parámetro del
     * método.
     *
     * @param cedula Valor a ser encontrado dentro de la consulta.
     * @return Devuelve un objeto Bibliotecario en caso de ser encontrado.
     */
    public Bibliotecario obtenerBibliotecarioCedula(String cedula) {
        return bibliotecario.obtenerBibliotecarioCedula(cedula);
    }
}
