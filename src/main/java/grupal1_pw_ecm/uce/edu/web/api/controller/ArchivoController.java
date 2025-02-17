package grupal1_pw_ecm.uce.edu.web.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jboss.resteasy.reactive.RestForm;

import grupal1_pw_ecm.uce.edu.web.api.service.CarpetaServiceImpl;
import grupal1_pw_ecm.uce.edu.web.api.service.IArchivoService;
import grupal1_pw_ecm.uce.edu.web.api.service.to.ArchivoTo;
import grupal1_pw_ecm.uce.edu.web.api.service.to.CarpetaTo;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

@Path("/archivos")
public class ArchivoController {

    @Inject
    private IArchivoService archivoService;
    @Inject
    private CarpetaServiceImpl carpetaService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response subirArchivo(
            @RestForm("archivo") InputStream fileInputStream,
            @RestForm("nombre") String nombre,
            @RestForm("tipo") String tipo,
            @RestForm("carpeta") String carpeta) {

        try {
            byte[] contenido = fileInputStream.readAllBytes(); // Convertir archivo a byte[]
            fileInputStream.close(); // Cerrar el InputStream para liberar el archivo

            ArchivoTo archivoTo = new ArchivoTo();
            archivoTo.setNombre(nombre);
            archivoTo.setTipo(tipo);
            archivoTo.setContenido(contenido);
            CarpetaTo carpetaTo = carpetaService.buscarNombre(carpeta);

            archivoTo.setCarpeta(carpetaTo); // Asignar la instancia de CarpetaTo

            if (archivoTo.getCarpeta() != null) {
                System.err.println(archivoTo.getCarpeta().getId()
                        + " " + archivoTo.getNombre() + " " + archivoTo.getTipo());
            } else {
                System.err.println("La carpeta es null");
            }

            this.archivoService.guardar(archivoTo);

            return Response.ok().build();

        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al procesar el archivo")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response descargarArchivo(@PathParam("id") Integer id) {
        ArchivoTo archivo = this.archivoService.buscar(id);

        if (archivo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(archivo.getContenido()) // Devuelve archivo en el cuerpo de la respuesta
                .header("Content-Disposition", "attachment; filename=\"" + archivo.getNombre() + "\"")
                .type(archivo.getTipo()) // Tipo MIME original del archivo
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

    @DELETE
    @Path("/{id}")
    public Response eliminarArchivo(@PathParam("id") Integer id) {
        this.archivoService.borrar(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    // http://localhost:8081/gestorcontenido/v1.1/archivos/listar?carpeta=nombre
    public Response listarArchivos(@QueryParam("carpeta") String carpeta) {
        List<ArchivoTo> archivos;

        if (carpeta != null && !carpeta.isEmpty()) {
            CarpetaTo carpetaTo = this.carpetaService.buscarNombre(carpeta);
            if (carpetaTo == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Carpeta no encontrada").build();
            }
            Integer carpetaId = carpetaTo.getId();
            archivos = archivoService.buscarPorCarpeta(carpetaId);
        } else {
            archivos = archivoService.buscarTodos();
        }

        // Si no hay archivos
        if (archivos.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity("No hay archivos disponibles").build();
        }

        // Crear enlaces HATEOAS para cada archivo con la URL completa
        archivos.forEach(archivo -> {
            // Usar una ruta relativa, sin el esquema completo (localhost:8080)
            String downloadUrl = UriBuilder.fromPath("/gestorcontenido/v1.1/archivos/{id}")
                    .resolveTemplate("id", archivo.getId())
                    .build()
                    .toString();

            // Agregar el enlace completo al objeto ArchivoTo
            archivo.setLink(Link.fromUri(downloadUrl).rel("descargar").build());
        });

        return Response.ok(archivos).build();
    }

    @GET
    @Path("/listarN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarArchivosN() {
        List<ArchivoTo> archivos = archivoService.buscarTodos();

        // Si no hay archivos
        if (archivos.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).entity("No hay archivos disponibles").build();
        }

        // Crear enlaces HATEOAS para cada archivo con la URL completa
        archivos.forEach(archivo -> {
            // Crear la URL completa para el archivo, asegurando que sea el esquema completo
            String downloadUrl = UriBuilder.fromUri("/gestorcontenido/v1.1/archivos/descargar")
                    .queryParam("nombre", archivo.getNombre())
                    .build()
                    .toString();

            // Agregar el enlace completo al objeto ArchivoTo
            archivo.setLink(Link.fromUri(downloadUrl).rel("descargar").build());
        });

        return Response.ok(archivos).build();
    }

}