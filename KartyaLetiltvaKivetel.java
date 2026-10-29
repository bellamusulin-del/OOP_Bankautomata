
package bankautomata;

public class KartyaLetiltvaKivetel extends AtmKivetel {

    private final int hibasProbalkozasok;

    public KartyaLetiltvaKivetel(int hibasProbalkozasok) {
        super("A kartya le van tiltva " +
              hibasProbalkozasok + " hibas PIN probalkozas miatt.");
        this.hibasProbalkozasok = hibasProbalkozasok;
    }

    public int getHibasProbalkozasok() {
        return hibasProbalkozasok;
    }
}

