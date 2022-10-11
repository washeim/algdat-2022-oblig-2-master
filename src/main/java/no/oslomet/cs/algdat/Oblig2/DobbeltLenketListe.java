package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> valgt;
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen


    public DobbeltLenketListe() {
        return;
    }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a, " a er null!");
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                Node nyNode = new Node(a[i]);
                if (hode == null) {
                    hode = hale = nyNode;
                    hode.forrige = null;
                    hale.neste = null;
                } else {
                    hale.neste = nyNode;
                    nyNode.forrige = hale;
                    hale = nyNode;
                    hale.neste = null;
                }
            }
        }
    }


    //OPPGAVE 3 B)
    public Liste<T> subliste(int fra, int til) {
        Liste<T> list = new DobbeltLenketListe<>();
        try {
            indeksKontroll(fra, false);
            indeksKontroll(til, false);
        } catch (Exception IndexOutOfBoundsException) {
            melding(fra);
            melding(til);
        }
        int avstand = til-fra;
        for (int i = 0; i < avstand; i++) {
            list.leggInn(hent(fra + i));
        }
        return list;
    }

    @Override
    public int antall() {
        int teller = 0;
        Node gjeldene = hode;
        while (gjeldene != null) {
            teller++;
            gjeldene = gjeldene.neste;
        }
        antall = teller;
        return teller;
    }

    @Override
    public boolean tom() {
        int teller = 0;
        Node gjeldene = hode;
        while (gjeldene != null) {
            gjeldene = gjeldene.neste;
            teller++;
        }
        if (teller > 0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "verdien er null!");
        Node newNode = new Node(verdi);
        //Hvis tom
        if (hode == null) {
            hode = hale = newNode;
            hode.forrige = null;
            hale.neste = null;
        }

        else {
            hale.neste = newNode;
            newNode.forrige = hale;
            hale = newNode;
            hale.neste = null;
        }
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    //Oppgave 3
    private Node<T> finnNode(int indeks) {
        Node gjeldene = hode;
        int sjekk = antall/2;

        if (indeks < sjekk) {
            for (int i = 0; i < indeks; i++) {
                gjeldene = gjeldene.neste;
            }
        }
        if (indeks  >= sjekk) {
            gjeldene = hale;
            for (int i = this.antall(); i > indeks+1; i--) {
                gjeldene = gjeldene.forrige;
            }
        }
        valgt = gjeldene;
        return valgt;
    }

    @Override
    public T hent(int indeks) {
        T hentet;
        try {
            indeksKontroll(indeks, false);
        } catch (Exception IndexOutOfBoundsException){melding(indeks);}
        Node funnet = finnNode(indeks);
        hentet = valgt.verdi;
        return hentet;
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Node<T> newNode = new Node(nyverdi);
        Node<T> erstatt = finnNode(indeks);
        T gammleVerdi = erstatt.verdi;
        try{
            indeksKontroll(indeks, false);
            if (nyverdi != null){
                gammleVerdi = erstatt.verdi;
                erstatt.verdi= newNode.verdi;
            }
        } catch (Exception IndexOutOfBoundsException) {
            melding(indeks);
        }
        endringer++;
        return gammleVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        if (hode == null || hale == null) {
            String ut = "[]";
            return ut;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            Node gjeldene = hode;
            sb.append(gjeldene.verdi);
            gjeldene = gjeldene.neste;
            while (gjeldene != null) {
                sb.append(", ");
                sb.append(gjeldene.verdi);
                gjeldene = gjeldene.neste;
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public String omvendtString() {
        if (hode == null || hale == null) {
            String ut = "[]";
            return ut;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");

            Node gjeldene = hale;
            sb.append(gjeldene.verdi);
            gjeldene = gjeldene.forrige;
            while (gjeldene != null) {
                sb.append(", ");
                sb.append(gjeldene.verdi);
                gjeldene = gjeldene.forrige;
            }
            sb.append("]");
            return sb.toString();
        }
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


