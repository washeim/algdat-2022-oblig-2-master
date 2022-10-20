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
        long tid = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) testliste.hent(1);
        tid = System.currentTimeMillis() - tid;

        if (tid > 20) {
            System.out.println("Oppgave 3a: Dette (" + tid + " ms) gikk altfor sakte!");
            System.out.println("            Har du kodet metoden finnNode() riktig?");
        }
        System.out.println(tid);
    }
}
