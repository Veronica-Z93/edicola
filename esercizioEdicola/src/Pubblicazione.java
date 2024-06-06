import java.util.Scanner;

class Pubblicazione {
    private String nomePubblicazione;
    private int copieRicevute;
    private double prezzoCopertina;
    private double aggio;
    private int copieVendute;

    public Scanner inputL = new Scanner(System.in); // Lettere
    public Scanner inputN = new Scanner(System.in); // Numeri

    public void setNome(String nomePubblicazione) {
        boolean dati = true;
        do {
            if (nomePubblicazione.length() >= 2) {
                this.nomePubblicazione = nomePubblicazione;
                dati = false;
            } else {
                System.out.print("Il nome della pubblicazione non è valido. Deve avere almeno 2 caratteri, reinseriscilo: ");
                nomePubblicazione = inputL.nextLine();
            }
        } while (dati);
    }

    public String getNome() { return nomePubblicazione; }

    public void setCopieRicevute(int copieRicevute) {
        // regola: numero positivo, min 1 copia, max 30 copie
        boolean dati = true;
        do {

        if (copieRicevute > 0 && copieRicevute <= 30)  {
            this.copieRicevute = copieRicevute;
            dati = false;
        } else {
            System.out.print("Il numero di copie ricevute non è valido. Deve essere maggiore di 0 e minore di 30, reinseriscilo: ");
            copieRicevute = inputN.nextInt();
        }
    } while(dati);
    }

    public int getCopieRicevute() { return copieRicevute; }

    public void setPrezzoCopertina(double prezzoCopertina) {
        // regola: maggiore di 1 e minore di 15
        boolean dati = true;

        do {
            if (prezzoCopertina >= 1 && prezzoCopertina <= 15) {
                this.prezzoCopertina = prezzoCopertina;
                dati = false;
            } else {
                System.out.println("Inserisci prezzo valido e compreso tra 1 e 15 euro: ");
                prezzoCopertina = inputN.nextDouble();
            }

        } while (dati);

    }

    public double getPrezzoCopertina() { return prezzoCopertina; }

    public void setAggio(double aggio) {
        // regola: aggio compreso tra 5 e 20
        boolean dati = true;

        do {
            if (aggio >= 5 && aggio <= 20) {
                this.aggio = aggio;
                dati = false;
            } else {
                System.out.println("Inserisci percentuale aggio valida: ");
                aggio = inputN.nextDouble();
            }

        } while (dati);

    }

    public double getAggio() { return aggio; }

    public void setCopieVendute(int copieVendute) {
        // regola: non superiore a copieRicevute (da considerare anche copie già disponibili)
        boolean dati = true;

        do {
            if (copieVendute >= 1 && copieVendute <= getCopieRicevute()) {
                this.copieVendute = copieVendute;
                dati = false;
            } else {
                System.out.println("Inserisci un numero di copie vendute valido e compreso tra 1 e " + getCopieRicevute() + ": ");
                copieVendute = inputN.nextInt();
            }

        } while (dati);
    }

    public int getCopieVendute() { return copieVendute; }
    
    public int calcolaCopieDaRestituire() {
        return getCopieRicevute() - getCopieVendute();
    }

    public double calcolaGuadagnoNetto() {
        double ricavi = getCopieVendute() * getPrezzoCopertina();
        return ricavi * (Math.round(getAggio()*100)/100.0) / 100;
    }
} 