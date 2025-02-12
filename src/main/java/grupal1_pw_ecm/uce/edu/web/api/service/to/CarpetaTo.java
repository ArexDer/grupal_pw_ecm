package grupal1_pw_ecm.uce.edu.web.api.service.to;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarpetaTo implements Serializable {

     @JsonProperty("id")
    private Integer id;
    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("carpeta_padre_id")
    private Integer carpetaPadreId; // ID de la carpeta padre
    @JsonProperty("subcarpetas")
    private List<CarpetaTo> subcarpetas; // Lista de subcarpetas

    private static final long serialVersionUID = -1544399202104638172L;

    public CarpetaTo() {
    }

    public CarpetaTo(Integer id, String nombre, Integer carpetaPadreId, List<CarpetaTo> subcarpetas) {
        this.id = id;
        this.nombre = nombre;
        this.carpetaPadreId = carpetaPadreId;
        this.subcarpetas = subcarpetas;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCarpetaPadreId() {
        return carpetaPadreId;
    }

    public void setCarpetaPadreId(Integer carpetaPadreId) {
        this.carpetaPadreId = carpetaPadreId;
    }

    public List<CarpetaTo> getSubcarpetas() {
        return subcarpetas;
    }

    public void setSubcarpetas(List<CarpetaTo> subcarpetas) {
        this.subcarpetas = subcarpetas;
    }
}