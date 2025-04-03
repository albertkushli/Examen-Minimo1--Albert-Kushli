package edu.upc.dsa.models;

public class Avion {
    private String id;
    private String modelo;
    private String companyia;

    public Avion() {}

    public Avion(String id, String modelo, String companyia) {
        this.id = id;
        this.modelo = modelo;
        this.companyia = companyia;
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

