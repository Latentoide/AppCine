package ferrando.alejandro.appcine.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Entrada extends RealmObject {
    @PrimaryKey
    int id;
    int idSesion;
    int fila;
    int butacaX;
    int butacaY;
    int idVenta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getButacaX() {
        return butacaX;
    }

    public void setButacaX(int butacaX) {
        this.butacaX = butacaX;
    }

    public int getButacaY() {
        return butacaY;
    }

    public void setButacaY(int butacaY) {
        this.butacaY = butacaY;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
}
