package ferrando.alejandro.appcine.model.tipos;

public enum Tipo {
    TRESD(1), CUATRODX(2), NORMAL(3);

    private int num;
    Tipo(int i){
        this.num = i;
    }

    public int getTipo(){
        return num;
    }
}
