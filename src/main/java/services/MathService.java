package services;

import manager.MathManager;
import manager.MathManagerImpl;
import models.Operation;
import models.Ranking;
import models.transferobjetos;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import models.OperationTO;


import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.ArrayList;


@Api(value = "/math", description = "Endpoint to Math Service")
@Path("/math")
public class MathService {

    private MathManager manager;

    public MathService() {
        this.manager = MathManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Añadir nueva operacion matematica")
    @Path("/operaciones")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response añadirOperacion(OperationTO opTO) {
        this.manager.añadirOperacion(opTO.getExpresion(), opTO.getIdAlumno(), opTO.getIdInstituto());
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "Procesar la siguiente operacion de la cola ")
    @Path("/procesar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response procesarSiguiente() throws Exception {
        Operation op = this.manager.procesarSiguienteOperacion();

        // Si la cola está vacía
        if (op == null) return Response.status(404).build();

        // Convertimos a transfer objetc para enviarla
        OperationTO respuesta = new OperationTO(op.getExpresion(), op.getIdAlumno(), op.getIdInstituto(), op.getResultado());
        return Response.status(200).entity(respuesta).build();
    }


    @GET
    @ApiOperation(value = "Consultar todas operaciones de un instituto")
    @Path("/institutos/{idInstituto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOperacionesInstituto(@PathParam("idInstituto") String idInstituto) {
        List<Operation> operaciones = this.manager.obtenerOperacionesPorInstituto(idInstituto);

        List<OperationTO> listaTO = new ArrayList<>();
        for (int i = 0; i < operaciones.size(); i++) {
            Operation op = operaciones.get(i);
            listaTO.add(new OperationTO(op.getExpresion(), op.getIdAlumno(), op.getIdInstituto(), op.getResultado()));
        }
        GenericEntity<List<OperationTO>> entity = new GenericEntity<List<OperationTO>>(listaTO) {
        };
        return Response.status(200).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Consultar todas las operaciones de un alumno")
    @Path("/alumnos/{idAlumno}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOperacionesAlumno(@PathParam("idAlumno") String idAlumno) {
        List<Operation> operaciones = this.manager.obtenerOperacionesPorAlumno(idAlumno);

        List<OperationTO> listaTO = new ArrayList<>();
        for (int i = 0; i < operaciones.size(); i++) {
            Operation op = operaciones.get(i); // Extraemos el elemento en la posición 'i'
            listaTO.add(new OperationTO(op.getExpresion(), op.getIdAlumno(), op.getIdInstituto(), op.getResultado()));
        }

        GenericEntity<List<OperationTO>> entity = new GenericEntity<List<OperationTO>>(listaTO) {
        };
        return Response.status(200).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Obtener ranking de institutos")
    @Path("/institutos/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRankingInstitutos() {
        List<String> lista = this.manager.obtenerInstitutosOrdenadosPorOperaciones();

        // Para poder solucionar el problema del JSON lo genero yo mismo con el siguiente codigo
        StringBuilder json = new StringBuilder();
        json.append("[");
        for (int i = 0; i < lista.size(); i++) {
            json.append("\"").append(lista.get(i)).append("\"");
            if (i < lista.size() - 1) {
                json.append(", ");
            }
        }
        json.append("]");

        return Response.status(200).entity(json.toString()).build();
    }
}