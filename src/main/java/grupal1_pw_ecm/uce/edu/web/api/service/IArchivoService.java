package grupal1_pw_ecm.uce.edu.web.api.service;

import java.util.List;

import grupal1_pw_ecm.uce.edu.web.api.repository.modelo.Archivo;
import grupal1_pw_ecm.uce.edu.web.api.service.to.ArchivoTo;
import jakarta.persistence.criteria.CriteriaBuilder.In;

public interface IArchivoService {
    
     public void guardar(ArchivoTo archivo);

    public ArchivoTo buscar(int id);

    public ArchivoTo buscarNombre(String nombre);

    public void borrar(Integer id);

    public List<ArchivoTo> buscarTodos();
    public List<ArchivoTo> buscarIdCarpeta(Integer id);
    

}
