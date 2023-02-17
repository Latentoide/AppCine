package ferrando.alejandro.appcine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.model.Film;
import ferrando.alejandro.appcine.model.User;
import ferrando.alejandro.appcine.model.tipos.TipoEdadMin;
import ferrando.alejandro.appcine.model.tipos.TipoGeneros;
import ferrando.alejandro.appcine.recicler.FilmsActivity;

public class CreateFilm extends AppCompatActivity {

    EditText titulo, duracion, descripcion, url;
    Spinner genero, minEdad;

    Button volver, crearPeli;
    CheckBox cb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_film);

        String user = ControllerBD.getInstance(this).getUserApp();
        User u = ControllerBD.getInstance(this).getUser(user);

        ArrayAdapter<TipoGeneros> adGenero = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, TipoGeneros.values());
        ArrayAdapter<TipoEdadMin> adMinEdad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, TipoEdadMin.values());

        genero = findViewById(R.id.spinGeneroFilmCreate);
        minEdad = findViewById(R.id.spinEdadFilmCreate);
        cb = findViewById(R.id.isInCartelera);

        genero.setAdapter(adGenero);
        minEdad.setAdapter(adMinEdad);

        titulo = findViewById(R.id.tituloFilmCreate);
        duracion = findViewById(R.id.duracionFilmCreate);
        descripcion = findViewById(R.id.descripcionFilmCreate);
        url = findViewById(R.id.urlImgFilmCreate);

        volver = findViewById(R.id.butVolverFilm);
        crearPeli = findViewById(R.id.burFilmCreate);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateFilm.this, FilmsActivity.class);
                intent.putExtra("usu", user);
                startActivity(intent);
                finish();
            }
        });

        crearPeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!duracion.getText().toString().isEmpty() && !duracion.getText().toString().isEmpty() && !descripcion.getText().toString().isEmpty() && !url.getText().toString().isEmpty()){
                    Film f = new Film();
                    f.setGenero(genero.getSelectedItem().toString());
                    f.setDuracion(duracion.getText().toString());
                    f.setTitulo(titulo.getText().toString());
                    f.setDescripcion(descripcion.getText().toString());
                    f.setEdad_min((TipoEdadMin) minEdad.getSelectedItem());
                    f.setCartelera(url.getText().toString());
                    f.setIsInCartelera(cb.isChecked());

                    ControllerBD.getInstance(getApplicationContext()).updateFilm(f);
                    Toast.makeText(CreateFilm.this, "Pelicula creada", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CreateFilm.this, "Rellan todos los campos", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}