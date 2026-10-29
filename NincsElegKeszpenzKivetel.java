package bankautomata;

public class NincsElegKeszpenzKivetel extends AtmKivetel {

    private final long kertOsszeg;

    public NincsElegKeszpenzKivetel(long kertOsszeg) {
        super("Az automata nem tudja kiadni a kert " +
              kertOsszeg + " lejt a jelenlegi cimletekkel.");
        this.kertOsszeg = kertOsszeg;
    }

    public long getKertOsszeg() {
        return kertOsszeg;
    }
}
