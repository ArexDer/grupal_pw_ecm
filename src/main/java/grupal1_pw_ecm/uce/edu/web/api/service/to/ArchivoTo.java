package grupal1_pw_ecm.uce.edu.web.api.service.to;

import jakarta.ws.rs.core.Link; 

public class ArchivoTo {
    private Integer id;
    private String nombre;
    private String tipo;
    private byte[] contenido;
    private Integer carpetaId; 

    private Link link;

    public ArchivoTo() {
    }

    public ArchivoTo(Integer id,String nombre, String tipo, byte[] contenido, Integer carpetaId) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.contenido = contenido;
        this.carpetaId = carpetaId;
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

    public Integer getCarpetaId() {
        return carpetaId;
    }

    public void setCarpetaId(Integer carpetaId) {
        this.carpetaId = carpetaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {

        this.link = link;

    }

    @Override
    public String toString() {
        return "ArchivoTo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", link=" + link +
                '}';
    }
    
}
