package grupal1_pw_ecm.uce.edu.web.api.service;

import java.util.List;
import java.util.function.Function;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import grupal1_pw_ecm.uce.edu.web.api.repository.ICarpetaRepository;
import grupal1_pw_ecm.uce.edu.web.api.repository.modelo.Carpeta;
import grupal1_pw_ecm.uce.edu.web.api.service.to.CarpetaTo;

@ApplicationScoped
public class CarpetaServiceImpl implements ICarpetaService {
    @Inject
    private ICarpetaRepository iCarpetaRepository;

    private Function<Carpeta, CarpetaTo> mapTo = c -> {
        List<CarpetaTo> subcarpetasTo = (c.getSubcarpetas() != null)
                ? c.getSubcarpetas().stream().map(this.mapTo).toList()
                : null;

        return new CarpetaTo(
                c.getId(),
                c.getNombre(),
                (c.getCarpetaPadre() != null) ? c.getCarpetaPadre().getId() : null, // ID de la carpeta padre
                subcarpetasTo // Lista de subcarpetas
        );
    };

    private Function<CarpetaTo, Carpeta> mapCarpeta = cTo -> {
        Carpeta carpeta = new Carpeta();
        carpeta.setId(cTo.getId());
        carpeta.setNombre(cTo.getNombre());

        // Si la carpeta padre existe, se crea un objeto Carpeta con solo el ID
        if (cTo.getCarpetaPadreId() != null) {
            Carpeta carpetaPadre = new Carpeta();
            carpetaPadre.setId(cTo.getCarpetaPadreId());
            carpeta.setCarpetaPadre(carpetaPadre);
        }

        // Convertir subcarpetasTo a subcarpetas reales
        if (cTo.getSubcarpetas() != null) {
            List<Carpeta> subcarpetas = cTo.getSubcarpetas().stream().map(this.mapCarpeta).toList();
            carpeta.setSubcarpetas(subcarpetas);
        }

        return carpeta;
    };

    @Override
    public CarpetaTo buscarPorId(Integer id) {
        Carpeta carp = this.iCarpetaRepository.buscarPorId(id);
        return this.mapTo.apply(carp);
    }

    @Override
    public void guardar(CarpetaTo carpetaTo) {
        Carpeta carpeta = new Carpeta();
        carpeta.setNombre(carpetaTo.getNombre());

        // Si tiene carpeta padre, buscarla y asignarla
        if (carpetaTo.getCarpetaPadreId() != null) {
            Carpeta carpetaPadre = iCarpetaRepository.buscarPorId(carpetaTo.getCarpetaPadreId());
            if (carpetaPadre != null) {
                carpeta.setCarpetaPadre(carpetaPadre);
            }
        }

        // Persistir la carpeta
        iCarpetaRepository.insertar(carpeta);
    }

    @Override
    public void borrar(Integer id) {
        iCarpetaRepository.eliminar(id);
    }

    @Override
    public List<CarpetaTo> buscarTodos() {
        List<Carpeta> carpetas = this.iCarpetaRepository.buscarTodos();
        // Asegúrate de que las subcarpetas estén cargadas antes de hacer el mapeo
        carpetas.forEach(c -> {
            // Inicializa la colección de subcarpetas si es lazy-loaded
            if (c.getSubcarpetas() != null) {
                c.getSubcarpetas().size(); // Esto fuerza la carga de la colección
            }
        });
        return carpetas.stream().map(this.mapTo).toList();
    }

    @Override
    public void actualizar(CarpetaTo carpetaTo) {
        Carpeta carpeta = iCarpetaRepository.buscarPorId(carpetaTo.getId());
        if (carpeta != null) {
            carpeta.setNombre(carpetaTo.getNombre());

            // Actualizar carpeta padre si existe
            if (carpetaTo.getCarpetaPadreId() != null) {
                Carpeta carpetaPadre = iCarpetaRepository.buscarPorId(carpetaTo.getCarpetaPadreId());
                if (carpetaPadre != null) {
                    carpeta.setCarpetaPadre(carpetaPadre);
                }
            }

            // Persistir cambios
            iCarpetaRepository.actualizar(carpeta);
        }
    }

}
