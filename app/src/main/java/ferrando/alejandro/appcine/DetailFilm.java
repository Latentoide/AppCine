package ferrando.alejandro.appcine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.model.Film;
import ferrando.alejandro.appcine.model.Sesion;
import ferrando.alejandro.appcine.model.User;
import ferrando.alejandro.appcine.recicler.AdapterHora;
import ferrando.alejandro.appcine.recicler.FilmsActivity;

public class DetailFilm extends AppCompatActivity implements View.OnClickListener {
    RecyclerView reciclerSesions;
    List<Sesion> sesionList;
    Film f;
    String user;

    ImageView img;
    TextView titulo, duracion, descripcion;
    Button volver;

    CheckBox ponerCartelera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        user = ControllerBD.getInstance(this).getUserApp();
        User u = ControllerBD.getInstance(this).getUser(user);
        String film = getIntent().getStringExtra("film");
        f = ControllerBD.getInstance(this).getFilm(film);

        ponerCartelera = findViewById(R.id.ponerQuitarCartelera);

        sesionList = ControllerBD.getInstance(this).getAllHoursSesionsFromFilmToday(f);
        reciclerSesions = findViewById(R.id.sesionRecicler);
        AdapterHora adapterHora = new AdapterHora(this, sesionList);
        adapterHora.setOnClickListener(this);

        reciclerSesions.setAdapter(adapterHora);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(this,2);
        reciclerSesions.setLayoutManager(linearLayoutManager);
        img = findViewById(R.id.imgDescFilm);
        titulo = findViewById(R.id.filmTitleDesc);
        duracion = findViewById(R.id.durFilmDesc);
        descripcion = findViewById(R.id.descFilmDesc);
        Picasso.get().load(f.getCartelera()).into(img);
        titulo.setText(f.getTitulo());
        duracion.setText(f.getDuracion());
        descripcion.setText(f.getDescripcion());
        ponerCartelera.setChecked(f.getIsInCartelera());

        volver = findViewById(R.id.volverDetailFilm);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailFilm.this, FilmsActivity.class);
                intent.putExtra("usu", user);
                startActivity(intent);
                finish();
            }
        });

        ponerCartelera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Film fa = new Film();
                fa.setCartelera(f.getCartelera());
                fa.setIsInCartelera(f.getIsInCartelera());
                fa.setEdad_min(f.getEdad_min());
                fa.setDescripcion(f.getDescripcion());
                fa.setTitulo(f.getTitulo());
                fa.setDuracion(f.getDuracion());
                fa.setGenero(f.getGenero());
                fa.setIsInCartelera(isChecked);
                ControllerBD.getInstance(getApplicationContext()).updateFilm(fa);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = reciclerSesions.getChildAdapterPosition(v);
        Intent intent = new Intent(DetailFilm.this, ButacasActivity.class);
        intent.putExtra("sesion", sesionList.get(i).getId());
        intent.putExtra("usu", user);
        intent.putExtra("film", f.getTitulo());
        startActivity(intent);
        finish();
    }
}