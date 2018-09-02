package controlador.dao;

import controlador.Conexion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Esta clase Adaptadora se compone de los métodos necesarios para realizar las
 * operaciones primordiales entre el sistema y su respectiva Base de datos de
 * uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 * @param <T> La clase es de tipo Genérico.
 */
public class AdaptadorDao<T> implements InterfazDao<T> {

    private Class clazz;

    /**
     * Constructor que asigna la Clase a usarse para realizar las distintas
     * operaciones del AdaptadorDao.
     *
     * @param clazz Clase a usarse en el AdaptadorDao.
     */
    public AdaptadorDao(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * Método que sirve obtener un EntityManager, útil para gestionar la entidad
     * u objeto.
     *
     * @return Devuelve una instancia EntityManager.
     */
    public EntityManager getManager() {
        return Conexion.getManager();
    }

    /**
     * Método que lista un conjunto de objetos de tipo T. El listado de objetos
     * se realiza mediante el uso de una consulta(Query). Los objetos listados
     * son de la clase asignada en el constructor del AdaptadorDao.
     *
     * @return Devuelve un listado de objetos de la clase asignada en el
     * constructor del AdaptadorDao.
     */
    @Override
    public List<T> listar() {
        List<T> lista = new ArrayList<>();
        try {
            Query query = getManager().createQuery("SELECT a FROM " + clazz.getSimpleName() + " a");
            lista = query.getResultList();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Listar los datos: " + e);
        }
        return lista;
    }

    /**
     * Método que sirve para guardar un objeto de tipo T en la respectiva Base
     * de Datos. Mediante el uso del método persist se podrá almacenar nuevas
     * entidades en la base de datos.
     *
     * @param obj Instancia a ser persistida o guardada.
     * @throws Exception Este método puede lanzar una Excepción si la entidad ya
     * existe.
     */
    @Override
    public void guardar(T obj) throws Exception {
        getManager().persist(obj);
    }

    /**
     * Método que sirve para modificar un objeto de tipo T de la respectiva Base
     * de Datos. Mediante el uso del método merge se podrá modificar una entidad
     * de la base de datos.
     *
     * @param obj Instancia a ser modificada.
     * @throws Exception Este método puede lanzar una Excepción si: la instancia
     * no es una entidad o es una entidad eliminada.
     */
    @Override
    public void modificar(T obj) throws Exception {
        getManager().merge(obj);
    }

    /**
     * Método que extrae un objeto de tipo T. El objeto obtenido se realiza
     * mediante el uso del método find, por el cual se podrá buscar y devolver
     * dicho objeto de la base de datos.
     *
     * @param id Clave primaria que hace referencia al objeto a ser obtenido.
     * @return Devuelve un objeto mediante su clave primaria de la clase
     * asignada en el constructor del AdaptadorDao.
     */
    @Override
    public T obtener(Long id) {
        T obj = null;
        try {
            obj = (T) getManager().find(clazz, id);
        } catch (Exception e) {
            System.out.println("No se ha podido encontrar el Identificador id: " + e);
        }
        return obj;
    }
}
