package edu.upc.dsa.services;

import edu.upc.dsa.AvionManager;
import edu.upc.dsa.AvionManagerImpl;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.MaletaDTO;

import edu.upc.dsa.exceptions.VueloNotFoundException;
import edu.upc.dsa.exceptions.AvionNotFoundException;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Api(value = "/maletas", description = "Endpoint for managing luggage")
@Path("/maletas")
public class MaletaService {

    private AvionManager avionManager;

    public MaletaService() throws VueloNotFoundException, AvionNotFoundException {
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


        if (avionManager.getVuelo("1") != null) {
            if (avionManager.getMaletasDeVueloDTO("1").isEmpty()) {
                avionManager.facturarMaleta(new Maleta("Usuario1", "1"));
                avionManager.facturarMaleta(new Maleta("Usuario2", "1"));
            }
        }
    }

    @POST
    @Path("/Afegir")
    @ApiOperation(value = "Create or update a luggage", notes = "Facture a luggage and assign it to a flight")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created/updated luggage"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrUpdateMaleta(Maleta maleta) {
        if (maleta.getVueloId() == null || maleta.getUsuarioId() == null) {
            return Response.status(400).entity("Missing fields").build();
        }

        try {
            avionManager.facturarMaleta(maleta);
            return Response.status(201).entity(maleta).build();
        } catch (VueloNotFoundException e) {
            return Response.status(404).entity("Flight not found: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(400).entity("Error facturing luggage: " + e.getMessage()).build();
        }
    }

    /*@GET
    @Path("/{vueloId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaletas(@PathParam("vueloId") String vueloId) {
        try {
            List<MaletaDTO> maletas = avionManager.getMaletasDeVueloDTO(vueloId);
            if (maletas == null || maletas.isEmpty()) {
                return Response.status(404).entity("No hay maletas para este vuelo").build();
            }
            return Response.status(200).entity(maletas).build();
        } catch (VueloNotFoundException e) {
            return Response.status(404).entity("Vuelo no encontrado: " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(500).entity("Error interno del servidor: " + e.getMessage()).build();
        }
    }

     */

    @GET
    @Path("/laugage/{vueloId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMaletas(@PathParam("vueloId") String vueloId) {
        List<MaletaDTO> maletas = new ArrayList<>();
        maletas.add(new MaletaDTO("1", "Usuario1", "1"));
        return Response.ok(maletas).build();
    }


}






