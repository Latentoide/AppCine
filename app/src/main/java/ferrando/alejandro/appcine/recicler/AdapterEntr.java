package ferrando.alejandro.appcine.recicler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ferrando.alejandro.appcine.R;
import ferrando.alejandro.appcine.controller.ControllerBD;
import ferrando.alejandro.appcine.model.AsientoOcupado;
import ferrando.alejandro.appcine.model.Entrada;
import ferrando.alejandro.appcine.model.Sala;
import ferrando.alejandro.appcine.model.tipos.Tipo;

public class AdapterEntr extends RecyclerView.Adapter<AdapterEntr.ViewHolder>{
    private LayoutInflater inflater;
    private View.OnClickListener clicker;
    private List<Entrada> asientoOcupadoList;
    private Context context;

    public AdapterEntr(Context context, List<Entrada> asientoOcupadoList) {
        this.context = context;
        this.asientoOcupadoList = asientoOcupadoList;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public AdapterEntr.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_compra, parent, false);
        view.setOnClickListener(clicker);
        return new AdapterEntr.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEntr.ViewHolder viewHolder, int position) {
        Entrada a = asientoOcupadoList.get(position);
        Sala s = ControllerBD.getInstance(context).getSalaFromSesion(a.getIdSesion());

        if(Tipo.TRESD == s.getTipo()){
            viewHolder.precio.setText("9€");
        }else if(Tipo.CUATRODX == s.getTipo()){
            viewHolder.precio.setText("11€");
        }else if(Tipo.NORMAL == s.getTipo()){
            viewHolder.precio.setText("7€");
        }

        viewHolder.columna.setText(a.getButacaX()+"");
        viewHolder.fila.setText(a.getButacaY()+"");
    }

    @Override
    public int getItemCount() {
        return asientoOcupadoList.size();
    }

    public void setOnClickListener(View.OnClickListener click){
        clicker = click;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fila, columna, precio;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fila = itemView.findViewById(R.id.filaBut);
            columna = itemView.findViewById(R.id.numBut);
            precio = itemView.findViewById(R.id.precioBut);
            this.view = itemView;
        }

        public void setBack(int position) {
            if (position % 2 != 0) {
                view.setBackgroundColor(context.getResources().getColor(R.color.segundo));
            } else {
                view.setBackgroundColor(context.getResources().getColor(R.color.segundoVariante));
            }
        }
    }
}
