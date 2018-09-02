package controlador.dao;

import java.util.List;

/**
 * Esta interfaz se compone de la estructura para realizar las operaciones
 * primordiales entre el sistema y su respectiva Base de datos de uso.
 *
 * @author Víctor Andrés Rojas
 * @author Mario Orellana
 * @param <T> La Interfaz es de tipo Genérico.
 */
public interface InterfazDao<T> {

    /**
     * Método que sirve para guardar un objeto de tipo T en la respectiva Base
     * de Datos.
     *
     * @param obj Instancia a ser persistida o guardada.
     * @throws Exception Este método puede lanzar una Excepción si la entidad ya
     * existe.
     */
    public void guardar(T obj) throws Exception;

    /**
     * Método que sirve para modificar un objeto de tipo T de la respectiva Base
     * de Datos.
     *
     * @param obj Instancia a ser modificada.
     * @throws Exception Este método puede lanzar una Excepción si: la instancia
     * no es una entidad o es una entidad eliminada.
     */
    public void modificar(T obj) throws Exception;

    /**
     * Método que lista un conjunto de objetos de tipo T de la respectiva Base
     * de Datos.
     *
     * @return Devuelve un listado de objetos de tipo T.
     */
    public List<T> listar();

    /**
     * Método que extrae un objeto de tipo T de la respectiva Base de Datos.
     *
     * @param id Clave primaria que hace referencia al objeto a ser obtenido.
     * @return Devuelve un objeto mediante su clave primaria.
     */
    public T obtener(Long id);
}
