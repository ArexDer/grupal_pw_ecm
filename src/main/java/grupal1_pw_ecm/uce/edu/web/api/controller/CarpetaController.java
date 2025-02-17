package grupal1_pw_ecm.uce.edu.web.api.controller;

import java.util.List;

import grupal1_pw_ecm.uce.edu.web.api.service.ICarpetaService;
import grupal1_pw_ecm.uce.edu.web.api.service.to.CarpetaTo;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/carpetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarpetaController {

    @Inject
    private ICarpetaService iCarpetaService;

    @GET
    @Path("/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    //http://localhost:8081/gestorcontenido/v1.1/carpetas/nombre
    public Response buscarPorNombre(@PathParam("nombre") String nombre) {
        CarpetaTo carpeta = this.iCarpetaService.buscarNombre(nombre);
        if (carpeta != null) {
            return Response.ok(carpeta).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarpetaTo> buscarTodos() {
        return this.iCarpetaService.buscarTodos();
    }

    
    @POST
    @Path("")
    public Response guardar(CarpetaTo carpeta) {
        this.iCarpetaService.guardar(carpeta);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Integer id, CarpetaTo carpeta) {
        carpeta.setId(id);
        this.iCarpetaService.actualizar(carpeta);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response borrar(@PathParam("id") Integer id) {
        CarpetaTo carpeta = this.iCarpetaService.buscarPorId(id);
        if (carpeta != null) {
            this.iCarpetaService.borrar(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
