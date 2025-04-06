package edu.upc.dsa.models;

import edu.upc.dsa.models.Maleta;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Vuelo {
    private String id;
    private String horaSalida;
    private String horaLlegada;
    private String avionId;
    private String origen;
    private String destino;
    private Deque<Maleta> maletas;


    public Vuelo() {
    }

    public Vuelo(String id, String horaSalida, String horaLlegada, String avionId, String origen, String destino) {
        this.id = id;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.avionId = avionId;
        this.origen = origen;
        this.destino = destino;
        this.maletas = new LinkedList<>();
    }

    public void addMaleta(Maleta maleta) {
        this.maletas.push(maleta);
    }

    public void setMaletas(List<Maleta> maletasList) {
        this.maletas = new LinkedList<>(maletasList);
    }

    public Deque<Maleta> getMaletas() {
        return this.maletas;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }

    public String getId() {
        return id;
    }

    public String getAvionId() {
        return avionId;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public String getOrigen() {
        return origen;
    }
}

