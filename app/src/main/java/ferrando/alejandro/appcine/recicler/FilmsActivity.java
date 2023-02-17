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
import ferrando.alejandro.appcine.DetailFilm;
import ferrando.alejandro.appcine.R;
import ferrando.alejandro.appcine.SignUpActivity;
import ferrando.alejandro.appcine.VentasActivity;
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
    FloatingActionButton verAllFilms;
    FloatingActionButton verAllVentas;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);
        createTr = findViewById(R.id.butCreateTra);
        createFilm = findViewById(R.id.butCreateFilm);
        createSes = findViewById(R.id.butCreateSes);
        verAllFilms = findViewById(R.id.seeAllFilms);
        verAllVentas = findViewById(R.id.buttonVerVentas);
        user = getIntent().getStringExtra("usu");
        User u = ControllerBD.getInstance(this).getUser(user);
        if(!(u.getRol() == TipoUsu.ADMIN)){
            createTr.setVisibility(View.INVISIBLE);
            createSes.setVisibility(View.INVISIBLE);
            createFilm.setVisibility(View.INVISIBLE);
        }
        boolean isFilmsOnly = false;
        isFilmsOnly = getIntent().getBooleanExtra("isOnly", false);
        if(isFilmsOnly){
            filmList = ControllerBD.getInstance(getApplicationContext()).getAllFilms();
        }else{
            filmList = ControllerBD.getInstance(getApplicationContext()).getAllFilmCarteleras();
        }

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
                finish();
            }
        });

        createFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilmsActivity.this, CreateFilm.class);
                intent.putExtra("usu", user);
                startActivity(intent);
                finish();
            }
        });

        createSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilmsActivity.this, CreateSes.class);
                intent.putExtra("usu", user);
                startActivity(intent);
                finish();
            }
        });

        verAllFilms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilmsActivity.this, FilmsActivity.class);
                intent.putExtra("usu", user);
                intent.putExtra("isOnly", true);
                startActivity(intent);
                finish();
            }
        });

        verAllVentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilmsActivity.this, VentasActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(FilmsActivity.this, DetailFilm.class);
        int i = reciclerFilms.getChildAdapterPosition(v);
        myIntent.putExtra("film", filmList.get(i).getTitulo());
        myIntent.putExtra("usu", user);
        startActivity(myIntent);
        finish();
    }
}