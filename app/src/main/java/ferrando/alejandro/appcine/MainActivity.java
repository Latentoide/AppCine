package ferrando.alejandro.appcine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.Control;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.controller.StartBD;
import ferrando.alejandro.appcine.db.DB;
import ferrando.alejandro.appcine.model.Sesion;
import ferrando.alejandro.appcine.model.User;
import ferrando.alejandro.appcine.model.tipos.TipoUsu;
import ferrando.alejandro.appcine.recicler.FilmsActivity;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    EditText usuario, password;
    Button iniciar, crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.usuarioLogIn);
        password = findViewById(R.id.passLogIn);

        iniciar = findViewById(R.id.logIn);
        crear = findViewById(R.id.crearLogIn);

        StartBD bd = new StartBD(this);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usu = usuario.getText().toString();
                String pass = password.getText().toString();
                if(!usu.isEmpty() && !pass.isEmpty()){
                    User u = ControllerBD.getInstance(getApplicationContext()).getUser(usu);
                    if(u.getPassword().equals(pass)){
                        ControllerBD.getInstance(getApplicationContext()).setUserApp(u);
                        Intent intent = new Intent(MainActivity.this, FilmsActivity.class);
                        intent.putExtra("usu", u.getUsername());
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Inicio de sesión incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Rellena los dos campos para iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


    }
}