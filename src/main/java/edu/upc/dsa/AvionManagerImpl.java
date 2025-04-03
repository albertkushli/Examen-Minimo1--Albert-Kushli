package edu.upc.dsa;

import org.apache.log4j.Logger;

import java.util.*;

import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Vuelo;
import edu.upc.dsa.models.Maleta;

import edu.upc.dsa.exceptions.VueloNotFoundException;

public class AvionManagerImpl implements AvionManager {
    private static AvionManagerImpl instance;
    private HashMap<String, Avion> aviones;
    private HashMap<String, Vuelo> vuelos;
    private HashMap<String, Deque<Maleta>> vuelosMaletas;


    final static Logger logger = Logger.getLogger(AvionManagerImpl.class);

    private AvionManagerImpl() {
        this.aviones = new HashMap<>();
        this.vuelos = new HashMap<>();
        this.vuelosMaletas = new HashMap<>();
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
    public void addVuelo(String id, String horaSalida, String horaLlegada, String avionId, String origen, String destino) throws VueloNotFoundException {
        logger.info("Iniciando addVuelo: id={}, horaSalida={}, horaLlegada={}, avionId={}, origen={}, destino={}" + id + horaSalida + horaLlegada + avionId + origen + destino);

        try {
            Avion avion = aviones.get(avionId);
            if (avion == null) {
                logger.error("El avión con id={} no existe" + avionId);
                throw new VueloNotFoundException("El avión especificado no existe");
            }

            Vuelo vuelo = new Vuelo(id, horaSalida, horaLlegada, avionId, origen, destino);
            vuelos.put(id, vuelo);
            vuelosMaletas.putIfAbsent(id, new ArrayDeque<>());
            logger.info("Vuelo creado: {}" + vuelo);
        } catch (VueloNotFoundException e) {
            logger.error("Error en addVuelo: {}" + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error inesperado en addVuelo", e);
        }

        logger.info("Finalizando addVuelo: id={}, horaSalida={}, horaLlegada={}, avionId={}, origen={}, destino={}" + id + horaSalida + horaLlegada + avionId + origen + destino);
    }

    @Override
    public void facturarMaleta(Maleta maleta) throws VueloNotFoundException {

        try {
            String vueloId = maleta.getVueloId();

            // Verificación de existencia de vuelo
            if (!vuelos.containsKey(vueloId)) {
                logger.error("Vuelo no encontrado para la maleta: vueloId={}" + vueloId);
                throw new VueloNotFoundException("El vuelo especificado no existe");
            }

            vuelosMaletas.get(vueloId).push(maleta);
            logger.info("Maleta facturada con éxito: maletaId={}" );
        } catch (VueloNotFoundException e) {
            logger.error("Error al facturar maleta: {}" + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error inesperado al facturar maleta", e);
        }

    }

    @Override
    public List<Maleta> getMaletasDeVuelo(String vueloId) {
        logger.info("Iniciando getMaletasDeVuelo: vueloId={}" + vueloId);
        List<Maleta> maletas = new ArrayList<>(vuelosMaletas.getOrDefault(vueloId, new ArrayDeque<>()));
        logger.info("Finalizando getMaletasDeVuelo: vueloId={}" + vueloId);
        return maletas;
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
