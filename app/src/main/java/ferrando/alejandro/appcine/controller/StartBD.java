package ferrando.alejandro.appcine.controller;

import android.content.Context;

import ferrando.alejandro.appcine.db.DB;
import ferrando.alejandro.appcine.model.Film;
import ferrando.alejandro.appcine.model.Sala;
import ferrando.alejandro.appcine.model.User;
import ferrando.alejandro.appcine.model.tipos.Tipo;
import ferrando.alejandro.appcine.model.tipos.TipoEdadMin;
import ferrando.alejandro.appcine.model.tipos.TipoGeneros;
import ferrando.alejandro.appcine.model.tipos.TipoUsu;
import io.realm.Realm;

public class StartBD {
    public Realm con;

    public StartBD(Context context){
        con = DB.getInstance().conectar(context);
        createBD();
    }

    private boolean checkBD(){
        long count = con.where(User.class).count();
        if(count == 0){
            return true;
        }
        //TODO CAMBIAS ETO
        return true;
    }

    public void createBD(){
        if(checkBD()){
            User u = new User();
            u.setDNI("24569780I");
            u.setApellido("Cocio");
            u.setNombre("Manolo el");
            u.setUsername("admin");
            u.setPassword("admin");
            u.setRol(TipoUsu.ADMIN);

            Film f = new Film();
            f.setTitulo("Las locuras de Jaime");
            f.setDescripcion("La película narra las mágicosas aventuras de Jaime y sus amigos lanzadores de lanzas");
            f.setDuracion("1h 30m");
            f.setCartelera("https://img2.rtve.es/imagenes/travesuras-jaimito/1650538689821.png");
            f.setGenero(TipoGeneros.COMEDIA);
            f.setEdad_min(TipoEdadMin.A);
            f.setIsInCartelera(true);

            Sala s1 = new Sala();
            s1.setColumnas(5);
            s1.setFilas(5);
            s1.setId(1);
            s1.setTipo(Tipo.CUATRODX);

            Sala s2 = new Sala();
            s2.setColumnas(5);
            s2.setFilas(5);
            s2.setId(2);
            s2.setTipo(Tipo.TRESD);

            Sala s3 = new Sala();
            s3.setColumnas(5);
            s3.setFilas(5);
            s3.setId(3);
            s3.setTipo(Tipo.NORMAL);

            Sala s4 = new Sala();
            s4.setColumnas(5);
            s4.setFilas(5);
            s4.setId(4);
            s4.setTipo(Tipo.NORMAL);

            con.beginTransaction();
            con.copyToRealmOrUpdate(u);
            con.copyToRealmOrUpdate(f);
            con.copyToRealmOrUpdate(s1);
            con.copyToRealmOrUpdate(s2);
            con.copyToRealmOrUpdate(s3);
            con.copyToRealmOrUpdate(s4);
            con.commitTransaction();
        }
    }
}
