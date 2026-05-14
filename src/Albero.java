/**
 * Albero generico della città.
 *
 * Struttura:
 *   Radice (CittàX)
 *   ├── ZONA A
 *   │   ├── Contatore C001
 *   │   ├── Contatore C002
 *   │   └── Contatore C003
 *   ├── ZONA B
 *   │   ├── Contatore C004
 *   │   └── Contatore C005
 *   └── ZONA C
 *       └── Contatore C006
 *
 * La struttura interna usa la tecnica pfc/pfs (primo figlio / primo fratello).
 */
public class Albero {
    private NodoAlbero radice;

    // --- Costruttore ---
    public Albero(String nomeCitta) {
        this.radice = new NodoAlbero(nomeCitta);
    }

    public NodoAlbero getRadice() { return radice; }

    // ---------------------------------------------------------------
    // AGGIUNTA DI UNA ZONA come figlia della radice
    // ---------------------------------------------------------------
    public void aggiungiZona(String nomeZona) {
        NodoAlbero nuovaZona = new NodoAlbero(nomeZona);
        aggiungiComeUltimoFiglio(radice, nuovaZona);
    }

    // ---------------------------------------------------------------
    // RICERCA di un nodo zona per nome (tra i figli della radice)
    // ---------------------------------------------------------------
    public NodoAlbero cercaZona(String nomeZona) {
        NodoAlbero corrente = radice.getPrimoFiglio();
        while (corrente != null) {
            if (corrente.isZona() && corrente.getNomeZona().equalsIgnoreCase(nomeZona)) {
                return corrente;
            }
            corrente = corrente.getPrimoFratello();
        }
        return null; // zona non trovata
    }

    // ---------------------------------------------------------------
    // AGGIUNTA DI UN CONTATORE in una zona specifica
    // ---------------------------------------------------------------
    public boolean aggiungiContatore(String nomeZona, Contatore contatore) {
        NodoAlbero zona = cercaZona(nomeZona);
        if (zona == null) {
            System.out.println("Zona \"" + nomeZona + "\" non trovata.");
            return false;
        }
        NodoAlbero nodoContatore = new NodoAlbero(contatore);
        aggiungiComeUltimoFiglio(zona, nodoContatore);
        return true;
    }

    // ---------------------------------------------------------------
    // Metodo privato di supporto: aggiunge un nodo come ultimo figlio
    // ---------------------------------------------------------------
    private void aggiungiComeUltimoFiglio(NodoAlbero padre, NodoAlbero nuovoFiglio) {
        if (padre.getPrimoFiglio() == null) {
            // Il padre non ha ancora figli
            padre.setPrimoFiglio(nuovoFiglio);
        } else {
            // Scorri i fratelli fino all'ultimo
            NodoAlbero corrente = padre.getPrimoFiglio();
            while (corrente.getPrimoFratello() != null) {
                corrente = corrente.getPrimoFratello();
            }
            corrente.setPrimoFratello(nuovoFiglio);
        }
    }

    // ---------------------------------------------------------------
    // VISITA IN ORDINE ANTICIPATO (stampa l'albero)
    // ---------------------------------------------------------------
    public void stampaAlbero() {
        System.out.println("=== Albero della città: " + radice.getNomeZona() + " ===");
        visitaAnticipata(radice.getPrimoFiglio(), 1);
    }

    private void visitaAnticipata(NodoAlbero nodo, int livello) {
        if (nodo == null) return;

        // Indentazione per visualizzare la gerarchia
        String indent = "    ".repeat(livello - 1);
        String prefisso = livello == 1 ? indent + "├── " : indent + "    └── ";

        System.out.println(prefisso + nodo);

        // Visita i figli (ricorsione)
        visitaAnticipata(nodo.getPrimoFiglio(), livello + 1);

        // Visita il fratello successivo (stesso livello)
        visitaAnticipata(nodo.getPrimoFratello(), livello);
    }

    // ---------------------------------------------------------------
    // CONSUMO TOTALE di una zona
    // ---------------------------------------------------------------
    public double consumoTotaleZona(String nomeZona) {
        NodoAlbero zona = cercaZona(nomeZona);
        if (zona == null) return 0;

        double totale = 0;
        NodoAlbero figlio = zona.getPrimoFiglio();
        while (figlio != null) {
            if (figlio.isContatore()) {
                totale += figlio.getContatore().getConsumoKwh();
            }
            figlio = figlio.getPrimoFratello();
        }
        return totale;
    }

    // ---------------------------------------------------------------
    // CONSUMO TOTALE dell'intera città
    // ---------------------------------------------------------------
    public double consumoTotaleCitta() {
        double totale = 0;
        NodoAlbero zona = radice.getPrimoFiglio();
        while (zona != null) {
            totale += consumoTotaleZona(zona.getNomeZona());
            zona = zona.getPrimoFratello();
        }
        return totale;
    }

    // ---------------------------------------------------------------
    // NUMERO DI CONTATORI in una zona
    // ---------------------------------------------------------------
    public int numeroContatoriZona(String nomeZona) {
        NodoAlbero zona = cercaZona(nomeZona);
        if (zona == null) return 0;

        int count = 0;
        NodoAlbero figlio = zona.getPrimoFiglio();
        while (figlio != null) {
            if (figlio.isContatore()) count++;
            figlio = figlio.getPrimoFratello();
        }
        return count;
    }

    // ---------------------------------------------------------------
    // RICERCA DI UN CONTATORE per ID (in tutta la città)
    // ---------------------------------------------------------------
    public Contatore cercaContatore(String idContatore) {
        NodoAlbero zona = radice.getPrimoFiglio();
        while (zona != null) {
            NodoAlbero figlio = zona.getPrimoFiglio();
            while (figlio != null) {
                if (figlio.isContatore()
                        && figlio.getContatore().getId().equalsIgnoreCase(idContatore)) {
                    return figlio.getContatore();
                }
                figlio = figlio.getPrimoFratello();
            }
            zona = zona.getPrimoFratello();
        }
        return null; // non trovato
    }

    // ---------------------------------------------------------------
    // STAMPA STATISTICHE per ogni zona
    // ---------------------------------------------------------------
    public void stampaStatistiche() {
        System.out.println("\n=== Statistiche per zona ===");
        NodoAlbero zona = radice.getPrimoFiglio();
        while (zona != null) {
            String nome = zona.getNomeZona();
            int n = numeroContatoriZona(nome);
            double tot = consumoTotaleZona(nome);
            double media = n > 0 ? tot / n : 0;
            System.out.printf("%-10s | Contatori: %2d | Totale: %8.2f kWh | Media: %6.2f kWh%n",
                    nome, n, tot, media);
            zona = zona.getPrimoFratello();
        }
        System.out.printf("%nConsumo totale città: %.2f kWh%n", consumoTotaleCitta());
    }
}
