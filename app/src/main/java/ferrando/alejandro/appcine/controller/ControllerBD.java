package ferrando.alejandro.appcine.controller;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import ferrando.alejandro.appcine.db.DB;
import ferrando.alejandro.appcine.model.Entrada;
import ferrando.alejandro.appcine.model.Film;
import ferrando.alejandro.appcine.model.Sala;
import ferrando.alejandro.appcine.model.Sesion;
import ferrando.alejandro.appcine.model.User;
import ferrando.alejandro.appcine.model.Venta;
import ferrando.alejandro.appcine.model.tipos.Tipo;
import io.realm.Realm;

public class ControllerBD {
    public static Realm con;

    private static ControllerBD instance=new ControllerBD();
    public static ControllerBD getInstance(Context context){
        con = DB.getInstance().conectar(context);
        return instance;
    }

    public List<Film> getAllFilmCarteleras(){
        SimpleDateFormat standar;
        String standarFormat;
        Calendar now = new GregorianCalendar();
        Date nowDate = now.getTime();
        standar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        standarFormat = standar.format( nowDate );

        List<Sesion> sesions = getAllSesion();
        List<Film> filmList = getAllFilms();
        List<Film> filmToExport = new LinkedList<>();
        for(Film f : filmList){
            for(Sesion s : sesions){
                if(f.getTitulo().equals(s.getIdPelicula())){
                    filmToExport.add(f);
                }
            }
        }
        return filmToExport;
    }

    public List<Film> getAllFilms(){
        return con.where(Film.class).findAll();
    }

    public List<Sesion> getAllSesion(){
        return con.where(Sesion.class).findAll();
    }

    public List<Sala> getAllSalas(Tipo tipo){
        return con.where(Sala.class).equalTo("tipo", tipo.getTipo()).findAll();
    }
    public int getLastIndexSesion(){
        return (int)con.where(Sesion.class).count();
    }

    //CRUD DE TODAS LAS CLASES

    public User getUser(String username){
        return con.where(User.class).equalTo("username", username).findFirst();
    }
    public Entrada getEntrada(int id){
        return con.where(Entrada.class).equalTo("id", id).findFirst();
    }
    public Film getFilm(String titulo){
        return con.where(Film.class).equalTo("titulo", titulo).findFirst();
    }
    public Sala getSala(int id){
        return con.where(Sala.class).equalTo("id", id).findFirst();
    }
    public Sesion getSesion(int id){
        return con.where(Sesion.class).equalTo("id", id).findFirst();
    }
    public Venta getVenta(int id){
        return con.where(Venta.class).equalTo("id", id).findFirst();
    }

    public void postUser(User u){
        con.beginTransaction();
        con.copyToRealm(u);
        con.commitTransaction();
    }
    public void postEntrada(Entrada e){
        con.beginTransaction();
        con.copyToRealm(e);
        con.commitTransaction();
    }
    public void postFilm(Film f){
        con.beginTransaction();
        con.copyToRealm(f);
        con.commitTransaction();
    }
    public void postSala(Sala s){
        con.beginTransaction();
        con.copyToRealm(s);
        con.commitTransaction();
    }
    public void postSesion(Sesion s){
        con.beginTransaction();
        con.copyToRealm(s);
        con.commitTransaction();
    }
    public void postVenta(Venta v){
        con.beginTransaction();
        con.copyToRealm(v);
        con.commitTransaction();
    }

    public void updateUser(User u){
        con.beginTransaction();
        con.copyToRealmOrUpdate(u);
        con.commitTransaction();
    }
    public void updateEntrada(Entrada e){
        con.beginTransaction();
        con.copyToRealmOrUpdate(e);
        con.commitTransaction();
    }
    public void updateFilm(Film f){
        con.beginTransaction();
        con.copyToRealmOrUpdate(f);
        con.commitTransaction();
    }
    public void updateSala(Sala s){
        con.beginTransaction();
        con.copyToRealmOrUpdate(s);
        con.commitTransaction();
    }
    public void updateSesion(Sesion s){
        con.beginTransaction();
        con.copyToRealmOrUpdate(s);
        con.commitTransaction();
    }
    public void updateVenta(Venta v){
        con.beginTransaction();
        con.copyToRealmOrUpdate(v);
        con.commitTransaction();
    }

    public void deleteUser(String username){
        User u;
        if((u = getUser(username)) != null){
            con.beginTransaction();
            u.deleteFromRealm();
            con.commitTransaction();
        }
    }
    public void deleteEntrada(int id){
        Entrada e;
        if((e = getEntrada(id)) != null){
            con.beginTransaction();
            e.deleteFromRealm();
            con.commitTransaction();
        }
    }
    public void deleteFilm(String titulo){
        Film f;
        if((f = getFilm(titulo)) != null){
            con.beginTransaction();
            f.deleteFromRealm();
            con.commitTransaction();
        }
    }
    public void deleteSala(int id){
        Sala s;
        if((s = getSala(id)) != null){
            con.beginTransaction();
            s.deleteFromRealm();
            con.commitTransaction();
        }
    }
    public void deleteSesion(int id){
        Sesion s;
        if((s = getSesion(id)) != null){
            con.beginTransaction();
            s.deleteFromRealm();
            con.commitTransaction();
        }
    }
    public void deleteVenta(int id){
        Venta v;
        if((v = getVenta(id)) != null){
            con.beginTransaction();
            v.deleteFromRealm();
            con.commitTransaction();
        }
    }




}

