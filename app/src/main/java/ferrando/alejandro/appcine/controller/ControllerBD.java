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
import ferrando.alejandro.appcine.model.AsientoOcupado;
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
    public static String userApp;

    private static ControllerBD instance=new ControllerBD();
    public static ControllerBD getInstance(Context context){
        con = DB.getInstance().conectar(context);
        return instance;
    }

    public List<AsientoOcupado> getAllButacasOcupadasDeSala(Sesion s){
        List<Entrada> entradas = getAllEntradasFromSesion(s);
        List<AsientoOcupado> ao = new LinkedList<>();
        for(Entrada e : entradas){
            ao.add(new AsientoOcupado(e.getButacaX(), e.getButacaY(), s.getIdSala()));
        }
        return ao;
    }
    public Sala getSalaFromSesion(int id){
        Sesion s = getSesion(id);
        Sala sala = getSala(s.getIdSala());
        return sala;
    }

    public List<Entrada> getEntradasFromVenta(Venta v){
        Sala sala = getSala(v.getIdSala());
        Sesion s = getSesionFromSala(sala);
        List<Entrada> entradas = getAllEntradasFromSesion(s);
        List<Entrada> nEntradas = new LinkedList<>();
        for(Entrada entrada : entradas){
            if(entrada.getIdVenta() == v.getId()){
                nEntradas.add(entrada);
            }
        }
        return nEntradas;
    }

    public Sesion getSesionFromSala(Sala id){
        List<Sesion> sesiones = getAllSesion();
        for(Sesion s : sesiones){
            if(s.getIdSala() == id.getId()){
                return s;
            }
        }
        return null;
    }
    public List<Entrada> getAllEntradasFromSesion(Sesion sesion){
        return con.where(Entrada.class).equalTo("idSesion", sesion.getId()).findAll();
    }

    public List<Venta> getAllVentas(){
        return con.where(Venta.class).findAll();
    }

    public List<Sesion> getAllHoursSesionsFromFilmToday(Film f){
        Calendar now = new GregorianCalendar();
        Date nowDate = now.getTime();
        List<Sesion> sesionsToShow = new LinkedList<>();
        List<Sesion> sesions = getAllSesion();
        for(Sesion s : sesions){
            if(s.getIdPelicula().equals(f.getTitulo()) && s.getHora().getDay() == nowDate.getDay()){
                sesionsToShow.add(s);
            }
        }
        return sesionsToShow;
    }

    public Tipo getTipoFromSala(Sala s){
        Sala sas = con.where(Sala.class).equalTo("id", s.getId()).findFirst();
        return sas.getTipo();
    }

    public List<Film> getAllFilmCarteleras(){
        return con.where(Film.class).equalTo("isCartelera", true).findAll();
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
    public int getLastIndexVenta(){
        return (int)con.where(Venta.class).count();
    }
    public int getLastIndexEntrada(){
        return (int)con.where(Entrada.class).count();
    }

    public void setUserApp(User u){
        userApp = u.getUsername();
    }

    public String getUserApp(){
        return userApp;
    }

    //CRUD DE TODAS LAS CLASES

    public List<User> getAllUserCount(){
        return con.where(User.class).findAll();
    }

    public User getUser(String username){
        User u = con.where(User.class).equalTo("username", username).findFirst();
        return u;
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

