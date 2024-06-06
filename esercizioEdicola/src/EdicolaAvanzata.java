import java.util.Scanner;
import java.util.ArrayList;

class EdicolaAvanzata {
    private ArrayList<PubblicazioneAvanzata> pubblicazioni = new ArrayList<>();
    private Scanner inputL = new Scanner(System.in); // Lettere
    private Scanner inputN = new Scanner(System.in); // Numeri
    public boolean aperto = true;

    public void menu() {
        System.out.println("Cosa vuoi fare?");
        System.out.println("1. Mostra pubblicazioni");
        System.out.println("2. Aggiungi pubblicazione");
        System.out.println("3. Rimuovi pubblicazione");
        System.out.println("4. Modifica dati pubblicazione");
        System.out.println("5. Mostra riepilogo");
        System.out.println("6. Chiudi edicola");
        System.out.println("Digita il numero corrispondente alla tua scelta:");
        int scelta = inputN.nextInt();

        switch (scelta) {
            case 1:
                mostraPubblicazioni();
                break;

            case 2:
                aggiungiPubblicazione();
                break;

            case 3:
                cancellaPublicazione();
                break;

            case 4:
                modificaDati();
                break;

            case 5:
                stampaRisultati();
                break;

            case 6:
                chiusuraEdicola();
                break;

            default:
                System.out.println("Opzione immessa non valida!");
                break;
        }
    }

    // APERTURA EDICOLA
    public void funzionamento() {
        boolean funziona = false;

        while (aperto) {
            if (!funziona) {
                System.out.println("*** Edicola Java ***");
                funziona = true;
            }
            menu();
        }
    }

    //CHIUSURA EDICOLA
    public void chiusuraEdicola() {
        System.out.println("Vuoi davvero chiudere l'edicola? Digita 1 se si, 2 se no.");
        int risposta = inputN.nextInt();
        if (risposta == 1) {
            aperto = false;
            System.out.println("*** Edicola Java CHIUSA ***");
            System.out.println("***     Arrivederci!    ***");
        } else if (risposta == 2) {
            System.out.println("Ok, l'edicola rimane aperta!");
        } else {
            System.out.println("Risposta inserita non valida!");
        }
        if (aperto) {
            funzionamento(); //torna al menù iniziale
        }
    }

    // MOSTRA ELENCO PUBBLICAZIONI
    public void mostraPubblicazioni() {
        if (pubblicazioni.isEmpty()) {
            System.out.println("Nessuna pubblicazione inserita!");
        } else {
            System.out.println("Elenco pubblicazioni inserite:");
            int index = 1;
            for (PubblicazioneAvanzata pubblicazione : pubblicazioni) {
                System.out.println("Indice pubblicazione: " + index);
                System.out.println("Nome della pubblicazione: " + pubblicazione.getNome());
                System.out.println("Copie ricevute: " + pubblicazione.getCopieRicevute());
                System.out.println("Prezzo di copertina: " + pubblicazione.getPrezzoCopertina() + " euro");
                System.out.println("Aggio: " + pubblicazione.getAggio() + "%");
                System.out.println("Copie vendute: " + pubblicazione.getCopieVendute());
                System.out.println("Guadagno netto: " + pubblicazione.calcolaGuadagnoNetto() + " euro");
                System.out.println();
                index++;
            }
        }
    }

    // AGGIUNGI PUBBLICAZIONE che sarebbe come il precedente gestisciVendita()
    public void aggiungiPubblicazione() {
        char continua;
        do {
            PubblicazioneAvanzata pubblicazione = new PubblicazioneAvanzata();

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
            if (continua != 'S' && continua != 's') {
                System.out.println("Stai tornando al menù principale...");
            }
            inputL.nextLine(); // Consuma riga vuota
           
        } while (continua == 'S' || continua == 's'); // se inserisco un carattere diverso da S torna al menù principale
    }

    // ELENCO PUBBLICAZIONE RAPIDA
    public void pubblicazioniNumeriIndice() {
        System.out.println("Pubblicazioni in ordine di indice: ");
        for (int i = 0; i < pubblicazioni.size(); i++) {
            System.out.println((i + 1) + ". " + pubblicazioni.get(i).getNome());
        }
    }

