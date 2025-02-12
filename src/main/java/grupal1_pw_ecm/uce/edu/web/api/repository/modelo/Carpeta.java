package grupal1_pw_ecm.uce.edu.web.api.repository.modelo;

import java.util.List;
import jakarta.persistence.*;

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

    // Relación con el usuario (dueño de la carpeta)
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    // Relación con archivos dentro de la carpeta
    @OneToMany(mappedBy = "carpeta", cascade = CascadeType.ALL)
    private List<Archivo> archivos;

    // Autorreferencia: Carpeta padre
    @ManyToOne
    @JoinColumn(name = "carp_padre_id")
    private Carpeta carpetaPadre;

    // Lista de subcarpetas
    @OneToMany(mappedBy = "carpetaPadre", cascade = CascadeType.ALL)
    private List<Carpeta> subcarpetas;

    // Constructor vacío
    public Carpeta() {
    }

    // Constructor con parámetros
    public Carpeta(Integer id, String nombre, Carpeta carpetaPadre) {
        this.id = id;
        this.nombre = nombre;
        this.carpetaPadre = carpetaPadre;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }

    public Carpeta getCarpetaPadre() {
        return carpetaPadre;
    }

    public void setCarpetaPadre(Carpeta carpetaPadre) {
        this.carpetaPadre = carpetaPadre;
    }

    public List<Carpeta> getSubcarpetas() {
        return subcarpetas;
    }

    public void setSubcarpetas(List<Carpeta> subcarpetas) {
        this.subcarpetas = subcarpetas;
    }
}
