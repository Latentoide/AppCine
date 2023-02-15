package ferrando.alejandro.appcine.model.tipos;

public enum TipoEdadMin {
    A(1), Ai(1), A7(1), A7i(1), A12(1), A16(1), A18(1);

    long numRes;

    TipoEdadMin(int nums){
        this.numRes = nums;
    }

    public long getNumRes() {
        return numRes;
    }
}
