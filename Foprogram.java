
package bankautomata;

import java.util.Scanner;

public class Foprogram {

    private static final String PINK = "\u001B[95m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Szamla szamla = new Szamla(1000);
        Kartya kartya = new Kartya("1234");
        Naplozo naplozo = new Naplozo("tranzakciok.txt");

        Atm atm = new Atm(
                szamla, kartya, 500, naplozo,
                20, 10, 5, 3);

        boolean fut = true;

        System.out.println(PINK
                + "==================================");
        System.out.println("       BANKAUTOMATA BONUSZHAZI");
        System.out.println("=================================="
                + RESET);
        System.out.println("Teszt PIN kod: 1234");

        while (fut) {
            System.out.println();
            System.out.println(PINK + "----- MENU -----" + RESET);
            System.out.println("1. Egyenleg lekerdezese");
            System.out.println("2. Befizetes");
            System.out.println("3. Keszpenz felvetele");
            System.out.println("0. Kilepes");
            System.out.print("Valasztas: ");

            String valasztas = scanner.nextLine();

            try {
                switch (valasztas) {
                    case "1": {
                        System.out.print("PIN kod: ");
                        String pin = scanner.nextLine();

                        long egyenleg = atm.egyenlegLekerdez(pin);

                        System.out.println("Egyenleg: "
                                + egyenleg + " lej");
                        break;
                    }

                    case "2": {
                        System.out.print("PIN kod: ");
                        String pin = scanner.nextLine();

                        System.out.print("Befizetendo osszeg: ");
                        long osszeg = Long.parseLong(
                                scanner.nextLine());

                        atm.befizet(pin, osszeg);

                        System.out.println(
                                "A befizetes sikeres volt.");
                        break;
                    }

                    case "3": {
                        System.out.print("PIN kod: ");
                        String pin = scanner.nextLine();

                        System.out.print("Kivenni kivant osszeg: ");
                        long osszeg = Long.parseLong(
                                scanner.nextLine());

                        atm.kivesz(pin, osszeg);

                        System.out.println(
                                "A kivetel sikeresen befejezodott.");
                        break;
                    }

                    case "0":
                        fut = false;
                        System.out.println(
                                "Koszonjuk, hogy hasznalta "
                                + "a bankautomatat!");
                        break;

                    default:
                        System.out.println(
                                "Ismeretlen menupont. "
                                + "Valasszon a menubol.");
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Hiba: kerem, szamjegyekkel adja meg "
                        + "az osszeget.");

            } catch (AtmKivetel e) {
                System.out.println("ATM hiba: " + e.getMessage());

            } catch (IllegalArgumentException e) {
                System.out.println("Hibas adat: " + e.getMessage());

            } catch (Exception e) {
                // Varatlan, de elkapott hibanal sincs stack trace.
                System.out.println(
                        "Varatlan hiba tortent. "
                        + "Kerem, probalja ujra.");
            }
        }

        scanner.close();
    }
}
