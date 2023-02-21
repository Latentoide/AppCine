package ferrando.alejandro.appcine.model;

import java.io.Serializable;

import ferrando.alejandro.appcine.model.tipos.TipoUsu;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject implements Serializable {
    @PrimaryKey
    String username;
    String DNI;
    String nombre;
    String apellido;
    int rol;
    String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoUsu getRol() {
        switch (rol) {
            case 1:
                return TipoUsu.CLIENTE;
            default:
                return TipoUsu.ADMIN;
        }
    }

    public void setRol(TipoUsu rol) {
        this.rol = rol.getNum();
    }
}
