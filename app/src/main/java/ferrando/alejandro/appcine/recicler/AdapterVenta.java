package ferrando.alejandro.appcine.recicler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ferrando.alejandro.appcine.R;
import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.model.Sesion;
import ferrando.alejandro.appcine.model.Venta;

public class AdapterVenta extends RecyclerView.Adapter<AdapterVenta.ViewHolder> {
    private LayoutInflater inflater;
    private View.OnClickListener clicker;
    private List<Venta> listVentas;
    private Context context;

    public AdapterVenta(Context context, List<Venta> listVentas) {
        this.context = context;
        this.listVentas = listVentas;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public AdapterVenta.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_venta, parent, false);
        view.setOnClickListener(clicker);
        return new AdapterVenta.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVenta.ViewHolder viewHolder, int position) {
        Venta v = listVentas.get(position);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String formattedDate = df.format(v.getHora());
        viewHolder.horaTotal.setText("Hora: "+ formattedDate);
        viewHolder.idVenta.setText("Id Venta: " + v.getId());
        viewHolder.idEmpleado.setText("Usuario: " + v.getIdUsuario());
        viewHolder.precioTotal.setText("Precio total: " + v.getImporte());
    }

    @Override
    public int getItemCount() {
        return listVentas.size();
    }

    public void setOnClickListener(View.OnClickListener click){
        clicker = click;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idEmpleado, precioTotal, idVenta, horaTotal;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idEmpleado = itemView.findViewById(R.id.idUsuarioVenta);
            precioTotal = itemView.findViewById(R.id.precioTotalVenta);
            idVenta = itemView.findViewById(R.id.idVenta);
            horaTotal = itemView.findViewById(R.id.horaVenta);
            this.view = itemView;
        }

    }
}
