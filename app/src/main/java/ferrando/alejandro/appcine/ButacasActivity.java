package ferrando.alejandro.appcine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.LinkedList;
import java.util.List;

import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.model.AsientoOcupado;
import ferrando.alejandro.appcine.model.Sala;
import ferrando.alejandro.appcine.model.Sesion;
import ferrando.alejandro.appcine.model.TotButacas;
import ferrando.alejandro.appcine.model.User;

public class ButacasActivity extends AppCompatActivity {

    Button comprar, volver;
    List<Integer> listaDeButacas;
    List<AsientoOcupado> asientosOcupados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butacas);
        listaDeButacas = new LinkedList<>();
        asientosOcupados = new LinkedList<>();

        String user = ControllerBD.getInstance(this).getUserApp();
        User u = ControllerBD.getInstance(this).getUser(user);

        int a = getIntent().getExtras().getInt("sesion");
        Sesion s = ControllerBD.getInstance(this).getSesion(a);
        Sala sala = ControllerBD.getInstance(this).getSala(s.getIdSala());

        TableLayout layout = (TableLayout) findViewById(R.id.tableButacas);

        TableLayout contenedor = new TableLayout(this);
        contenedor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        int contadorTotal = 0;
        for (int i = 0; i < sala.getFilas(); i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            tableRow.setGravity(Gravity.CENTER);
            for (int j = 0; j < sala.getColumnas(); j++) {
                CheckBox cb = new CheckBox(this);
                cb.setButtonDrawable(R.drawable.checbox_selector);
                cb.setGravity(Gravity.CENTER);
                cb.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                cb.setId(++contadorTotal);
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        int posibleY = 1;
                        int posibleX = buttonView.getId();

                        for (int k = 2, g = 5; k <= sala.getFilas(); k++, g+=5) {
                            if (posibleX >= 1+g && posibleX <= 5+g){
                                posibleX -= g;
                                posibleY = k;
                            }
                        }

                        AsientoOcupado asiento = new AsientoOcupado(posibleX, posibleY, sala.getId());
                        if(isChecked){
                            asientosOcupados.add(asiento);
                        }else{
                            asientosOcupados.remove(asiento);
                        }
                    }
                });
                tableRow.addView(cb);
            }
            contenedor.addView(tableRow);
        }

        layout.addView(contenedor);

        volver = findViewById(R.id.volverButa);
        comprar = findViewById(R.id.butComprarButacas);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ButacasActivity.this, DetailFilm.class);
                intent.putExtra("usu", user);
                startActivity(intent);
            }
        });

        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ButacasActivity.this, CompraActivity.class);
                intent.putExtra("usu", user);
                TotButacas tot = new TotButacas(asientosOcupados);
                intent.putExtra("butacas", tot);
                intent.putExtra("sesion", a);
                startActivity(intent);
            }
        });

    }
}