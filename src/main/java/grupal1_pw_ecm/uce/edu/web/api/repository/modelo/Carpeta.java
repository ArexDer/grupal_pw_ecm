package grupal1_pw_ecm.uce.edu.web.api.repository.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "carpeta")
public class Carpeta {
    @Id
    @GeneratedValue(generator = "seq_carpeta", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_carpeta", sequenceName = "seq_carpeta", allocationSize = 1)
    @Column(name = "carp_id")
    private Integer id;
    @Column(name = "carp_nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    //@OneToMany(mappedBy = "carpeta", cascade = CascadeType.ALL)
    //private List<Archivo> archivos;

    public Carpeta() {
    }

    public Carpeta(Integer id, String nombre) {
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
