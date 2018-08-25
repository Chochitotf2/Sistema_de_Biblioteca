package controlador.servicio;

import controlador.dao.DocumentoDao;
import java.util.List;
import modelo.Documento;

/**
 *
 * @author Chochitotf2
 */
public class DocumentoServicio {

    private DocumentoDao documento = new DocumentoDao();

    public Documento obtenerDocumento() {
        return documento.getDocumento();
    }

    public Documento obtenerDocumento(Long id) {
        return documento.obtener(id);
    }

    public void fijarDocumento(Documento documento) {
        this.documento.setDocumento(documento);
    }

    public boolean guardar() {
        return documento.guardar();
    }

    public List<Documento> listar() {
        return documento.listar();
    }

    public List<Documento> listarDocumentoTipo(String tipo) {
        return documento.listarDocumentoTipo(tipo);
    }

    public List<Documento> listarDocumentoLike(String busqueda) {
        return documento.listarDocumentoLike(busqueda);
    }

    public List<Documento> listarDocumentoTipoLike(String tipo, String busqueda) {
        return documento.listarDocumentoTipoLike(tipo, busqueda);
    }
}
