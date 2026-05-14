/**
 * Classe principale: dimostra l'utilizzo dell'albero con zone e contatori.
 */
public class Main {
    public static void main(String[] args) {

        // --- Creazione dell'albero (radice = nome della città) ---
        Albero albero = new Albero("Milano");

        // --- Aggiunta delle zone come figli della radice ---
        albero.aggiungiZona("ZONA A");
        albero.aggiungiZona("ZONA B");
        albero.aggiungiZona("ZONA C");
        albero.aggiungiZona("ZONA D");

        // --- Aggiunta dei contatori in ZONA A ---
        albero.aggiungiContatore("ZONA A", new Contatore("C001", "Via Roma 1",      120.50));
        albero.aggiungiContatore("ZONA A", new Contatore("C002", "Via Roma 15",     340.00));
        albero.aggiungiContatore("ZONA A", new Contatore("C003", "Corso Italia 3",   87.75));

        // --- Aggiunta dei contatori in ZONA B ---
        albero.aggiungiContatore("ZONA B", new Contatore("C004", "Via Verdi 2",     210.30));
        albero.aggiungiContatore("ZONA B", new Contatore("C005", "Via Verdi 44",    158.90));

        // --- Aggiunta dei contatori in ZONA C ---
        albero.aggiungiContatore("ZONA C", new Contatore("C006", "Piazza Duomo 1",  530.00));
        albero.aggiungiContatore("ZONA C", new Contatore("C007", "Via Dante 7",      95.20));
        albero.aggiungiContatore("ZONA C", new Contatore("C008", "Viale Monza 10",  460.80));

        // --- Aggiunta dei contatori in ZONA D ---
        albero.aggiungiContatore("ZONA D", new Contatore("C009", "Via Torino 22",   300.00));

        // --- Stampa dell'albero completo ---
        albero.stampaAlbero();

        // --- Stampa delle statistiche ---
        albero.stampaStatistiche();

        // --- Ricerca di un contatore per ID ---
        System.out.println("\n=== Ricerca contatore C006 ===");
        Contatore trovato = albero.cercaContatore("C006");
        if (trovato != null) {
            System.out.println("Trovato: " + trovato);
        } else {
            System.out.println("Contatore non trovato.");
        }

        // --- Modifica del consumo di un contatore ---
        System.out.println("\n=== Aggiornamento consumo C001 ===");
        Contatore c001 = albero.cercaContatore("C001");
        if (c001 != null) {
            System.out.println("Prima: " + c001);
            c001.setConsumoKwh(199.99);
            System.out.println("Dopo:  " + c001);
        }

        // --- Disattivazione di un contatore ---
        System.out.println("\n=== Disattivazione C009 ===");
        Contatore c009 = albero.cercaContatore("C009");
        if (c009 != null) {
            c009.setAttivo(false);
            System.out.println("Stato aggiornato: " + c009);
        }

        // --- Consumo totale ZONA A dopo la modifica ---
        System.out.printf("%nConsumo totale ZONA A (dopo modifica): %.2f kWh%n",
                albero.consumoTotaleZona("ZONA A"));
    }
}
