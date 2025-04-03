package edu.upc.dsa;

import edu.upc.dsa.exceptions.VueloNotFoundException;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Vuelo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AvionTest {
    AvionManager am;

    @Before
    public void setUp() {
        this.am = AvionManagerImpl.getInstance();
        this.am.addAvion("A123", "Boing74", "Skycent");
        this.am.addAvion("A2", "Azona45", "Vueling");
        this.am.addAvion("V123", "Rapzor49", "Vueling");
    }

    @After
    public void tearDown() {
        // És un Singleton
        this.am.clear();
    }

    @Test
    public void testAddAvion() {
        this.am.addAvion("A125", "Boeing 777", "Aerolineas Z");
        Assert.assertNotNull(am.getAvion("A125"));
        Assert.assertEquals("Boeing 777", am.getAvion("A125").getModelo());
    }


    @Test
    public void testAddVolConAvionExistente() throws VueloNotFoundException {
        this.am.addVuelo("V123", "2025-04-10 10:00", "2025-04-10 12:00", "A123", "Barcelona", "Madrid");
        Assert.assertNotNull(am.getVuelo("V123"));
        Assert.assertEquals("Madrid", am.getVuelo("V123").getDestino());
    }


    @Test
    public void testAddVolConAvionInexistente() {
        Exception exception = Assert.assertThrows(VueloNotFoundException.class, () -> {
            am.addVuelo("V124", "2025-04-10 14:00", "2025-04-10 16:00", "A999", "Barcelona", "Paris");
        });
        Assert.assertEquals("El avión especificado no existe", exception.getMessage());
    }

    @Test
    public void testFacturarMaletaAVolExistente() throws VueloNotFoundException {
        // Crear un vuelo
        this.am.addVuelo("V123", "2025-04-10 10:00", "2025-04-10 12:00", "A123", "Barcelona", "Madrid");

        // Crear una maleta que pertenece a este vuelo
        Maleta maleta = new Maleta("user123", "V123");

        // Facturar la maleta
        this.am.facturarMaleta(maleta);

        // Verificar que la maleta se haya añadido correctamente al vuelo
        List<Maleta> maletas = this.am.getMaletasDeVuelo("V123");
        Assert.assertEquals(1, maletas.size());
        Assert.assertEquals("user123", maletas.get(0).getUsuarioId());
    }



    @Test
    public void testFacturarMaletaAVolInexistente() {
        Maleta maleta = new Maleta("user123", "V999");

        VueloNotFoundException exception = Assert.assertThrows(VueloNotFoundException.class, () -> {
            am.facturarMaleta(maleta);
        });

        Assert.assertEquals("El vuelo especificado no existe", exception.getMessage());
    }



    @Test
    public void testModificarVueloExistente() throws VueloNotFoundException {
        this.am.addVuelo("V123", "2025-04-10 10:00", "2025-04-10 12:00", "A123", "Barcelona", "Madrid");

        // Modificar el vuelo cambiando la hora de salida y llegada
        this.am.addVuelo("V123", "2025-04-11 15:00", "2025-04-11 17:00", "A123", "Barcelona", "Paris");

        Vuelo vueloModificado = am.getVuelo("V123");

        Assert.assertEquals("2025-04-11 15:00", vueloModificado.getHoraSalida());
        Assert.assertEquals("2025-04-11 17:00", vueloModificado.getHoraLlegada());
        Assert.assertEquals("Paris", vueloModificado.getDestino());
    }

    @Test
    public void testOrdenDescargaMaletas() throws VueloNotFoundException {
        this.am.addVuelo("V123", "2025-04-10 10:00", "2025-04-10 12:00", "A123", "Barcelona", "Madrid");

        Maleta maleta1 = new Maleta("user123", "V123");  // Facturada primero
        Maleta maleta2 = new Maleta("user456", "V123");  // Facturada después

        am.facturarMaleta(maleta1);
        am.facturarMaleta(maleta2);

        List<Maleta> maletas = am.getMaletasDeVuelo("V123");

        // LIFO: La última en entrar debe ser la primera en la lista
        Assert.assertEquals("user456", maletas.get(0).getUsuarioId());
        Assert.assertEquals("user123", maletas.get(1).getUsuarioId());
    }

    @Test
    public void testGetAvion() {
        Avion avion = this.am.getAvion("A123");
        Assert.assertNotNull(avion);
        Assert.assertEquals("Boing74", avion.getModelo());
    }





}


