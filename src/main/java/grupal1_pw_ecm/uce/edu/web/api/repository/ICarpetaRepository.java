package grupal1_pw_ecm.uce.edu.web.api.repository;

import java.util.List;

import grupal1_pw_ecm.uce.edu.web.api.repository.modelo.Carpeta;

public interface ICarpetaRepository {
    public Carpeta buscarPorId(Integer id);

    public List<Carpeta> buscarTodos();
    
    public void insertar(Carpeta carpeta);

    public void actualizar(Carpeta carpeta);

    public void eliminar(Integer id);

    public Carpeta buscarNombre(String nombre);
}