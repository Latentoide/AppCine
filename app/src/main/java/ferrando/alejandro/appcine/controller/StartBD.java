package ferrando.alejandro.appcine.controller;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import ferrando.alejandro.appcine.db.DB;
import ferrando.alejandro.appcine.model.Entrada;
import ferrando.alejandro.appcine.model.Film;
import ferrando.alejandro.appcine.model.Sala;
import ferrando.alejandro.appcine.model.Sesion;
import ferrando.alejandro.appcine.model.User;
import ferrando.alejandro.appcine.model.Venta;
import ferrando.alejandro.appcine.model.tipos.Tipo;
import ferrando.alejandro.appcine.model.tipos.TipoEdadMin;
import ferrando.alejandro.appcine.model.tipos.TipoGeneros;
import ferrando.alejandro.appcine.model.tipos.TipoUsu;
import io.realm.Realm;

public class StartBD {
    public Realm con;
    List<User> users;
    List<Sala> salas;
    List<Film> peliculas;
    List<Sesion> sesions;

    Context context;

    public StartBD(Context context){
        users = new LinkedList<>();
        sesions = new LinkedList<>();
        salas = new LinkedList<>();
        peliculas = new LinkedList<>();
        con = DB.getInstance().conectar(context);
        this.context = context;
        createBD();
    }

    private boolean checkBD(){
        long count = con.where(User.class).count();
        if(count == 0){
            return true;
        }
        return false;
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
            users.add(u);

            User u1 = new User();
            u1.setDNI("78564738O");
            u1.setApellido("Fernandez");
            u1.setNombre("Paco el");
            u1.setUsername("paco");
            u1.setPassword("paco");
            u1.setRol(TipoUsu.CLIENTE);
            users.add(u1);

            Film f = new Film();
            f.setTitulo("Avatar 2");
            f.setDescripcion("La película narra las mágicosas aventuras de Jaime y sus amigos lanzadores de lanzas azules");
            f.setDuracion("3h 30m");
            f.setCartelera("https://cdn.kinepolis.es/images/ES/65459BAD-CA99-4711-A97B-E049A5FA94D2/HO00003725/0000005757/Avatar:_El_Sentido_del_Agua.jpg?impolicy=Poster");
            f.setGenero(TipoGeneros.ACCION);
            f.setEdad_min(TipoEdadMin.A);
            f.setIsInCartelera(true);

            Film f2 = new Film();
            f2.setTitulo("Quantumania");
            f2.setDescripcion("La familia se encuentra explorando el Reino Cuántico, mientras interactúan con extrañas nuevas criaturas y se embarcan en una aventura que los llevará más allá de los límites de lo que pensaban que era posible.");
            f2.setDuracion("1h 30m");
            f2.setCartelera("https://cdn.kinepolis.es/images/ES/65459BAD-CA99-4711-A97B-E049A5FA94D2/HO00004253/0000006055/Ant-Man_y_la_Avispa:_Quantumanía.jpg");
            f2.setGenero(TipoGeneros.SUPERHEROES);
            f2.setEdad_min(TipoEdadMin.A);
            f2.setIsInCartelera(true);


            Film f3 = new Film();
            f3.setTitulo("¡SHAZAM! LA FURIA DE LOS DIOSES");
            f3.setDescripcion("Billy Batson y sus compañeros adoptados han recibido los poderes de los dioses pero aún están aprendiendo a compaginar sus vidas de adolescentes con sus alter-egos superheróicos");
            f3.setDuracion("2h 30m");
            f3.setCartelera("https://cdn.kinepolis.es/images/ES/65459BAD-CA99-4711-A97B-E049A5FA94D2/HO00003830/0000006208/¡Shazam!_La_Furia_de_los_Dioses.jpg");
            f3.setGenero(TipoGeneros.SUPERHEROES);
            f3.setEdad_min(TipoEdadMin.A);
            f3.setIsInCartelera(true);

            Film f4 = new Film();
            f4.setTitulo("CREED III");
            f4.setDescripcion("Después de dominar el mundo del boxeo, Adonis Creed ha progresado tanto en su carrera como en su vida familiar. Cuando Damian (Jonathan Majors), un amigo de la infancia y antiguo prodigio del boxeo");
            f4.setDuracion("2h 30m");
            f4.setCartelera("https://cdn.kinepolis.es/images/ES/65459BAD-CA99-4711-A97B-E049A5FA94D2/HO00004088/0000006229/Creed_III.jpg");
            f4.setGenero(TipoGeneros.ACCION);
            f4.setEdad_min(TipoEdadMin.A);
            f4.setIsInCartelera(true);

            Film f5 = new Film();
            f5.setTitulo("LA NIÑA DE LA COMUNIÓN");
            f5.setDescripcion("Mayo de 1987. Las campanas de un pequeño pueblo del interior tañen con motivo de una fiesta. La iglesia está preparada para celebrar una misa de primera comunión, entre los niños está Judith, la hermana pequeña de Sara. A partir de la comunión de su hermana pequeña,");
            f5.setDuracion("2h 00m");
            f5.setCartelera("https://cdn.kinepolis.es/images/ES/65459BAD-CA99-4711-A97B-E049A5FA94D2/HO00004251/0000006118/La_Niña_de_La_Comunión.jpg");
            f5.setGenero(TipoGeneros.SUPERHEROES);
            f5.setEdad_min(TipoEdadMin.A);
            f5.setIsInCartelera(true);

            peliculas.add(f);
            peliculas.add(f2);
            peliculas.add(f3);
            peliculas.add(f4);
            peliculas.add(f5);

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


            Sala s5 = new Sala();
            s5.setColumnas(7);
            s5.setFilas(6);
            s5.setId(5);
            s5.setTipo(Tipo.NORMAL);


            Sala s6 = new Sala();
            s6.setColumnas(8);
            s6.setFilas(6);
            s6.setId(6);
            s6.setTipo(Tipo.CUATRODX);


            Sala s7 = new Sala();
            s7.setColumnas(9);
            s7.setFilas(4);
            s7.setId(7);
            s7.setTipo(Tipo.TRESD);
            salas.add(s1);
            salas.add(s2);
            salas.add(s3);
            salas.add(s4);
            salas.add(s5);
            salas.add(s6);
            salas.add(s7);

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
            Calendar today = Calendar.getInstance();
            today.add(Calendar.HOUR, 5);
            Date d = today.getTime();
            Sesion ses = new Sesion(1, 7, "LA NIÑA DE LA COMUNIÓN", d);
            Sesion ses1 = new Sesion(2, 4, "Avatar 2", d);
            Sesion ses2 = new Sesion(3, 1, "Quantumania", d);
            Sesion ses3 = new Sesion(4, 5, "CREED III", d);
            Sesion ses4 = new Sesion(5, 3, "¡SHAZAM! LA FURIA DE LOS DIOSES", d);

            sesions.add(ses);
            sesions.add(ses1);
            sesions.add(ses2);
            sesions.add(ses3);
            sesions.add(ses4);

            createUsers();
            createFilms();
            createSalas();
            createSesions();
        }
    }
    public void createSesions(){
        for(Sesion s : sesions){
            ControllerBD.getInstance(context).updateSesion(s);
        }
    }
    public void createUsers(){
        for(User u : users){
            ControllerBD.getInstance(context).updateUser(u);
        }
    }
    public void createFilms(){
        for(Film f : peliculas){
            ControllerBD.getInstance(context).updateFilm(f);
        }
    }
    public void createSalas(){
        for(Sala s : salas){
            ControllerBD.getInstance(context).updateSala(s);
        }
    }
}
