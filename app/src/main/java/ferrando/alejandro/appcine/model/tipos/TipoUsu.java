package ferrando.alejandro.appcine.model.tipos;

import androidx.annotation.NonNull;

public enum TipoUsu {
    CLIENTE(1), ADMIN(2), TRABAJADOR(3);
    private int num;
    TipoUsu(int id){
        num=id;
    }

    public int getNum() {
        return num;
    }

}
