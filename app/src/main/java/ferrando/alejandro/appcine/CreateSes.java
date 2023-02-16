package ferrando.alejandro.appcine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.model.Film;
import ferrando.alejandro.appcine.model.Sala;
import ferrando.alejandro.appcine.model.Sesion;
import ferrando.alejandro.appcine.model.User;
import ferrando.alejandro.appcine.model.tipos.Tipo;
import ferrando.alejandro.appcine.recicler.FilmsActivity;

public class CreateSes extends AppCompatActivity {

    Spinner spinTipoSala, spinSalas, spinFilm;
    EditText hora;

    Button crearSesion, volver;
    List<Sala> listSalas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ses);

        String user = ControllerBD.getInstance(this).getUserApp();
        User u = ControllerBD.getInstance(this).getUser(user);

        spinTipoSala = findViewById(R.id.spiTipo);
        spinFilm = findViewById(R.id.spiFilm);
        spinSalas = findViewById(R.id.spiSala);
        hora = findViewById(R.id.sesHora);

        ArrayAdapter<Tipo> ar = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Tipo.values());
        ArrayAdapter<Film> adFilm = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ControllerBD.getInstance(this).getAllFilms());

        spinTipoSala.setAdapter(ar);
        spinFilm.setAdapter(adFilm);

        spinTipoSala.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listSalas = ControllerBD.getInstance(getApplicationContext()).getAllSalas((Tipo)spinTipoSala.getSelectedItem());
                ArrayAdapter<Sala> adSalas = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, listSalas);
                spinSalas.setAdapter(adSalas);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        crearSesion = findViewById(R.id.butCrearSes);
        volver = findViewById(R.id.butVolCreaSes);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateSes.this, FilmsActivity.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

        crearSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!spinSalas.getSelectedItem().toString().isEmpty() && !spinFilm.getSelectedItem().toString().isEmpty() && !hora.getText().toString().isEmpty()){
                        int salaText = Integer.parseInt(spinSalas.getSelectedItem().toString());
                        Sala sala = ControllerBD.getInstance(getApplicationContext()).getSala(salaText);

                        String peliculaText = spinFilm.getSelectedItem().toString();
                        Film f = ControllerBD.getInstance(getApplicationContext()).getFilm(peliculaText);

                        try{
                            Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(hora.getText().toString());
                            int lastIndex = ControllerBD.getInstance(getApplicationContext()).getLastIndexSesion();
                            lastIndex++;
                            Sesion s = new Sesion();
                            s.setId(lastIndex);
                            s.setHora(date);
                            s.setIdPelicula(f.getTitulo());
                            s.setIdSala(sala.getId());
                            ControllerBD.getInstance(getApplicationContext()).updateSesion(s);
                            Toast.makeText(CreateSes.this, "Sesion creada", Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            Toast.makeText(CreateSes.this, "Hora mal colocada", Toast.LENGTH_SHORT).show();
                        }

                        hora.setText("");
                    }else{
                        Toast.makeText(CreateSes.this, "Rellena todos los campos por favor", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }
}