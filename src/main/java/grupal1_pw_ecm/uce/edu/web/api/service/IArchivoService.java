package grupal1_pw_ecm.uce.edu.web.api.service;

import grupal1_pw_ecm.uce.edu.web.api.service.to.ArchivoTo;

public interface IArchivoService {
    
     public void guardar(ArchivoTo archivo);

    public ArchivoTo buscar(int id);

    public ArchivoTo buscarNombre(String nombre);

}
