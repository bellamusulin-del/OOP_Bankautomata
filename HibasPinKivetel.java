
package bankautomata;

public class HibasPinKivetel extends AtmKivetel {

    private final int probalkozasok;

    public HibasPinKivetel(int probalkozasok) {
        super("Hibas PIN kod. Probalkozasok: " + probalkozasok + "/3.");
        this.probalkozasok = probalkozasok;
    }

    public int getProbalkozasok() {
        return probalkozasok;
    }
}
