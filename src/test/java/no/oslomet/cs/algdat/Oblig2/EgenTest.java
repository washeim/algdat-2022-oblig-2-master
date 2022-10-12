package no.oslomet.cs.algdat.Oblig2;

import java.sql.SQLOutput;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class EgenTest {
    ////// Oppgave 1 /////////////////////
    @org.junit.jupiter.api.Test
    void oppgave1() {
        Liste<Integer> testliste = new DobbeltLenketListe<>();
        for (int i = 0; i < 100000; i++) testliste.leggInn(i);
        System.out.println(testliste.toString());
    }
}
