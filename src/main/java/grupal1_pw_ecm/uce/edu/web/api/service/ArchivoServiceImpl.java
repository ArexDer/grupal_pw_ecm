package grupal1_pw_ecm.uce.edu.web.api.service;

import grupal1_pw_ecm.uce.edu.web.api.repository.IArchivoRepository;
import grupal1_pw_ecm.uce.edu.web.api.repository.modelo.Archivo;
import grupal1_pw_ecm.uce.edu.web.api.service.to.ArchivoTo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ArchivoServiceImpl implements IArchivoService {

    @Inject
    private IArchivoRepository archivoRepository;

    @Override
    public void guardar(ArchivoTo archivoTo) {
        Archivo archivo = convertir(archivoTo);
        archivoRepository.insertar(archivo);
    }

    @Override
    public ArchivoTo buscar(int id) {
        Archivo archivo = archivoRepository.seleccionar(id);
        if (archivo == null) {
            return null; // Manejo de error: se podría lanzar una excepción personalizada
        }
        return convertirTO(archivo);
    }

    @Override
    public ArchivoTo buscarNombre(String nombre) {
        Archivo archivo = archivoRepository.seleccionarNombre(nombre);
        if (archivo == null) {
            return null; // Manejo de error: se podría lanzar una excepción personalizada
        }
        return convertirTO(archivo);
    }

    // Métodos privados para convertir entre Archivo y ArchivoTO
    private Archivo convertir(ArchivoTo archivoTO) {
        Archivo archivo = new Archivo();
        archivo.setNombre(archivoTO.getNombre());
        archivo.setTipo(archivoTO.getTipo());
        archivo.setContenido(archivoTO.getContenido());
        return archivo;
    }

    private ArchivoTo convertirTO(Archivo archivo) {
        ArchivoTo archivoTO = new ArchivoTo();
        archivoTO.setId(archivo.getId());
        archivoTO.setNombre(archivo.getNombre());
        archivoTO.setTipo(archivo.getTipo());
        archivoTO.setContenido(archivo.getContenido());
        return archivoTO;
    }
}
