package edu.upc.dsa.models;


public class Maleta {
    private String usuarioId;
    private String vueloId;

    public Maleta() {}


    public Maleta(String usuarioId, String vueloId) {
        this.usuarioId = usuarioId;
        this.vueloId = vueloId;
    }


    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getVueloId() {
        return vueloId;
    }

    public void setVueloId(String vueloId) {
        this.vueloId = vueloId;
    }
}

