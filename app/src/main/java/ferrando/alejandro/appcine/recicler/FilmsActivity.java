package ferrando.alejandro.appcine.recicler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    FloatingActionButton createFilm;
    FloatingActionButton createSes;
    FloatingActionButton verAllFilms;
    FloatingActionButton verAllVentas;
    FloatingActionButton verTodo;
    String user;

    TextView clientOrAdmin;

    Button volver;

    boolean isFABOpen;

    AdapterCine adapterCine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);
        isFABOpen = false;
        createFilm = findViewById(R.id.butCreateFilm);
        createSes = findViewById(R.id.butCreateSes);
        verAllFilms = findViewById(R.id.seeAllFilms);
        verAllVentas = findViewById(R.id.buttonVerVentas);
        clientOrAdmin = findViewById(R.id.clientOrAdmin);

        user = ControllerBD.getInstance(this).getUserApp();
        User u = ControllerBD.getInstance(this).getUser(user);
        verTodo = findViewById(R.id.verTodo);
        if(!(u.getRol() == TipoUsu.ADMIN)){
            createSes.setVisibility(View.INVISIBLE);
            createFilm.setVisibility(View.INVISIBLE);
            clientOrAdmin.setText("Vista cliente");
        }
        clientOrAdmin.setText("Vista admin");

        boolean isFilmsOnly = getIntent().getBooleanExtra("isOnly", false);
        reciclerFilms = findViewById(R.id.filmsRecicler);

        volver = findViewById(R.id.butVolverVerPelis);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilmsActivity.this, FilmsActivity.class);
                intent.putExtra("isOnly", false);
                startActivity(intent);
                finish();
            }
        });

        filmList = (isFilmsOnly)?ControllerBD.getInstance(getApplicationContext()).getAllFilms():ControllerBD.getInstance(getApplicationContext()).getAllFilmCarteleras();

        AdapterCine adapterCine = new AdapterCine(this, filmList);
        adapterCine.setOnClickListener(this);

        if(isFilmsOnly){

            ItemTouchHelper mIth = new ItemTouchHelper(
                    new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
                        @Override
                        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                            // move item in `fromPos` to `toPos` in adapter.
                            recyclerView.getAdapter().notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                            return true;// true if moved, false otherwise
                        }
                        @Override
                        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(FilmsActivity.this);
                            builder.setMessage("Seguro que quieres borrar la película")
                                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Film f = filmList.get(viewHolder.getAdapterPosition());
                                            Toast.makeText(FilmsActivity.this, "Se ha eliminado la pelicula: " + f.getTitulo(), Toast.LENGTH_SHORT).show();
                                            ControllerBD.getInstance(getApplication()).deleteFilm(f.getTitulo());
                                            adapterCine.notifyItemRemoved(direction);
                                            adapterCine.notifyDataSetChanged();
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            Toast.makeText(FilmsActivity.this, "Operacion cancelada", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            builder.create().show();
                        }
                    });
            mIth.attachToRecyclerView(reciclerFilms);
        }else{
            volver.setVisibility(View.INVISIBLE);
        }
        reciclerFilms.setAdapter(adapterCine);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        reciclerFilms.setLayoutManager(linearLayoutManager);

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

        verTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });
    }
    private void showFABMenu(){
        isFABOpen=true;
        createFilm.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        createSes.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
        verAllFilms.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
        verAllVentas.animate().translationY(-getResources().getDimension(R.dimen.standard_205));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        createFilm.animate().translationY(0);
        createSes.animate().translationY(0);
        verAllFilms.animate().translationY(0);
        verAllVentas.animate().translationY(0);
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