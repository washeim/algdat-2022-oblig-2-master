# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Navn Navnesen, S123456, s123456@oslomet.no
* ...

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Per har hatt hovedansvar for oppgave 1, 3, og 5. 
* Else har hatt hovedansvar for oppgave 2, 4, og 6. 
* Fatima har hatt hovedansvar for oppgave 7 og 8. 
* Vi har i fellesskap løst oppgave 10. 

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

I oppgave 6 fikk jeg kun T fjern til å fungere ved å bruke boolean fjern inni funksjonen. boolean fjern lagde jeg ved først en if sjekk
for å fjerne hodet hvis det er der verdien ligger. Og så en while løkke for å gå helt til jeg når verdien. Da fjerner jeg verdien og setter nodene rundt
sine forrige og neste verdi til hverandres verdier. T fjern løste jeg ved å kjøre en hent() og deretter en fjern av denne verdien som blir hentet.

I oppgave 8 løste jeg den ved å sjekke om endringerne i next() var riktige med en if sjekk, og throwet exceptions hvis det ikke stemte.
Så gjorde jeg at iterator() returnerte en instans av interatorklassen. I DobbeltLenketListeIterator gjorde jeg en kontroll, og returnerte en instans av iteratorklassen.
