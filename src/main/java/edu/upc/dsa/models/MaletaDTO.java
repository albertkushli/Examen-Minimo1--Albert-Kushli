package edu.upc.dsa.models;

import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import edu.upc.dsa.util.RandomUtils;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MaletaDTO {
    private String id;
    private String usuarioId;
    private String vueloId;

    public MaletaDTO() {}

    public MaletaDTO(String id, String usuarioId, String vueloId) {
        this();
        this.usuarioId = usuarioId;
        this.vueloId = vueloId;


    }

    private void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

