package ferrando.alejandro.appcine.model;

import androidx.annotation.NonNull;

import ferrando.alejandro.appcine.model.tipos.Tipo;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Sala extends RealmObject {
    @PrimaryKey
    int id;
    int filas;
    int columnas;
    int tipo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public Tipo getTipo() {
        switch (tipo){
            case 1:
                return Tipo.TRES;
            case 2:
                return Tipo.CUATROX;
            default:
                return Tipo.NORMAL;
        }
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo.getTipo();
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
