package ferrando.alejandro.appcine.db;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DB {
    private Realm con;
    public static DB instance=new DB();
    public static DB getInstance(){
        return instance;
    }



    public Realm conectar(Context context){
        if(con==null){
            Realm.init(context);
            String nomBD = "cinema";
            RealmConfiguration config = new RealmConfiguration.Builder().name(nomBD)
                    .schemaVersion(2).deleteRealmIfMigrationNeeded().build();
            con = Realm.getInstance(config);
        }
        return con;
    }
}
