package edu.upc.dsa;

import edu.upc.dsa.exceptions.AvionNotFoundException;
import edu.upc.dsa.exceptions.VueloNotFoundException;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.MaletaDTO;
import edu.upc.dsa.models.Vuelo;
import edu.upc.dsa.models.Maleta;

import java.util.List;


public interface AvionManager {

    void addAvion(String id, String modelo, String companyia);
    Avion getAvion(String id);
    Vuelo getVuelo(String id);
    void addVuelo(String id, String horaSalida, String horaLlegada, String avionId, String origen, String destino) throws AvionNotFoundException;
    void clear();
    List<Maleta> getMaletasDeVuelo(String vueloId) throws VueloNotFoundException;
    void facturarMaleta(Maleta maleta) throws VueloNotFoundException;
    List<MaletaDTO> getMaletasDeVueloDTO(String vueloId) throws VueloNotFoundException;

}
