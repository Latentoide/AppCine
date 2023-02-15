package ferrando.alejandro.appcine.model.tipos;

import androidx.annotation.NonNull;

public enum TipoGeneros {
    ACCION("Accion"), COMEDIA("Comedia");
    private String num;

    TipoGeneros(String id){
        num = id;
    }

    public String getNum() {
        return num;
    }

    @NonNull
    @Override
    public String toString() {
        return num;
    }
}
