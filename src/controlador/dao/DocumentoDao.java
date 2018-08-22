package controlador.dao;

import modelo.Documento;

/**
 *
 * @author Chochitotf2
 */
public class DocumentoDao extends AdaptadorDao<Documento> {

    private Documento documento;

    public DocumentoDao() {
        super(Documento.class);
    }

    public Documento getDocumento() {
        if (documento == null) {
            documento = new Documento();
        }
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public boolean guardar() {
        boolean estado = false;
        try {
            getManager().getTransaction().begin();
            if (documento.getId() != null) {
                modificar(documento);
            } else {
                guardar(documento);
            }
            getManager().getTransaction().commit();
            estado = true;
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al Guardar o Modificar Documento: " + e);
        }
        return estado;
    }
}