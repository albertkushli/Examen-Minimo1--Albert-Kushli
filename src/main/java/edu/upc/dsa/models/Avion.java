package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Avion {
    private String id;
    private String modelo;
    private String companyia;
    private List<Vuelo> vuelos;

    public Avion() {}

    public Avion(String id, String modelo, String companyia) {
        this.id = id;
        this.modelo = modelo;
        this.companyia = companyia;
        this.vuelos = new ArrayList<>();
    }

    public void addVuelo(Vuelo vuelo) {
        vuelos.removeIf(v -> v.getId().equals(vuelo.getId()));
        vuelos.add(vuelo);
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCompanyia() {
        return companyia;
    }

    public void setCompanyia(String companyia) {
        this.companyia = companyia;
    }
}

