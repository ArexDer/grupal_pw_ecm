package grupal1_pw_ecm.uce.edu.web.api.controller;

import grupal1_pw_ecm.uce.edu.web.api.service.ICarpetaService;
import grupal1_pw_ecm.uce.edu.web.api.service.to.CarpetaTo;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

@Path("/carpetas")
public class CarpetaController {

    @Inject
    private ICarpetaService iCarpetaService;

    /*@GET
    @Path("/{id}")
    public CarpetaTo buscarPorId(@PathParam("id") Integer id) {
        return this.iCarpetaService.buscarPorId(id);
    }*/

    
    @GET
    @Path("")
    public List<CarpetaTo> buscarTodos(){
        return this.iCarpetaService.buscarTodos();
    }

    @POST
    @Path("/guardar")
    public void guardar(CarpetaTo carpeta) {
        this.iCarpetaService.guardar(carpeta);
    }

    /*@PUT
    @Path("/actualizar")
    public void actualizar(CarpetaTo carpeta) {
        this.iCarpetaService.actualizar(carpeta);
    }

    @PATCH
    @Path("/actualizar/parcial")
    public void actualizarParcial(CarpetaTo carpeta) {
        CarpetaTo tmp = this.iCarpetaService.buscarPorId(carpeta.getId());
        tmp.setNombre(carpeta.getNombre());
        this.iCarpetaService.actualizar(tmp);
    }*/

    @DELETE
    @Path("/borrar")
    public void borrar() {
        Integer id = 3;
        this.iCarpetaService.borrar(id);
    }
}
