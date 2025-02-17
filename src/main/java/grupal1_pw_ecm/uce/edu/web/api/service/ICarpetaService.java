package grupal1_pw_ecm.uce.edu.web.api.service;

import java.util.List;

import grupal1_pw_ecm.uce.edu.web.api.repository.modelo.Carpeta;
import grupal1_pw_ecm.uce.edu.web.api.service.to.CarpetaTo;

public interface ICarpetaService {
    public CarpetaTo buscarPorId(Integer id);

    public List<CarpetaTo> buscarTodos();

    public void guardar(CarpetaTo carpeta);

    public void actualizar(CarpetaTo carpeta);

    public void borrar(Integer id);

    public List<CarpetaTo> buscarIdPadre(Integer id);
}