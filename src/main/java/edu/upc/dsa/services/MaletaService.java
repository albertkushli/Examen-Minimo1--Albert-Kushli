package edu.upc.dsa.services;
/*
import edu.upc.dsa.AvionManager;
import edu.upc.dsa.AvionManagerImpl;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.exceptions.VueloNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/maletas", description = "Endpoint for handling luggage")
@Path("/maletas")
public class MaletaService {

    private AvionManager avionManager;

    public MaletaService() {
        this.avionManager = AvionManagerImpl.getInstance();
    }

    // Facturar una maleta (registrar una maleta en un vuelo)
    @POST
    @ApiOperation(value = "Check in a luggage item", notes = "Register a luggage item for a specific flight")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully checked in luggage"),
            @ApiResponse(code = 404, message = "Flight not found")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response facturarMaleta(Maleta maleta) {
        try {
            avionManager.facturarMaleta(maleta);
            return Response.status(201).entity(maleta).build();
        } catch (VueloNotFoundException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    // Obtener todas las maletas de un vuelo
    @GET
    @ApiOperation(value = "Get all luggage for a flight", notes = "Retrieve a list of all luggage checked in for a specific flight")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved luggage", response = Maleta.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Flight not found")
    })
    @Path("/{vueloId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaletasDeVuelo(@PathParam("vueloId") String vueloId) {
        try {
            List<Maleta> maletas = avionManager.getMaletasDeVuelo(vueloId);
            return Response.status(200).entity(maletas).build();
        } finally {

        }
    }
}

*/
