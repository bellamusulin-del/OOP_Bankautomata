
package bankautomata;

public class Atm {

    private final Szamla szamla;
    private final Kartya kartya;
    private final Naplozo naplozo;
    private final long napiLimit;

    // A tomb sorrendje: 200, 100, 50, 10 lej.
    private final int[] cimletek = {200, 100, 50, 10};
    private final int[] keszlet;

    private long napiFelhasznalas;

    public Atm(Szamla szamla, Kartya kartya, long napiLimit,
               Naplozo naplozo, int db10, int db50,
               int db100, int db200) {

        if (szamla == null || kartya == null || naplozo == null) {
            throw new IllegalArgumentException(
                    "A szamla, kartya es naplozo kotelezo.");
        }

        if (napiLimit <= 0 || db10 < 0 || db50 < 0
                || db100 < 0 || db200 < 0) {
            throw new IllegalArgumentException(
                    "Hibas napi limit vagy cimletkeszlet.");
        }

        this.szamla = szamla;
        this.kartya = kartya;
        this.napiLimit = napiLimit;
        this.naplozo = naplozo;

        // A keszlet is a cimletek sorrendjet koveti.
        keszlet = new int[] {db200, db100, db50, db10};
        napiFelhasznalas = 0;
    }

    public long egyenlegLekerdez(String megadottPin)
            throws AtmKivetel {

        try {
            kartya.ellenorizPin(megadottPin);
            naplozo.naploz("Egyenleglekerdezes sikeres.");
            return szamla.getEgyenleg();

        } catch (AtmKivetel e) {
            naplozo.naploz("Sikertelen egyenleglekerdezes: "
                    + e.getMessage());
            throw e;
        }
    }

    public void befizet(String megadottPin, long osszeg)
            throws AtmKivetel {

        try {
            if (osszeg <= 0) {
                throw new IllegalArgumentException(
                        "A befizetett osszegnek pozitivnak kell lennie.");
            }

            kartya.ellenorizPin(megadottPin);
            szamla.befizet(osszeg);
            naplozo.naploz("Sikeres befizetes: " + osszeg + " lej.");

        } catch (AtmKivetel e) {
            naplozo.naploz("Sikertelen befizetes: " + e.getMessage());
            throw e;

        } catch (IllegalArgumentException e) {
            naplozo.naploz("Sikertelen befizetes: " + e.getMessage());
            throw e;
        }
    }

    public void kivesz(String megadottPin, long osszeg)
            throws AtmKivetel {

        try {
            if (osszeg <= 0) {
                throw new IllegalArgumentException(
                        "A kivett osszegnek pozitivnak kell lennie.");
            }

            kartya.ellenorizPin(megadottPin);

            if (osszeg > napiLimit - napiFelhasznalas) {
                throw new NapiLimitKivetel(
                        napiLimit, napiFelhasznalas, osszeg);
            }

            if (osszeg > szamla.getEgyenleg()) {
                throw new NincsFedezetKivetel(
                        osszeg - szamla.getEgyenleg());
            }

            int[] kiadando = new int[cimletek.length];

            if (!keresCimleteket(0, osszeg, kiadando)) {
                throw new NincsElegKeszpenzKivetel(osszeg);
            }

            // Csak akkor modositunk, ha minden ellenorzes sikerult.
            szamla.kivesz(osszeg);

            for (int i = 0; i < keszlet.length; i++) {
                keszlet[i] -= kiadando[i];
            }

            napiFelhasznalas += osszeg;

            naplozo.naploz("Sikeres kivet: " + osszeg + " lej.");

            System.out.println("A kiadott cimletek:");

            for (int i = 0; i < cimletek.length; i++) {
                if (kiadando[i] > 0) {
                    System.out.println(cimletek[i] + " lej: "
                            + kiadando[i] + " db");
                }
            }

        } catch (AtmKivetel e) {
            naplozo.naploz("Sikertelen kivet: " + e.getMessage());
            throw e;

        } catch (IllegalArgumentException e) {
            naplozo.naploz("Sikertelen kivet: " + e.getMessage());
            throw e;
        }
    }

    private boolean keresCimleteket(int index, long maradek,
                                    int[] kiadando) {

        if (maradek == 0) {
            return true;
        }

        if (index >= cimletek.length) {
            return false;
        }

        int maxDarab = (int) Math.min(
                keszlet[index], maradek / cimletek[index]);

        for (int darab = maxDarab; darab >= 0; darab--) {
            kiadando[index] = darab;

            long ujMaradek = maradek
                    - (long) darab * cimletek[index];

            if (keresCimleteket(index + 1, ujMaradek, kiadando)) {
                return true;
            }
        }

        kiadando[index] = 0;
        return false;
    }

    public long getNapiFelhasznalas() {
        return napiFelhasznalas;
    }
}

