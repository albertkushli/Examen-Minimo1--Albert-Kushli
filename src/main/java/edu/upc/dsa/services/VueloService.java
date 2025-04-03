package edu.upc.dsa.services;
/*
import edu.upc.dsa.AvionManager;
import edu.upc.dsa.AvionManagerImpl;
import edu.upc.dsa.exceptions.VueloNotFoundException;
import edu.upc.dsa.models.Vuelo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/vuelos", description = "Endpoint for managing flights")
@Path("/vuelos")
public class VueloService {

    private AvionManager avionManager;

    public VueloService() {
        this.avionManager = AvionManagerImpl.getInstance();

        // Inicialización de vuelos de ejemplo
        if (avionManager.getVuelo("V001") == null) {
            avionManager.addVuelo("V001", "10:00", "12:00", "1", "Barcelona", "Madrid");
        }
        if (avionManager.getVuelo("V002") == null) {
            avionManager.addVuelo("V002", "14:00", "16:00", "2", "Madrid", "Paris");
        }
    }

    // Add a new flight
    @POST
    @ApiOperation(value = "Create a new flight", notes = "Adds a new flight to the system")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created flight"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addVuelo(Vuelo vuelo) {
        try {
            avionManager.addVuelo(vuelo.getId(), vuelo.getHoraSalida(), vuelo.getHoraLlegada(),
                    vuelo.getAvionId(), vuelo.getOrigen(), vuelo.getDestino());
            return Response.status(201).entity(vuelo).build();
        } catch (VueloNotFoundException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    // Get a flight by ID
    @GET
    @ApiOperation(value = "Get a flight by ID", notes = "Retrieve details of a specific flight")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved flight", response = Vuelo.class),
            @ApiResponse(code = 404, message = "Flight not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVuelo(@PathParam("id") String id) {
        Vuelo vuelo = avionManager.getVuelo(id);
        if (vuelo == null) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(vuelo).build();
    }
}


 */



