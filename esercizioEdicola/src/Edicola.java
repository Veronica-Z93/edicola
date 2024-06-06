import java.util.Scanner;
import java.util.ArrayList;

class Edicola {
    private ArrayList<Pubblicazione> pubblicazioni = new ArrayList<>();
    private Scanner inputL = new Scanner(System.in); // Lettere
    private Scanner inputN = new Scanner(System.in); // Numeri

    public void gestisciVendite() {
        char continua;
        do {
            Pubblicazione pubblicazione = new Pubblicazione();

            System.out.println("Nome della pubblicazione: ");
            pubblicazione.setNome(inputL.nextLine());

            System.out.println("Quantità di copie ricevute (tra 1 e 30 comprese): ");
            pubblicazione.setCopieRicevute(inputN.nextInt());

            System.out.println("Prezzo di copertina (da 1 a 15 euro): ");
            pubblicazione.setPrezzoCopertina(inputN.nextDouble());

            System.out.println("Aggio (percentuale tra 5 e 20): ");
            pubblicazione.setAggio(inputN.nextDouble());

            System.out.println("Quantità di copie vendute: ");
            pubblicazione.setCopieVendute(inputN.nextInt());

            pubblicazioni.add(pubblicazione);

            System.out.println("Vuoi inserire un'altra pubblicazione (S/N)? ");
            continua = inputL.next().charAt(0);

            inputL.nextLine(); // Consuma riga vuota
        } while (continua == 'S' || continua == 's'); // se inserisco un carattere diverso da S esce dal programma
    }

    public void stampaRisultati() {
        double guadagnoNettoComplessivo = 0;

        for (Pubblicazione pubblicazione : pubblicazioni) {
            System.out.println("Nome della pubblicazione: " + pubblicazione.getNome());
            System.out.println("Copie vendute: " + pubblicazione.getCopieVendute());
            System.out.println("Copie non vendute e da restituire: " + pubblicazione.calcolaCopieDaRestituire());
            System.out.println("Guadagno netto: " + pubblicazione.calcolaGuadagnoNetto() + " euro");

            guadagnoNettoComplessivo += (Math.round(pubblicazione.calcolaGuadagnoNetto()*100))/100.0;
        } 

        System.out.println("Guadagno netto complessivo della giornata: " + guadagnoNettoComplessivo + " euro");
        System.out.println("*** Arrivederci! ***");
    }
}
