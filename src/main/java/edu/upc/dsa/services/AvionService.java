package edu.upc.dsa.services;

import edu.upc.dsa.AvionManager;
import edu.upc.dsa.AvionManagerImpl;
import edu.upc.dsa.models.Avion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/avions", description = "Endpoint for managing airplanes")
@Path("/avions")
public class AvionService {

    private AvionManager avionManager;

    public AvionService() {
        this.avionManager = AvionManagerImpl.getInstance();

        // Inicialización de aviones de ejemplo
        if (avionManager.getAvion("1") == null) {
            avionManager.addAvion("1", "Boe737", "Iba");
        }
        if (avionManager.getAvion("2") == null) {
            avionManager.addAvion("2", "AiA320", "Aire");
        }
    }

    @POST
    @ApiOperation(value = "Create or update an airplane", notes = "Adds a new airplane or updates an existing one")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created/updated airplane"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrUpdateAvion(Avion avion) {
        if (avion.getId() == null || avion.getModelo() == null || avion.getCompanyia() == null) {
            return Response.status(400).entity("Missing fields").build();
        }
        avionManager.addAvion(avion.getId(), avion.getModelo(), avion.getCompanyia());
        return Response.status(201).entity(avion).build();
    }

    // Get an airplane by ID
    @GET
    @ApiOperation(value = "Get an airplane by ID", notes = "Retrieve details of a specific airplane")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved airplane", response = Avion.class),
            @ApiResponse(code = 404, message = "Airplane not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvion(@PathParam("id") String id) {
        Avion avion = avionManager.getAvion(id);
        if (avion == null) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(avion).build();
    }
}

