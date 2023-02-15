package ferrando.alejandro.appcine.recicler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ferrando.alejandro.appcine.CreateFilm;
import ferrando.alejandro.appcine.CreateSes;
import ferrando.alejandro.appcine.R;
import ferrando.alejandro.appcine.SignUpActivity;
import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.model.Film;
import ferrando.alejandro.appcine.model.User;
import ferrando.alejandro.appcine.model.tipos.TipoUsu;

public class FilmsActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView reciclerFilms;
    List<Film> filmList;
    FloatingActionButton createTr;
    FloatingActionButton createFilm;
    FloatingActionButton createSes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);
        createTr = findViewById(R.id.butCreateTra);
        createFilm = findViewById(R.id.butCreateFilm);
        createSes = findViewById(R.id.butCreateSes);
        String user = getIntent().getStringExtra("usu");
        User u = ControllerBD.getInstance(this).getUser(user);
        if(!(u.getRol() == TipoUsu.ADMIN)){
            createTr.setVisibility(View.INVISIBLE);
        }

        filmList = ControllerBD.getInstance(getApplicationContext()).getAllFilmCarteleras();

        reciclerFilms = findViewById(R.id.filmsRecicler);

        AdapterCine adapterCine = new AdapterCine(this, filmList);
        adapterCine.setOnClickListener(this);

        reciclerFilms.setAdapter(adapterCine);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        reciclerFilms.setLayoutManager(linearLayoutManager);

        createTr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilmsActivity.this, SignUpActivity.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

        createFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilmsActivity.this, CreateFilm.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

        createSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilmsActivity.this, CreateSes.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}