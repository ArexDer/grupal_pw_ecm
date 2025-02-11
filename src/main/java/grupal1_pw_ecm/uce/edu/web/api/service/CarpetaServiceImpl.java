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
        CarpetaTo cTo = new CarpetaTo(c.getId(), c.getNombre());
        return cTo;
    };

    private Function<CarpetaTo, Carpeta> mapCarpeta = cTo -> {
        Carpeta c = new Carpeta(cTo.getId(), cTo.getNombre());
        return c;
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
