package grupal1_pw_ecm.uce.edu.web.api.service.to;

import java.io.Serializable;
import java.util.List;

public class CarpetaTo implements Serializable {
    private Integer id;
    private String nombre;
    private Integer carpetaPadreId; // ID de la carpeta padre
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

    // GETTERS Y SETTERS
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
