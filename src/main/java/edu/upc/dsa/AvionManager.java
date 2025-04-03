package edu.upc.dsa;

import edu.upc.dsa.exceptions.VueloNotFoundException;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Vuelo;
import edu.upc.dsa.models.Maleta;

import java.util.List;


public interface AvionManager {

    void addAvion(String id, String modelo, String companyia);
    Avion getAvion(String id);
    Vuelo getVuelo(String id);
    void addVuelo(String id, String horaSalida, String horaLlegada, String avionId, String origen, String destino) throws VueloNotFoundException;
    void clear();
    List<Maleta> getMaletasDeVuelo(String vueloId);
    void facturarMaleta(Maleta maleta) throws VueloNotFoundException;

}
