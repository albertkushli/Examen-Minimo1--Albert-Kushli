package edu.upc.dsa.models;


public class Vuelo {
    private String id;
    private String horaSalida;
    private String horaLlegada;
    private String avionId;
    private String origen;
    private String destino;

    public void Vuelo() {}

    public Vuelo(String id, String horaSalida, String horaLlegada, String avionId, String origen, String destino) {
        this.id = id;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.avionId = avionId;
        this.origen = origen;
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

