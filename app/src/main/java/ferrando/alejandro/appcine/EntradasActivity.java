package ferrando.alejandro.appcine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.model.AsientoOcupado;
import ferrando.alejandro.appcine.model.Entrada;
import ferrando.alejandro.appcine.model.Venta;
import ferrando.alejandro.appcine.recicler.AdapterBut;
import ferrando.alejandro.appcine.recicler.AdapterEntr;
import ferrando.alejandro.appcine.recicler.FilmsActivity;

public class EntradasActivity extends AppCompatActivity {
    TextView hora, total, idVenta, usuario;
    RecyclerView rcView;
    List<Entrada> butacas;
    Venta v;
    Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);
        rcView = findViewById(R.id.entradaRecicler);

        v = ControllerBD.getInstance(this).getVenta(getIntent().getExtras().getInt("venta"));
        butacas = ControllerBD.getInstance(this).getEntradasFromVenta(v);

        AdapterEntr adapterBut = new AdapterEntr(this, butacas);

        rcView.setAdapter(adapterBut);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcView.setLayoutManager(linearLayoutManager);

        hora = findViewById(R.id.HoraEntradaView);
        total = findViewById(R.id.TotalEntradasView);
        usuario = findViewById(R.id.idUsuarioEntradaView);
        idVenta = findViewById(R.id.idVentaEntradaView);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String formattedDate = df.format(v.getHora());

        hora.setText("Hora: "+ formattedDate);
        usuario.setText("Usuario: " + v.getIdUsuario());
        idVenta.setText("Id Venta: " + v.getId());
        total.setText("Precio total: " + v.getImporte());

        but = findViewById(R.id.butVolverEntradaView);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntradasActivity.this, VentasActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}