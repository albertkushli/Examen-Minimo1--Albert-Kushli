package edu.upc.dsa.services;

import edu.upc.dsa.AvionManager;
import edu.upc.dsa.AvionManagerImpl;
import edu.upc.dsa.exceptions.AvionNotFoundException;
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

    public VueloService() throws AvionNotFoundException {
        this.avionManager = AvionManagerImpl.getInstance();

        if (avionManager.getAvion("1") == null) {
            avionManager.addAvion("1", "Boeing 747", "Iberia");
        }
        if (avionManager.getAvion("2") == null) {
            avionManager.addAvion("2", "Airbus A320", "Vueling");
        }

        if (avionManager.getAvion("1") != null && avionManager.getVuelo("1") == null) {
            avionManager.addVuelo("1", "10:00", "12:00", "1", "Barcelona", "Madrid");
        }
        if (avionManager.getAvion("2") != null && avionManager.getVuelo("2") == null) {
            avionManager.addVuelo("2", "15:00", "17:00", "2", "Madrid", "Paris");
        }
    }

    @POST
    @ApiOperation(value = "Create or update a flight", notes = "Adds a new flight or updates an existing one")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created/updated flight"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Airplane not found")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrUpdateVuelo(Vuelo vuelo) {
        if (vuelo.getId() == null || vuelo.getHoraSalida() == null || vuelo.getHoraLlegada() == null ||
                vuelo.getAvionId() == null || vuelo.getOrigen() == null || vuelo.getDestino() == null) {
            return Response.status(400).entity("Missing fields").build();
        }


        try {
            // Se valida que el avión existe
            avionManager.addVuelo(vuelo.getId(), vuelo.getHoraSalida(), vuelo.getHoraLlegada(), vuelo.getAvionId(), vuelo.getOrigen(), vuelo.getDestino());
            return Response.status(201).entity(vuelo).build();
        } catch (Exception e) {
            return Response.status(404).entity("Airplane not found: " + e.getMessage()).build();
        }
    }

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



