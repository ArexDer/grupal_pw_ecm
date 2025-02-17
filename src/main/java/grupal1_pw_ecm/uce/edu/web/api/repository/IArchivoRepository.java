package grupal1_pw_ecm.uce.edu.web.api.repository;

import java.util.List;

import grupal1_pw_ecm.uce.edu.web.api.repository.modelo.Archivo;
import jakarta.persistence.criteria.CriteriaBuilder.In;

public interface IArchivoRepository {

    public void insertar(Archivo archivo);

    public Archivo seleccionar(int id);

    public Archivo seleccionarNombre(String nombre);

    public List<Archivo> seleccionarTodos();

    public void eliminar(Integer id);

    public List<Archivo> seleccionarPorCarpeta(Integer carpetaId);

    
    
}
