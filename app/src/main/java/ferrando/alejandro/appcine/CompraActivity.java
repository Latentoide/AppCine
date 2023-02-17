package ferrando.alejandro.appcine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.model.AsientoOcupado;
import ferrando.alejandro.appcine.model.Entrada;
import ferrando.alejandro.appcine.model.Film;
import ferrando.alejandro.appcine.model.Sala;
import ferrando.alejandro.appcine.model.Sesion;
import ferrando.alejandro.appcine.model.TotButacas;
import ferrando.alejandro.appcine.model.User;
import ferrando.alejandro.appcine.model.Venta;
import ferrando.alejandro.appcine.model.tipos.Tipo;
import ferrando.alejandro.appcine.recicler.AdapterBut;
import ferrando.alejandro.appcine.recicler.FilmsActivity;

public class CompraActivity extends AppCompatActivity{

    List<AsientoOcupado> butacas;
    TextView totalCompra;
    Button comprar, volver;
    RecyclerView rcView;
    Sesion sesion;
    User u;
    Film f;
    int totalDinero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        totalCompra = findViewById(R.id.TotalCompra);
        comprar = findViewById(R.id.realCompra);
        rcView = findViewById(R.id.reciclerCompra);
        volver = findViewById(R.id.butVolverCompra);

        butacas = ((TotButacas) getIntent().getSerializableExtra("butacas") ).getButacas();
        f = ControllerBD.getInstance(this).getFilm(getIntent().getStringExtra("film"));
        String user = ControllerBD.getInstance(this).getUserApp();
        u = ControllerBD.getInstance(this).getUser(user);
        sesion = ControllerBD.getInstance(this).getSesion(getIntent().getExtras().getInt("sesion"));
        Sala s = ControllerBD.getInstance(this).getSala(butacas.get(0).getIdSala());

        totalDinero = 0;

        AdapterBut adapterBut = new AdapterBut(this, butacas);

        rcView.setAdapter(adapterBut);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcView.setLayoutManager(linearLayoutManager);

        for(AsientoOcupado a : butacas){
            if(s.getTipo() == Tipo.NORMAL){
                totalDinero += 7;
            }else if(s.getTipo() == Tipo.TRESD){
                totalDinero += 9;
            }else if(s.getTipo() == Tipo.CUATRODX){
                totalDinero += 11;
            }
        }

        totalCompra.setText("Usuario: " + user + " TOTAL COMPRA: " + totalDinero);

        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Venta venta = new Venta();

                Date c = Calendar.getInstance().getTime();

                venta.setHora(c);
                venta.setIdUsuario(u.getUsername());
                venta.setImporte(totalDinero);
                venta.setId(ControllerBD.getInstance(getApplicationContext()).getLastIndexVenta());

                for(AsientoOcupado asiento : butacas){
                    Entrada entrada = new Entrada();
                    entrada.setButacaX(asiento.getX());
                    entrada.setButacaY(asiento.getY());
                    entrada.setIdVenta(venta.getId());
                    entrada.setIdSesion(sesion.getId());
                    entrada.setId(ControllerBD.getInstance(getApplicationContext()).getLastIndexEntrada());

                    ControllerBD.getInstance(getApplicationContext()).updateEntrada(entrada);
                }
                ControllerBD.getInstance(getApplicationContext()).updateVenta(venta);

                Toast.makeText(CompraActivity.this, "Compra realizada", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CompraActivity.this, FilmsActivity.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompraActivity.this, ButacasActivity.class);
                intent.putExtra("usu", user);
                intent.putExtra("sesion", sesion.getId());
                intent.putExtra("film", f.getTitulo());
                startActivity(intent);
                finish();
            }
        });
    }
}