    // CANCELLA PUBBLICAZIONE E TUTTI I DATI RELATIVI
    public void cancellaPublicazione() {
        if (pubblicazioni.isEmpty()) {
            System.out.println("Nessuna pubblicazione ancora inserita!");
        } else { // visualizzo tutte le pubblicazioni
            System.out.println("Elenco pubblicazioni inserite:");
            pubblicazioniNumeriIndice();
            System.out.println("Digita il numero indice della pubbblicazione che vuoi eliminare: ");
            int numeroPub = inputN.nextInt();
            if (numeroPub >= 1 && numeroPub <= pubblicazioni.size()) {
                System.out.println("Vuoi davvero rimuovere " + numeroPub + "? Digita 1 per si o 2 per no.");
                int risposta = inputN.nextInt();
                if (risposta == 1) {
                    pubblicazioni.remove(numeroPub - 1);
                    System.out.println("Pubblicazione rimossa con successo!");
                } else if (risposta == 2) {
                    System.out.println("Pubblicazione non eliminata!");
                } else {
                    System.out.println("Risposta inserita non valida, riprova!");
                }
            }
        }
    }

    // MODIFICA DATI UNO PER UNO
    public void modificaDati() {

        if (pubblicazioni.isEmpty()) {
            System.out.println("Nessuna pubblicazione ancora inserita!");
        } else { // visualizzo tutte le pubblicazioni
            System.out.println("Elenco pubblicazioni inserite:");
            pubblicazioniNumeriIndice();
            System.out.println("Digita il numero della pubblicazione da modificare: ");
            int numeroPub = inputN.nextInt();
            if (numeroPub >= 1 && numeroPub <= pubblicazioni.size()) {
                PubblicazioneAvanzata pubblicazione = pubblicazioni.get(numeroPub - 1);
                System.out.println("Quale dato vuoi modificare?");
                System.out.println("1. Modifica nome pubblicazione");
                System.out.println("2. Modifica quantità ricevuta");
                System.out.println("3. Modifica quantità venduta");
                System.out.println("4. Modifica prezzo");
                System.out.println("5. Modifica percentuale aggio");
                System.out.println("6. Torna al menù principale");
                System.out.println("Digita il numero corrispondente alla tua scelta:");
                int scelta = inputN.nextInt();

                switch (scelta) {
                    case 1:
                        System.out.println("Nuovo nome pubblicazione: ");
                        pubblicazione.setNome(inputL.nextLine());
                        break;
                    case 2: 
                        System.out.println("Nuova quantità ricevuta: ");
                        int nuoveCopieRicevute = inputN.nextInt();
                        while (nuoveCopieRicevute <= pubblicazione.getCopieVendute()) {
                            System.out.println("Non è possibile che le copie ricevute siano minori delle copie vendute! Reinseriscilo:");
                            nuoveCopieRicevute = inputN.nextInt();
                        }
                            pubblicazione.setCopieRicevute(nuoveCopieRicevute);
                        break;

                    case 3:
                        System.out.println("Nuova quantità venduta: ");
                        pubblicazione.setCopieVendute(inputN.nextInt());
                        break;

                    case 4:
                        System.out.println("Nuovo prezzo di copertina: ");
                        pubblicazione.setPrezzoCopertina(inputN.nextDouble());
                        break;

                    case 5:
                        System.out.println("Nuova percentuale aggio: ");
                        pubblicazione.setAggio(inputN.nextDouble());
                        break;

                    case 6:
                        menu();
                        break;

                    default:
                        System.out.println("Opzione immessa non valida!");
                        break;
                }     
            } else {
                System.out.println("Pubblicazione non trovata!");
            }
        }
    }

    public void stampaRisultati() {
        if (pubblicazioni.isEmpty()) {
            System.out.println("Nessun riepilogo da mostrare!");
        } else {
            double guadagnoNettoComplessivo = 0;

            for (PubblicazioneAvanzata pubblicazione : pubblicazioni) {
                System.out.println("Nome della pubblicazione: " + pubblicazione.getNome());
                System.out.println("Copie vendute: " + pubblicazione.getCopieVendute());
                System.out.println("Copie non vendute e da restituire: " + pubblicazione.calcolaCopieDaRestituire());
                System.out.println("Guadagno netto: " + pubblicazione.calcolaGuadagnoNetto() + " euro");

                guadagnoNettoComplessivo += (Math.round(pubblicazione.calcolaGuadagnoNetto() * 100)) / 100.0;
            }

            System.out.println("Guadagno netto complessivo sino ad ora: " + guadagnoNettoComplessivo + " euro");
        }
    }
}
