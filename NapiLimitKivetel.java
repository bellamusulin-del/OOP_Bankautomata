
package bankautomata;

public class NapiLimitKivetel extends AtmKivetel {

    private final long limit;
    private final long marFelhasznalt;
    private final long kertOsszeg;

    public NapiLimitKivetel(long limit, long marFelhasznalt,
                            long kertOsszeg) {
        super("A napi limit tullepese. Limit: " + limit
                + " lej, eddig felhasznalva: " + marFelhasznalt
                + " lej, kert osszeg: " + kertOsszeg + " lej.");

        this.limit = limit;
        this.marFelhasznalt = marFelhasznalt;
        this.kertOsszeg = kertOsszeg;
    }

    public long getLimit() {
        return limit;
    }

    public long getMarFelhasznalt() {
        return marFelhasznalt;
    }

    public long getKertOsszeg() {
        return kertOsszeg;
    }
}

