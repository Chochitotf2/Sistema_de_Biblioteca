package controlador.servicio;

import controlador.dao.DocumentoNoConvencionalDao;
import java.util.List;
import modelo.DocumentoNoConvencional;

/**
 *
 * @author Chochitotf2
 */
public class DocumentoNoConvencionalServicio {

    private DocumentoNoConvencionalDao documentoNoConvensional = new DocumentoNoConvencionalDao();

    public DocumentoNoConvencional obtenerDocumentoNoConvensional() {
        return documentoNoConvensional.getDocumentoNoConvensional();
    }

    public DocumentoNoConvencional obtenerDocumentoNoConvensional(Long id) {
        return documentoNoConvensional.obtener(id);
    }

    public void fijarDocumentoNoConvensional(DocumentoNoConvencional documentoNoConvensional) {
        this.documentoNoConvensional.setDocumentoNoConvensional(documentoNoConvensional);
    }

    public boolean guardar() {
        return documentoNoConvensional.guardar();
    }

    public List<DocumentoNoConvencional> listar() {
        return documentoNoConvensional.listar();
    }
}
