package grupal1_pw_ecm.uce.edu.web.api.service.to;

public class ArchivoTo {
    private Integer id;
    private String nombre;
    private String tipo;
    private byte[] contenido;
    private Long carpetaId; 

    public ArchivoTo() {
    }

    public ArchivoTo(Integer id,String nombre, String tipo, byte[] contenido, Long carpetaId) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.contenido = contenido;
        this.carpetaId = carpetaId;
    }

    
    public ArchivoTo(String nombre, String tipo, byte[] contenido) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.contenido = contenido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public Long getCarpetaId() {
        return carpetaId;
    }

    public void setCarpetaId(Long carpetaId) {
        this.carpetaId = carpetaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
}
