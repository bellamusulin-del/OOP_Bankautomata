package bankautomata;

public class Kartya {

    private final String pin;
    private int hibasProbalkozasok;
    private boolean letiltva;

    public Kartya(String pin) {
        if (pin == null || !pin.matches("[0-9]{4}")) {
            throw new IllegalArgumentException(
                    "A PIN kodnak 4 szamjegybol kell allnia.");
        }

        this.pin = pin;
        hibasProbalkozasok = 0;
        letiltva = false;
    }

    public void ellenorizPin(String megadottPin)
            throws HibasPinKivetel, KartyaLetiltvaKivetel {

        if (letiltva) {
            throw new KartyaLetiltvaKivetel(hibasProbalkozasok);
        }

        if (pin.equals(megadottPin)) {
            hibasProbalkozasok = 0;
            return;
        }

        hibasProbalkozasok++;

        if (hibasProbalkozasok >= 3) {
            letiltva = true;
            throw new KartyaLetiltvaKivetel(hibasProbalkozasok);
        }

        throw new HibasPinKivetel(hibasProbalkozasok);
    }

    public boolean isLetiltva() {
        return letiltva;
    }
}

