package no.oslomet.cs.algdat.Oblig2;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class EgenTest {
    ////// Oppgave 1 /////////////////////
    @org.junit.jupiter.api.Test
    void oppgave1() {
        String[] s = {"Ole", null, "Per", "Kari", null};
        Liste<String> liste = new DobbeltLenketListe<>(s);
        System.out.println(liste.antall() + " " + liste.tom());
    }

}
