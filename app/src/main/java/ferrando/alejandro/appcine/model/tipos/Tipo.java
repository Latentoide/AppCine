package ferrando.alejandro.appcine.model.tipos;

public enum Tipo {
    TRES(1), CUATROX(2), NORMAL(3);

    private int num;
    Tipo(int i){
        this.num = i;
    }

    public int getTipo(){
        return num;
    }
}
