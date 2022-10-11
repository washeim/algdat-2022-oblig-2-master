package no.oslomet.cs.algdat.Oblig2;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class EgenTest {
    ////// Oppgave 1 /////////////////////
    @org.junit.jupiter.api.Test
    void oppgave1() {
        Character[] c = {'A','B','C','D','E','F','G','H','I','J',};
        DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(c);
        System.out.println("123");
        System.out.println(liste.hent(2));
        }
}
