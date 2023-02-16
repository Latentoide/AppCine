package ferrando.alejandro.appcine.recicler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ferrando.alejandro.appcine.R;
import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.model.Film;
import ferrando.alejandro.appcine.model.Sesion;

public class AdapterHora extends RecyclerView.Adapter<AdapterHora.ViewHolder>{
    private LayoutInflater inflater;
    private View.OnClickListener clicker;
    private List<Sesion> listSesion;
    private Context context;

    public AdapterHora(Context context, List<Sesion> listFilm) {
        this.context = context;
        this.listSesion = listFilm;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public AdapterHora.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_sesion, parent, false);
        view.setOnClickListener(clicker);
        return new AdapterHora.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHora.ViewHolder viewHolder, int position) {
        Sesion f = listSesion.get(position);
        SimpleDateFormat standar;
        String standarFormat;
        Date nowDate = listSesion.get(position).getHora();
        standar = new SimpleDateFormat("HH:mm");
        standarFormat = standar.format(nowDate);
        viewHolder.hora.setText("Hora: " + standarFormat + "\n Sala: " + listSesion.get(position).getIdSala() + "\n Tipo: " + ControllerBD.getInstance(context).getTipoFromSala(ControllerBD.getInstance(context).getSala(f.getIdSala())));
    }

    @Override
    public int getItemCount() {
        return listSesion.size();
    }

    public void setOnClickListener(View.OnClickListener click){
        clicker = click;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView hora;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hora = itemView.findViewById(R.id.horaView);
            this.view = itemView;
        }

    }
}
