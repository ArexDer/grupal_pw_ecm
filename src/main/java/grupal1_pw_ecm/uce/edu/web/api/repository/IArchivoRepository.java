package grupal1_pw_ecm.uce.edu.web.api.repository;

import grupal1_pw_ecm.uce.edu.web.api.repository.modelo.Archivo;

public interface IArchivoRepository {

    public void insertar(Archivo archivo);

    public Archivo seleccionar(int id);

    public Archivo seleccionarNombre(String nombre);

    
    
}
