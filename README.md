# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Adrian Asheim, S364585, S364585@oslomet.no

# Arbeidsfordeling

Alene.

# Oppgavebeskrivelse

I oppgave 1 lagde jeg en teller og en ny node som er like hode. 
Deretter med en while løkke gjør jeg noden til neste så lenge verdien ikke er null. 
Legger til en på teller for hver gang while løkken skjer. 
I tom bruker jeg samme while løkke og teller opp hvor mange ganger while skjer for å se om den er tom eller ikke. 
DobbeltLenketListe sjekker om verdiene er gyldige, deretter bruker en for loop av hvor mange elemter det er i tabellen. Den lager en ny node og sjekker om listen har et hode eller ikke. Hvis den har det så legger den noden som neste element. 

I oppgave 2 sjekker jeg først om deet er noe i listen. 
Deretter med en while løkke legger til innholdet av hver node inn i en stringbuilder helt til neste noden ikke har noe verdi.  
I omvendt gjør jeg akkurat det samme men begynner fra halen og jobber fram til hodet. LeggInn sjekker først om verdien er gyldig, 
deretter om det er noen verdier i listen. Hvis det ikke er det blior nye verdien hodet, hvis ikke blir den lagt inn som hale. Legger til en i endringer til slutt.

I oppgave 3 bruker antall og deler på 2 for å se om løkken skal starte fra hodet eller halen. Hvis indeksen er før antall/2 starter den fra 0, 
hvis den er mer enn det så starter den fra siste indeks. Gjør variablen valgt om til den noden som matcher indeksen. I hent sjekker jeg om indeksen er gyldig, 
så kjører finnNode med indeksen. Dette endrer variablen gjeldene og jeg kan returere verdien dens. Oppdater lager en ny node, og en node fra finnNode funksjonen med valgt indeks. 
Erstatter finnNoden med den nye noden og returnerer den gamle verdien. Subliste bruker en forløkke som starter på indeks + 0, og øker med en for hvor mange tall det er mellom fra og til.

I oppgave 4 lagret jeg hodet i en node. Deretter en variabel til indexen. Gårigjennom listen med en while løkke,
så lengde verdien av noden min ikke er lik verdien og ikke null så legger jeg til en på telleren min pos.
Deretter returnerer jeg -1 hvis den finnes i lista eller returnerer telleren hvis den finnes. Inneholder returnerer true hvis ikke tallet er -1,

I oppgave 5 sjekker jeg om feil i indeksen. Så om den er 0 eller ikke. Hvis den er 0 så gjør jeg nye noden til hode. 
Hvis den er over 0 lager jeg en midlertidig node som er hode, bruker for løkke til å gå helt frem til indeks noden og gjør
den midlertidig noden til neste i lista. Når for løkka er ferdig gjør jeg den nye LeggInn noden til lik den midlertidige, og gire den neste og forrige verdier.

