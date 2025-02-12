package grupal1_pw_ecm.uce.edu.web.api.repository.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "archivo")
public class Archivo {

    @Id
    @GeneratedValue(generator = "seq_archivo", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq_archivo", sequenceName = "seq_archivo", allocationSize = 1)
    @Column(name = "arch_id")
    private Integer id;

    @Column(name = "arch_nombre")
    private String nombre;

    @Column(name = "arch_tipo")
    private String tipo;

    @Lob
    @Column(name = "arch_contenido")
    private byte[] contenido;

    @ManyToOne
    @JoinColumn(name = "carp_id")
    private Carpeta carpeta;

    

    public Archivo() {
    }

    

    public Archivo(Integer id, String nombre, String tipo, byte[] contenido, Carpeta carpeta) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.contenido = contenido;
        this.carpeta = carpeta;
    }



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

    public Carpeta getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(Carpeta carpeta) {
        this.carpeta = carpeta;
    }

    
    
}
