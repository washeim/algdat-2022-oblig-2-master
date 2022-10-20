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

    public DobbeltLenketListe() {}
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
    }


    //OPPGAVE 3 B)

    //Oppgave 1
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
        int antall = antall();
        if (antall == 0) {
            return true;
        } else {
            return false;
        }
    }
    //Oppgave 2
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
        Node gjeldene = new Node(null);
        int sjekk = antall/2;

        if (indeks < sjekk) {
            gjeldene = hode;
            for (int i = 0; i < indeks; i++) {
                gjeldene = gjeldene.neste;
            }
        } else {
            gjeldene = hale;
            for (int i = antall-1; i > indeks; i--) {
                gjeldene = gjeldene.forrige;
            }
        }
        return gjeldene;
    }
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        T hentet;
        Node<T> newNode = finnNode(indeks);
        hentet = newNode.verdi;
        return hentet;
    }
    @Override
    public T oppdater(int indeks, T nyverdi) {
        if (nyverdi == null) {
            throw new NullPointerException("Null i nyverdi");
        }
        if (indeks < 0 || indeks > antall) {
            throw new IndexOutOfBoundsException("Feil index");
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
        if ((fra <= til && fra >= 0 && til <= antall())) {
            int avstand = til - fra;
            for (int i = 0; i < avstand; i++) {
                list.leggInn(hent(fra + i));
            }
            this.antall = avstand;
            return list;
        } else {
            throw new IndexOutOfBoundsException ("Fra eller Til utenfor rekkevidde");
        }
    }

    //Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        Node temp = hode;
        int pos = 0;
        while (temp.verdi != verdi && temp.neste != null) {
            temp = temp.neste;
            pos++;
        }
        if (temp.verdi != verdi)
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
        if (verdi == null) {
            throw new NullPointerException("Verdi feil");
        }
        if (indeks < 0) {
            throw new IndexOutOfBoundsException("Feil i index");
        }
        Node<T> newNode = new Node(verdi);
        //tom
        if(tom() && indeks == 0) {
            leggInn(verdi);
        }
        //ikke tom
        if (!tom() && indeks == 0) {
            hode.forrige = newNode;
            newNode.neste = hode;
            hode = newNode;
        }
        //Legges bakerst
        if (indeks == antall && indeks > 0) {
            hale.neste = newNode;
            newNode.forrige = hale;
            hale = newNode;
        }
        //Legges til i indeksen i
        else {
            Node current = hode, temp;
            for (int i = 1; i<indeks; i++){
                current = current.neste;
            }
            temp = current.neste;
            temp.forrige = current;
            current.neste = newNode;
            newNode.forrige = current;
            newNode.neste = temp;
            temp.forrige = newNode;
        }
    }

    //Oppgave 6
    @Override
    public boolean fjern(T verdi) {
        endringer++;
        antall--;
        int indeks = indeksTil(verdi);
        hent(indeks);
        Node<T> temp = hode, forrige = null;
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
        return true;
    }

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


