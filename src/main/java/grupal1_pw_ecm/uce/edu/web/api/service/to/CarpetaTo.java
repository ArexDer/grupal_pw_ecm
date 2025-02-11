package grupal1_pw_ecm.uce.edu.web.api.service.to;

import java.io.Serializable;

public class CarpetaTo implements Serializable {
    private Integer id;
    private String nombre;
    private static final long serialVersionUID = -1544399202104638172L;

    public CarpetaTo() {
    }

    public CarpetaTo(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // SET Y GET
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

}
