package ferrando.alejandro.appcine.model;

import java.io.Serializable;

public class AsientoOcupado implements Serializable {
    int x;
    int y;

    int idSala;

    public AsientoOcupado(int x, int y, int idSala){
        this.x=x;
        this.y=y;
        this.idSala = idSala;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public int getIdSala() {
        return idSala;
    }
}
