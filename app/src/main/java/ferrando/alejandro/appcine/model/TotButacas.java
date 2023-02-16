package ferrando.alejandro.appcine.model;

import java.io.Serializable;
import java.util.List;

public class TotButacas implements Serializable {
    List<AsientoOcupado> butacas;

    public TotButacas(List<AsientoOcupado> totBUtacas){
        butacas = totBUtacas;
    }

    public List<AsientoOcupado> getButacas() {
        return butacas;
    }
}
