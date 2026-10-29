
package bankautomata;

public class Szamla {

    private long egyenleg;

    public Szamla(long kezdoEgyenleg) {
        if (kezdoEgyenleg < 0) {
            throw new IllegalArgumentException(
                    "A kezdoegyenleg nem lehet negativ.");
        }

        egyenleg = kezdoEgyenleg;
    }

    public long getEgyenleg() {
        return egyenleg;
    }

    public void befizet(long osszeg) {
        if (osszeg <= 0) {
            throw new IllegalArgumentException(
                    "A befizetett osszegnek pozitivnak kell lennie.");
        }

        if (egyenleg > Long.MAX_VALUE - osszeg) {
            throw new IllegalArgumentException(
                    "Tul nagy osszeg.");
        }

        egyenleg += osszeg;
    }

    public void kivesz(long osszeg) throws NincsFedezetKivetel {
        if (osszeg <= 0) {
            throw new IllegalArgumentException(
                    "A kivett osszegnek pozitivnak kell lennie.");
        }

        if (osszeg > egyenleg) {
            throw new NincsFedezetKivetel(osszeg - egyenleg);
        }

        egyenleg -= osszeg;
    }
}
