package edu.upc.dsa;

import edu.upc.dsa.models.MaletaDTO;
import org.apache.log4j.Logger;

import java.util.*;

import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Vuelo;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.exceptions.AvionNotFoundException;

import edu.upc.dsa.exceptions.VueloNotFoundException;

public class AvionManagerImpl implements AvionManager {
    private static AvionManagerImpl instance;
    private HashMap<String, Avion> aviones;
    private HashMap<String, Vuelo> vuelos;


    final static Logger logger = Logger.getLogger(AvionManagerImpl.class);

    private AvionManagerImpl() {
        this.aviones = new HashMap<>();
        this.vuelos = new HashMap<>();
    }

    public static AvionManagerImpl getInstance() {
        if (instance == null) {
            instance = new AvionManagerImpl();
        }
        return instance;
    }

    @Override
    public void addAvion(String id, String modelo, String companyia) {
        logger.info("Iniciando addAvion: id={}, modelo={}, companyia={}" + id + modelo + companyia);

        try {
            if (aviones.containsKey(id)) {
                Avion avionExistente = aviones.get(id);
                avionExistente.setModelo(modelo);
                avionExistente.setCompanyia(companyia);
                logger.info("Avión actualizado: {}" + avionExistente);
            } else {
                Avion avion = new Avion(id, modelo, companyia);
                aviones.put(id, avion);
                logger.info("Avión creado: {}" + avion);
            }
        } catch (Exception e) {
            logger.error("Error en addAvion", e);
        }

        logger.info("Finalizando addAvion: id={}, modelo={}, companyia={}" + id + modelo + companyia);
    }

    @Override
    public Avion getAvion(String id) {
        logger.info("Iniciando getAvion: id={}" + id);
        Avion avion = aviones.get(id);
        if (avion == null) {
            logger.error("Avión no encontrado: id={}" + id);
        }
        logger.info("Finalizando getAvion: id={}" + id);
        return avion;
    }

    @Override
    public void addVuelo(String id, String horaSalida, String horaLlegada, String avionId, String origen, String destino)
            throws AvionNotFoundException {
        logger.info("Iniciando addVuelo: id={}, horaSalida={}, horaLlegada={}, avionId={}, origen={}, destino={}" + id + horaSalida + horaLlegada + avionId + origen + destino);

        try {
            Avion avion = aviones.get(avionId);
            if (avion == null) {
                logger.error("El avión con id={} no existe" + avionId);
                throw new AvionNotFoundException("El avión especificado no existe");
            }

            Vuelo vuelo = vuelos.get(id);
            if (vuelo == null) {
                vuelo = new Vuelo(id, horaSalida, horaLlegada, avionId, origen, destino);
                vuelos.put(id, vuelo);
                avion.addVuelo(vuelo);
                logger.info("Vuelo creado: {}" + vuelo);
            } else {
                if (!vuelo.getAvionId().equals(avionId)) {
                    logger.warn("Intento de cambiar el avión de un vuelo existente. No se permite.");
                }
                vuelo.setHoraSalida(horaSalida);
                vuelo.setHoraLlegada(horaLlegada);
                vuelo.setOrigen(origen);
                vuelo.setDestino(destino);
                logger.info("Vuelo actualizado: {}" + vuelo);
            }

        } catch (AvionNotFoundException e) {
            logger.error("Error al agregar vuelo: el avión no existe", e);
            throw e;
        } catch (Exception e) {
            // Log de error para excepciones generales
            logger.error("Error inesperado en addVuelo", e);
        }

        logger.info("Finalizando addVuelo: id={}, horaSalida={}, horaLlegada={}, avionId={}, origen={}, destino={}" +
                id + horaSalida + horaLlegada + avionId + origen + destino);
    }


    @Override
    public void facturarMaleta(Maleta maleta) throws VueloNotFoundException {

        try {
            String vueloId = maleta.getVueloId();

            Vuelo vuelo = vuelos.get(vueloId);

            if (vuelo == null) {
                logger.error("Vuelo no encontrado para la maleta: vueloId={}" + vueloId);
                throw new VueloNotFoundException("El vuelo especificado no existe");
            }

            vuelo.addMaleta(maleta);


            logger.info("Maleta facturada con éxito: maletaId=" + maleta.getId());

        } catch (VueloNotFoundException e) {
            logger.error("Error al facturar maleta: {}" + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error inesperado al facturar maleta", e);
        }

    }

    @Override
    public List<Maleta> getMaletasDeVuelo(String vueloId) throws VueloNotFoundException {
        logger.info("Iniciando getMaletasDeVuelo: vueloId={}" + vueloId);
        Vuelo vuelo = vuelos.get(vueloId);
        if (vuelo == null) {
            logger.error("Vuelo no encontrado: vueloId={}" + vueloId);
            throw new VueloNotFoundException("El vuelo con ID " + vueloId + " no existe");
        }

        Deque<Maleta> maletasDeque = vuelo.getMaletas();
        List<Maleta> maletas = new ArrayList<>(maletasDeque);


        logger.info("Finalizando getMaletasDeVuelo: vueloId={}" + vueloId);
        return maletas;
    }

    @Override
    public List<MaletaDTO> getMaletasDeVueloDTO(String vueloId) throws VueloNotFoundException {
        // Agregar log de depuración para el vueloId
        logger.info("Iniciando getMaletasDeVueloDTO: vueloId={}" + vueloId);

        if (vueloId == null || vueloId.isEmpty()) {
            logger.error("VueloID es nulo o vacío: vueloId={}" + vueloId);
            throw new VueloNotFoundException("El vuelo con ID " + vueloId + " no existe");
        }

        // Verificar que el vuelo exista en el mapa
        Vuelo vuelo = vuelos.get(vueloId);
        if (vuelo == null) {
            logger.error("Vuelo no encontrado: vueloId={}" + vueloId);
            throw new VueloNotFoundException("El vuelo con ID " + vueloId + " no existe");
        }

        // Log para verificar si el vuelo fue encontrado correctamente
        logger.info("Vuelo encontrado: {}" + vuelo);

        Deque<Maleta> maletasDeque = vuelo.getMaletas();
        List<MaletaDTO> maletasDTO = new ArrayList<>();
        for (Maleta maleta : maletasDeque) {
            MaletaDTO maletaDTO = new MaletaDTO(maleta.getId(), maleta.getUsuarioId(), maleta.getVueloId());
            maletasDTO.add(maletaDTO);
        }

        logger.info("Finalizando getMaletasDeVueloDTO: vueloId={}" + vueloId);
        return maletasDTO;
    }



    @Override
    public Vuelo getVuelo(String id) {
        logger.info("Iniciando getVuelo: id={}" + id);
        Vuelo vuelo = vuelos.get(id);
        if (vuelo == null) {
            logger.error("Vuelo no encontrado: id={}" + id);
        }
        logger.info("Finalizando getVuelo: id={}" + id);
        return vuelo;
    }

    @Override
    public void clear() {
        logger.info("Iniciando clear: eliminando todos los aviones y vuelos");
        aviones.clear();
        vuelos.clear();
        logger.info("Finalizando clear: todos los aviones y vuelos han sido eliminados");
    }
}
