package no.oslomet.cs.algdat.Oblig2;


public class EgenTest {
    ////// Oppgave 1 /////////////////////
    @org.junit.jupiter.api.Test
    void oppgave1() {
        DobbeltLenketListe<String> liste = new DobbeltLenketListe<>();

        liste = new DobbeltLenketListe<>(new String[]{"A", "B", "C", "D", "E", "F", "G"});


        System.out.println(liste.fjern("G"));
        liste.fjern("G");
        System.out.println(liste.toString());
        System.out.println(liste.omvendtString());
    }
}
