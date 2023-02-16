package ferrando.alejandro.appcine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.db.DB;
import ferrando.alejandro.appcine.model.User;
import ferrando.alejandro.appcine.model.tipos.TipoUsu;
import ferrando.alejandro.appcine.recicler.FilmsActivity;
import io.realm.Realm;

public class SignUpActivity extends AppCompatActivity {
    EditText password, repassword, username, nombre, dni, apellido;
    Button but;

    Realm con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        password = findViewById(R.id.passwordSignUp);
        repassword = findViewById(R.id.rePasswordSignUp);
        username = findViewById(R.id.userSignUp);
        nombre = findViewById(R.id.nombreSignUp);
        dni = findViewById(R.id.DNISingUp);
        apellido = findViewById(R.id.apellidoSingUp);

        String user = getIntent().getStringExtra("usu");

        con = DB.getInstance().conectar(this);
        but = findViewById(R.id.butSignUp);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ur = "";

                if(user != null){
                    ur = ControllerBD.getInstance(getApplicationContext()).getUserApp();
                }
                String nombreText = nombre.getText().toString();
                String passText = password.getText().toString();
                String rePass = repassword.getText().toString();
                String userText = username.getText().toString();
                String dniText = dni.getText().toString();
                String apellidoText = apellido.getText().toString();
                if(passText.equals(rePass)){
                    long count = con.where(User.class).equalTo("username", userText).count();
                    if(count == 0){
                        User u = new User();
                        u.setPassword(passText);
                        u.setUsername(userText);
                        u.setNombre(nombreText);
                        u.setApellido(apellidoText);
                        u.setDNI(dniText);
                        if(u.getRol() == TipoUsu.ADMIN){
                            u.setRol(TipoUsu.ADMIN);
                        }else{
                            u.setRol(TipoUsu.CLIENTE);
                        }

                        con.beginTransaction();
                        con.copyToRealmOrUpdate(u);
                        con.commitTransaction();

                        Intent intent = new Intent(SignUpActivity.this, FilmsActivity.class);
                        if(user != null){
                            intent.putExtra("usu",ur);
                        }else{
                            ControllerBD.getInstance(getApplicationContext()).setUserApp(u);
                            intent.putExtra("usu",u);
                        }
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignUpActivity.this, "Nombre de usuario ya en uso", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignUpActivity.this, "Las contraseñas no son correctas", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}