package bankautomata;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Naplozo {

    private final String fajlNev;

    public Naplozo(String fajlNev) {
        this.fajlNev = fajlNev;
    }

    public void naploz(String uzenet) {
        try (BufferedWriter iro =
                     new BufferedWriter(new FileWriter(fajlNev, true))) {

            String ido = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            iro.write(ido + " - " + uzenet);
            iro.newLine();

        } catch (IOException e) {
            System.out.println(
                    "[FIGYELEM] A naplozas nem sikerult, "
                    + "de a program tovabb fut.");
        }
    }
}

