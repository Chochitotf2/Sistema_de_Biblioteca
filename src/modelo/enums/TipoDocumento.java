package modelo.enums;

/**
 *
 * @author MAYLI
 */
public enum TipoDocumento {
    Libros("Libros"),
    Revistas("Revistas"),
    Documentos_no_Convencionales("Documentos no convencionales"),
    Tesis("Tesis");

    String tipo;

    private TipoDocumento(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
