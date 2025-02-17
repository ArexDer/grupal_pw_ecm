package grupal1_pw_ecm.uce.edu.web.api.service;

import java.util.List;
import java.util.function.Function;

import grupal1_pw_ecm.uce.edu.web.api.repository.IArchivoRepository;
import grupal1_pw_ecm.uce.edu.web.api.repository.ICarpetaRepository;
import grupal1_pw_ecm.uce.edu.web.api.repository.modelo.Archivo;
import grupal1_pw_ecm.uce.edu.web.api.repository.modelo.Carpeta;
import grupal1_pw_ecm.uce.edu.web.api.service.to.ArchivoTo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ArchivoServiceImpl implements IArchivoService {

    @Inject
    private IArchivoRepository archivoRepository;

    @Inject
    private ICarpetaRepository carpetaRepository;

    @Override
    @Transactional
    public void guardar(ArchivoTo archivoTo) {
        Archivo archivo = convertir(archivoTo);
        archivoRepository.insertar(archivo);
    }

    @Override
    @Transactional
    public ArchivoTo buscar(int id) {
        Archivo archivo = archivoRepository.seleccionar(id);
        if (archivo == null) {
            return null; // Manejo de error: se podría lanzar una excepción personalizada
        }
        return convertirTO(archivo);
    }

    @Override
    @Transactional
    public ArchivoTo buscarNombre(String nombre) {
        Archivo archivo = archivoRepository.seleccionarNombre(nombre);
        if (archivo == null) {
            return null; // Manejo de error: se podría lanzar una excepción personalizada
        }
        return convertirTO(archivo);
    }

    private Archivo convertir(ArchivoTo archivoTO) {
        Archivo archivo = new Archivo();
        archivo.setNombre(archivoTO.getNombre());
        archivo.setTipo(archivoTO.getTipo());
        archivo.setContenido(archivoTO.getContenido());
    
        if (archivoTO.getCarpetaId() != null) {
            Carpeta carpeta = this.carpetaRepository.buscarPorId(archivoTO.getCarpetaId());
            archivo.setCarpeta(carpeta);
        }
    
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

    @Override
    @Transactional
    public void borrar(Integer id) {
        this.archivoRepository.eliminar(id);
    }

    Function<List<Archivo>, List<ArchivoTo>> convertirTOs = (archivos) -> {
        return archivos.stream().map(archivo -> convertirTO(archivo)).toList();
    };

    @Override
    @Transactional
    public List<ArchivoTo> buscarTodos() {
        return convertirTOs.apply(this.archivoRepository.seleccionarTodos());
    }

    @Override
    public List<ArchivoTo> buscarIdCarpeta(Integer id) {

        return convertirTOs.apply(this.archivoRepository.buscarIdCarpeta(id));
    }

}
