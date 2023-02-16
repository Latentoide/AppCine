package ferrando.alejandro.appcine.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

import ferrando.alejandro.appcine.model.tipos.TipoEdadMin;
import ferrando.alejandro.appcine.model.tipos.TipoGeneros;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Film extends RealmObject implements Serializable {
    @PrimaryKey
    String titulo;
    String duracion;
    String descripcion;
    String genero;
    long edad_min;
    String url;
    boolean isCartelera;

    public boolean getIsInCartelera() {
        return isCartelera;
    }

    public void setIsInCartelera(boolean isCartelera) {
        this.isCartelera = isCartelera;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoGeneros getGenero() {
        switch (genero){
            case "Accion":
                return TipoGeneros.ACCION;
            case "Comedia":
                return TipoGeneros.COMEDIA;
            default:
                return TipoGeneros.ACCION;
        }
    }

    public void setGenero(TipoGeneros genero) {
        this.genero = genero.getNum();
    }

    public long getEdad_min() {
        return edad_min;
    }

    public void setEdad_min(TipoEdadMin edad_min) {
        this.edad_min = edad_min.getNumRes();
    }

    public String getCartelera() {
        return url;
    }

    public void setCartelera(String cartelera) {
        this.url = cartelera;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEdad_min(long edad_min) {
        this.edad_min = edad_min;
    }

    @NonNull
    @Override
    public String toString() {
        return titulo;
    }
}
