package ferrando.alejandro.appcine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.model.Film;
import ferrando.alejandro.appcine.model.Venta;
import ferrando.alejandro.appcine.recicler.AdapterCine;
import ferrando.alejandro.appcine.recicler.AdapterVenta;
import ferrando.alejandro.appcine.recicler.FilmsActivity;

public class VentasActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView reciclerFilms;
    List<Venta> ventaList;
    Button volverBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        volverBut = findViewById(R.id.volverButVentas);
        reciclerFilms = findViewById(R.id.ventaRecicler);

        ventaList = ControllerBD.getInstance(this).getAllVentas();

        AdapterVenta adapterVenta = new AdapterVenta(this, ventaList);
        adapterVenta.setOnClickListener(this);

        reciclerFilms.setAdapter(adapterVenta);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        reciclerFilms.setLayoutManager(linearLayoutManager);


        volverBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VentasActivity.this, FilmsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = reciclerFilms.getChildAdapterPosition(v);
        Venta va = ventaList.get(i);
        Intent intent = new Intent(VentasActivity.this, EntradasActivity.class);
        intent.putExtra("venta", va.getId());
        startActivity(intent);
        finish();
    }
}