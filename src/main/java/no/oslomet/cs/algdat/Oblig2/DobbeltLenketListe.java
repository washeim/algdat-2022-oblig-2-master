package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


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
        if (fra < 0 || til > antall) {
            throw new IndexOutOfBoundsException ("Fra og Til utenfor rekkevidde");
        }
        int avstand = til-fra;
        for (int i = 0; i < avstand; i++) {
            list.leggInn(hent(fra + i));
        }
        antall = avstand;
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

    //Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        antall();
        Node newNode = new Node(verdi);
        if (indeks < 0 || indeks>antall) {
            throw new NullPointerException("Feil indeks");
        }
        if (indeks == 0) {
            newNode.neste = hode;
            hode = newNode;
            antall++;
            endringer++;
        } else {
            Node temp = hode;
            for (int i = 1; i < indeks-1; i++) {
                if (temp != null) {
                    temp = temp.neste;
                }
            }
            if (temp != null) {
                newNode.neste = temp.neste;
                temp.neste = newNode;
                newNode.forrige = temp;
            }
            antall++;
            endringer++;
        }
    }

    @Override
    public boolean inneholder(T verdi) {
        if (indeksTil(verdi) == -1){
            return false;
        } else {
            return true;
        }
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
        return gjeldene;
    }

    @Override
    public T hent(int indeks) {
        T hentet;
        try {
            indeksKontroll(indeks, false);
        } catch (Exception IndexOutOfBoundsException){melding(indeks);}
        finnNode(indeks);
        hentet = valgt.verdi;
        return hentet;
    }

    @Override
    public int indeksTil(T verdi) {
        Node temp = hode;
        int pos = 0;
        while (temp.verdi != verdi && temp.neste != null) {
            pos++;
            temp = temp.neste;
        }
        if (temp.verdi != verdi)
            return -1;
        return (pos);
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

    //Oppgave 6
    @Override
    public boolean fjern(T verdi) {
        int indeks = indeksTil(verdi);
        hent(indeks);
        Node temp = hode, forrige = null;
        if (temp != null && temp.verdi == verdi) {
            hode = null;
            forrige.neste = temp.neste;
        }
        while (temp != null && temp.verdi != verdi) {
            forrige = temp;
            temp = temp.neste;
            forrige.neste = temp.neste;
        }
        if (verdi == null)
            throw new UnsupportedOperationException();
        endringer++;
        antall--;
        return true;
    }
    //Boolean fjern fungerer ikke, boolean indeks fungerer...

    @Override
    public T fjern(int indeks) {
        T verdi = hent(indeks);
        fjern(verdi);
        return verdi;
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

    //Oppgave 8
    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
        return iterator;
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks,true);

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


        //Oppgave 8
        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException("Feil i endringer");
            }
            if (!hasNext()){
                throw new NoSuchElementException("Has next error");
            }
            fjernOK = true;
            denne.neste.verdi = denne.verdi;
            return denne.verdi;
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


