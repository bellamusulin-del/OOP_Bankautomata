# OOP_Bankautomata
## Checked és unchecked kivételek

Az `AtmKivetel` osztály és a belőle származó kivételek checked kivételek, mert a felhasználó által okozott hibákra reagálnak, például a hibás PIN-kódra, a letiltott kártyára, a fedezet hiányára vagy a napi limit túllépésére, ezért ezeket a programnak kezelnie kell. Az `IllegalArgumentException` unchecked kivétel, mert a nulla vagy negatív összeg megadása érvénytelen paraméter, amit a hívó programnak kell elkerülnie. A checked kivételek az `Exception` osztályból származnak, és kezelésüket vagy továbbdobásukat a Java megköveteli. Az unchecked kivételek az `RuntimeException` leszármazottai, ezért kezelésük fordításkor nem kötelező.
