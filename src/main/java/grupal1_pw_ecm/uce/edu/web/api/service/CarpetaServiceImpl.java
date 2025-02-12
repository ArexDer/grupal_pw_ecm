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
        List<CarpetaTo> subcarpetasTo = (c.getSubcarpetas() != null) ? 
            c.getSubcarpetas().stream().map(this.mapTo).toList() : null;
    
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
    public void guardar(CarpetaTo carpeta) {
        this.iCarpetaRepository.insertar(this.mapCarpeta.apply(carpeta));
    }

    @Override
    public void borrar(Integer id) {
        this.iCarpetaRepository.eliminar(id);
    }

    @Override
    public List<CarpetaTo> buscarTodos() {
        List<Carpeta> carpetas = this.iCarpetaRepository.buscarTodos();
        return carpetas.stream().map(this.mapTo).toList();
    }
}
