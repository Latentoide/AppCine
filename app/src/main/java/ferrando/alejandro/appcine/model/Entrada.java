package ferrando.alejandro.appcine.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Entrada extends RealmObject {
    @PrimaryKey
    int id;
    int idSesion;
    int fila;
    int butaca;

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

    public int getButaca() {
        return butaca;
    }

    public void setButaca(int butaca) {
        this.butaca = butaca;
    }
}
