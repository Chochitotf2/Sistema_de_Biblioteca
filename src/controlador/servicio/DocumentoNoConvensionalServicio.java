package controlador.servicio;

import controlador.dao.DocumentoNoConvensionalDao;
import java.util.List;
import modelo.DocumentoNoConvencional;

/**
 *
 * @author Chochitotf2
 */
public class DocumentoNoConvensionalServicio {

    private DocumentoNoConvensionalDao documentoNoConvensional = new DocumentoNoConvensionalDao();

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
