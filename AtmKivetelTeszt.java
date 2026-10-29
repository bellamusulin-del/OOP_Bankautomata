
package bankautomata;

import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class AtmKivetelTeszt {

    private Atm keszitsAtm(long egyenleg, long limit) {
        Szamla szamla = new Szamla(egyenleg);
        Kartya kartya = new Kartya("1234");
        Naplozo naplozo = new Naplozo(
                "target/test-tranzakciok.txt");

        return new Atm(szamla, kartya, limit, naplozo,
                20, 10, 5, 3);
    }

    @Test
    public void alapKivetelTeszt() {
        assertThrows(AtmKivetel.class, () -> {
            throw new AtmKivetel("Tesztkivetel");
        });
    }

    @Test
    public void hibasPinTeszt() {
        Kartya kartya = new Kartya("1234");

        assertThrows(HibasPinKivetel.class, () -> {
            kartya.ellenorizPin("0000");
        });
    }

    @Test
    public void kartyaLetiltasTeszt() {
        Kartya kartya = new Kartya("1234");

        assertThrows(HibasPinKivetel.class, () -> {
            kartya.ellenorizPin("0000");
        });

        assertThrows(HibasPinKivetel.class, () -> {
            kartya.ellenorizPin("0000");
        });

        assertThrows(KartyaLetiltvaKivetel.class, () -> {
            kartya.ellenorizPin("0000");
        });
    }

    @Test
    public void nincsFedezetTeszt() {
        Atm atm = keszitsAtm(100, 500);

        assertThrows(NincsFedezetKivetel.class, () -> {
            atm.kivesz("1234", 200);
        });
    }

    @Test
    public void nincsElegKeszpenzTeszt() {
        // A keszlet teljes erteke 1800 lej.
        Atm atm = keszitsAtm(10000, 5000);

        assertThrows(NincsElegKeszpenzKivetel.class, () -> {
            atm.kivesz("1234", 2000);
        });
    }

    @Test
    public void napiLimitTeszt() {
        Atm atm = keszitsAtm(10000, 300);

        atm.kivesz("1234", 200);

        assertThrows(NapiLimitKivetel.class, () -> {
            atm.kivesz("1234", 200);
        });
    }

    @Test
    public void negativOsszegTeszt() {
        Szamla szamla = new Szamla(1000);

        assertThrows(IllegalArgumentException.class, () -> {
            szamla.kivesz(0);
        });
    }
}
