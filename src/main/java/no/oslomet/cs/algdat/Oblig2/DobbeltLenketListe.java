
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
        private Node forrige;
        private Node neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {this(verdi, null, null);}
    }

    // instansvariabler
    private Node hode;          // peker til den første i listen
    private Node hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {return;}
    public DobbeltLenketListe(T[] a) {
        if (a==null) {
            throw new NullPointerException("Tabellen a er null!");
        } else {
            for (T t : a) {
                if (t != null) {
                    Node<T> nyNode = new Node(t);
                    if (hode == null) {
                        hode = hale = nyNode;
                        hode.forrige = null;
                    } else {
                        hale.neste = nyNode;
                        nyNode.forrige = hale;
                        hale = nyNode;
                    }
                    hale.neste = null;
                }
            }

        }
        antall();
    }

    //Oppgave 1
    @Override
    public int antall() {
        int teller = 0;
        Node gjeldene = hode;
        while (gjeldene != null) {
            teller++;
            gjeldene = gjeldene.neste;
        }
        this.antall = teller;
        return teller;
    }
    @Override
    public boolean tom() {
        int antall = this.antall;
        if (antall == 0) {
            return true;
        } else {
            return false;
        }
    }

    //Oppgave 2
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (hode == null || hale == null) {
            sb.append("]");
        } else {
            Node gjeldene = hode;
            sb.append(gjeldene.verdi);
            gjeldene = gjeldene.neste;
            while (gjeldene != null) {
                sb.append(", ");
                sb.append(gjeldene.verdi);
                gjeldene = gjeldene.neste;
            }
            sb.append("]");
        }
        return sb.toString();
    }
    public String omvendtString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (hode == null || hale == null) {
            sb.append("]");
        } else {
            Node gjeldene = hale;
            sb.append(gjeldene.verdi);
            gjeldene = gjeldene.forrige;
            while (gjeldene != null) {
                sb.append(", ");
                sb.append(gjeldene.verdi);
                gjeldene = gjeldene.forrige;
            }
            sb.append("]");
        }
        return sb.toString();
    }
    @Override
    public boolean leggInn(T verdi) {
        if (verdi == null) {
            throw new NullPointerException("Tabellen er null");
        } else {
            Node nyNode = new Node(verdi);
            if (tom()) {
                hode = nyNode;
            } else {
                hale.neste = nyNode;
                nyNode.forrige = hale;
            }
            hale = nyNode;
            endringer++;
            antall++;
        }
        return true;
    }

    //Oppgave 3 a)
    private Node finnNode(int indeks) {
        Node current = null;
        int midten = antall/2;
        if (indeks < midten) {
            current = hode;
            for (int i = 0; i < indeks; i++) {
                current = current.neste;}
        }
        if (indeks >= midten) {
            current = hale;
            for (int i = antall-1; i > indeks; i--) {
                current = current.forrige;}
        }

        return current;
    }
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        Node<T> newNode = finnNode(indeks);
        return newNode.verdi;
    }
    @Override
    public T oppdater(int indeks, T nyverdi) {
        if (nyverdi == null) {
            throw new NullPointerException("Null i nyverdi");
        }
        if (indeks < 0 || indeks > antall) {
            throw new IndexOutOfBoundsException("Feil index");
        }
        if (indeks == antall) {
            throw new IndexOutOfBoundsException("Utenfor rekkevidde");
        }
        Node<T> newNode = new Node(nyverdi);
        Node<T> erstatt = finnNode(indeks);
        T gammleVerdi = erstatt.verdi;
        erstatt.verdi = newNode.verdi;
        endringer++;
        return gammleVerdi;
    }

    //Oppgave 3 b)
    public Liste<T> subliste(int fra, int til) {
        Liste<T> list = new DobbeltLenketListe<>();
        if(fratilKontroll(antall(), fra, til)) {
            int avstand = til - fra;
            for (int i = 0; i < avstand; i++) {
                list.leggInn(hent(fra + i));
            }
            this.antall = avstand;
            return list;
        } else {
            throw new IllegalArgumentException("");
        }
    }
    private boolean fratilKontroll(int antall, int fra, int til) {
        if (fra > til) {
            throw new IllegalArgumentException("Fra er mer enn til");
        }
        if (fra < 0 || til > antall) {
            throw new IndexOutOfBoundsException ("Fra eller Til utenfor rekkevidde " + antall + " " + antall());
        }
        return true;
    }

    //Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        if (tom()) {
            return -1;
        }
        Node temp = hode;
        int pos = 0;
        while (temp.verdi != verdi && temp.neste != null)  {
            temp = temp.neste;
            pos++;
        }
        if (!temp.verdi.equals(verdi))
            return -1;
        return (pos);
    }
    @Override
    public boolean inneholder(T verdi) {
        if (indeksTil(verdi) == -1){
            return false;
        } else {
            return true;
        }
    }

    //Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        antall();
        Node newNode = new Node(verdi);
        newNode.neste = null;
        newNode.forrige = null;
        if (verdi == null) {
            throw new NullPointerException("Verdi feil");
        }
        if (indeks < 0 || indeks > antall) {
            throw new IndexOutOfBoundsException("Feil i index:" + indeks + " antall:" + antall);
        }
        //hvis den skal forran
        if (indeks == 0) {
            if(tom()){
                leggInn(verdi);
            } else {
                hode.forrige = newNode;
                newNode.neste = hode;
                hode = newNode;
                antall++;
            }
        }
        //hvis bakerst
        else if (indeks == antall) {
            leggInn(verdi);
        }

        else {
            int count = 0;
            Node current,previous;
            current = previous = hode;
            while (current!=null && ++count != indeks+1) {
                previous = current;
                current = current.neste;
            }
            newNode.neste = current;
            previous.neste = newNode;
            newNode.forrige = previous;
            antall++;
        }
    }

    //Oppgave 6
    @Override
    public T fjern(int indeks) {
        T gammel = hent(indeks);
        if(tom()) {
            throw new NoSuchElementException("Tom liste");
        }
        if (gammel == null) {
            throw new UnsupportedOperationException();}
        if (!inneholder(gammel)) {
            throw new NoSuchElementException("Finnes ikke i liste");
        }

        Node temp = null;
        if (indeks == 0) {
            temp = hode;
            if (hode == hale) {
                hale = null;
            } else {
                hode.neste.forrige = null;
            }
            hode = hode.neste;
            temp.neste = null;
        }
        if (indeks == antall) {
            temp = hale;
            if (hode == hale){
                hode = null;
            } else {
                hale.forrige.neste = null;
            }
            hale = hale.forrige;
            temp.forrige = null;
        } else {
            Node previous = hode;
            int count = 1;
            while (count < indeks){
                previous = previous.neste;
                count++;
            }
            Node current = previous.neste;
            previous.neste = current.neste;
            current.neste = null;
        }
        endringer++;
        antall--;
        return gammel;
    }
    @Override
    public boolean fjern(T verdi) {
        if(tom()) {
            throw new NoSuchElementException("Tom liste");
        }
        if (!inneholder(verdi)) {
            return false;
        }
        int indeks= indeksTil(verdi);
        fjern(indeks);
        return true;
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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
        DobbeltLenketListeIterator dL = new DobbeltLenketListeIterator();
        return dL;
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