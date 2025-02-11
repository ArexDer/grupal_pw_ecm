package grupal1_pw_ecm.uce.edu.web.api.controller;

import grupal1_pw_ecm.uce.edu.web.api.repository.modelo.Archivo;
import grupal1_pw_ecm.uce.edu.web.api.service.IArchivoService;
import grupal1_pw_ecm.uce.edu.web.api.service.to.ArchivoTo;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/archivos")
public class ArchivoController {

    @Inject
    private IArchivoService archivoService;

    @POST
    public Response subirArchivo(ArchivoTo archivo) {
        this.archivoService.guardar(archivo);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM) // Indica que se devuelve un archivo binario
    public Response descargarArchivo(@PathParam("id") Integer id) {
        ArchivoTo archivo = this.archivoService.buscar(id);

        if (archivo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(archivo.getContenido()) // Devuelve el archivo en el cuerpo de la respuesta
                .header("Content-Disposition", "attachment; filename=\"" + archivo.getNombre() + "\"")
                .type(archivo.getTipo()) // Tipo MIME del archivo (ej. application/pdf)
                .build();
    }

    @GET
    @Path("/descargar")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarArchivoPorNombre(@QueryParam("nombre") String nombre) {
        ArchivoTo archivo = archivoService.buscarNombre(nombre);

        if (archivo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(archivo.getContenido())
                .header("Content-Disposition", "attachment; filename=\"" + archivo.getNombre() + "\"")
                .type(archivo.getTipo())
                .build();
    }

    /*
     * 
@Produces(MediaType.APPLICATION_OCTET_STREAM)

Esto indica que el endpoint devuelve un archivo binario (útil para cualquier tipo de archivo).
Response.ok(archivo.getContenido())

Ahora el cuerpo de la respuesta contiene los datos binarios del archivo.
header("Content-Disposition", "attachment; filename=...")

Esto hace que el navegador descargue el archivo en lugar de abrirlo como texto.
type(archivo.getTipo())

Usa el tipo MIME original del archivo (ej. application/pdf, image/png, etc.).

     */
}
