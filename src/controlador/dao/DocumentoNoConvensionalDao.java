package controlador.dao;

import modelo.DocumentoNoConvencional;

/**
 *
 * @author Chochitotf2
 */
public class DocumentoNoConvensionalDao extends AdaptadorDao<DocumentoNoConvencional> {

    private DocumentoNoConvencional documentoNoConvensional;

    public DocumentoNoConvensionalDao() {
        super(DocumentoNoConvencional.class);
    }

    public DocumentoNoConvencional getDocumentoNoConvensional() {
        if (documentoNoConvensional == null) {
            documentoNoConvensional = new DocumentoNoConvencional();
        }
        return documentoNoConvensional;
    }

    public void setDocumentoNoConvensional(DocumentoNoConvencional documentoNoConvensional) {
        this.documentoNoConvensional = documentoNoConvensional;
    }

    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (documentoNoConvensional.getId() != null) {
                modificar(documentoNoConvensional);
            } else {
                guardar(documentoNoConvensional);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar DocumentoNoConvensional: " + e);
        }
        return estado;
    }
}
