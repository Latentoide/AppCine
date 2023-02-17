package ferrando.alejandro.appcine.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Venta extends RealmObject {
    @PrimaryKey
    int id;
    String idUsuario;
    float importe;
    Date hora;
    int idSala;

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
}
