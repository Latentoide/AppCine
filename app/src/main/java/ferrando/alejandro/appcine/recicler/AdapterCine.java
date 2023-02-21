package ferrando.alejandro.appcine.recicler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ferrando.alejandro.appcine.R;
import ferrando.alejandro.appcine.model.Film;

public class AdapterCine extends RecyclerView.Adapter<AdapterCine.ViewHolder>  {
    private LayoutInflater inflater;
    private View.OnClickListener clicker;
    private List<Film> listFilm;
    private Context context;

    public AdapterCine(Context context, List<Film> listFilm) {
        this.context = context;
        this.listFilm = listFilm;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_peli, parent, false);
        view.setOnClickListener(clicker);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Film f = listFilm.get(position);

        viewHolder.titulo.setText(f.getTitulo());
        viewHolder.descripcion.setText(f.getDescripcion());
        viewHolder.duracion.setText(f.getDuracion());
        Picasso.get().load(f.getCartelera()).into(viewHolder.imgFilm);
        viewHolder.setBack(position);
    }

    @Override
    public int getItemCount() {
        return listFilm.size();
    }

    public void setOnClickListener(View.OnClickListener click){
        clicker = click;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFilm;
        TextView titulo;
        TextView descripcion;
        TextView duracion;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFilm = itemView.findViewById(R.id.imgPeli);
            titulo = itemView.findViewById(R.id.nomPeli);
            descripcion = itemView.findViewById(R.id.descripcionFilm);
            duracion = itemView.findViewById(R.id.duracion);
            this.view = itemView;
        }

        public void setBack(int position) {
            if (position % 2 != 1) {
                view.setBackgroundColor(context.getResources().getColor(R.color.backPink));
            } else {
                view.setBackgroundColor(context.getResources().getColor(R.color.backYell));
            }
        }
    }
}
