package edu.upc.dsa.models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import java.util.UUID;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Maleta {
    private String usuarioId;
    private String vueloId;
    private String id;


    public Maleta() {}


    public Maleta(String usuarioId, String vueloId) {
        this();
        this.usuarioId = usuarioId;
        this.vueloId = vueloId;
        generateId();

    }

    private void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public String getId() {
        return id;
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

