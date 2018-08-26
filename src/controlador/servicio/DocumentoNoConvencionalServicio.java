package controlador.servicio;

import controlador.dao.DocumentoNoConvencionalDao;
import java.util.List;
import modelo.DocumentoNoConvencional;

/**
 *
 * @author Chochitotf2
 */
public class DocumentoNoConvencionalServicio {

    private DocumentoNoConvencionalDao documentoNoConvencional = new DocumentoNoConvencionalDao();

    public DocumentoNoConvencional obtenerDocumentoNoConvencional() {
        return documentoNoConvencional.getDocumentoNoConvencional();
    }

    public DocumentoNoConvencional obtenerDocumentoNoConvencional(Long id) {
        return documentoNoConvencional.obtener(id);
    }

    public void fijarDocumentoNoConvencional(DocumentoNoConvencional documentoNoConvencional) {
        this.documentoNoConvencional.setDocumentoNoConvencional(documentoNoConvencional);
    }

    public boolean guardar() {
        return documentoNoConvencional.guardar();
    }

    public List<DocumentoNoConvencional> listar() {
        return documentoNoConvencional.listar();
    }

    public List<DocumentoNoConvencional> listarDocumentoNoConvencionalLike(String busqueda) {
        return documentoNoConvencional.listarDocumentoNoConvencionalLike(busqueda);
    }
}
