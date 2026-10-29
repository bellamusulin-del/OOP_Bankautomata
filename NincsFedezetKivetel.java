package bankautomata;

public class NincsFedezetKivetel extends AtmKivetel {

    private final long hiany;

    public NincsFedezetKivetel(long hiany) {
        super("Nincs eleg fedezet. Hianyzo osszeg: " + hiany + " lej.");
        this.hiany = hiany;
    }

    public long getHiany() {
        return hiany;
    }
}

