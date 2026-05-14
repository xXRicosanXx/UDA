/**
 * Nodo generico dell'albero, utilizzando la struttura pfc/pfs (primo figlio / primo fratello).
 *
 * Ogni nodo può contenere:
 *  - una stringa "nomeZona" se è un nodo zona (es. "ZONA A")
 *  - un oggetto Contatore se è un nodo foglia
 */
public class NodoAlbero {
    // Componente informativa: solo uno dei due sarà non-null a seconda del tipo di nodo
    private String nomeZona;        // usato per i nodi zona (livello 1)
    private Contatore contatore;    // usato per i nodi foglia (livello 2)

    // Struttura dell'albero con tecnica primo-figlio / primo-fratello
    private NodoAlbero primoFiglio;   // pfc: riferimento al primo figlio
    private NodoAlbero primoFratello; // pfs: riferimento al primo fratello

    // --- Costruttore per nodo ZONA ---
    public NodoAlbero(String nomeZona) {
        this.nomeZona = nomeZona;
        this.contatore = null;
        this.primoFiglio = null;
        this.primoFratello = null;
    }

    // --- Costruttore per nodo CONTATORE (foglia) ---
    public NodoAlbero(Contatore contatore) {
        this.contatore = contatore;
        this.nomeZona = null;
        this.primoFiglio = null;
        this.primoFratello = null;
    }

    // --- Tipo del nodo ---
    public boolean isZona() { return nomeZona != null; }
    public boolean isContatore() { return contatore != null; }

    // --- Getters e Setters ---
    public String getNomeZona() { return nomeZona; }
    public Contatore getContatore() { return contatore; }

    public NodoAlbero getPrimoFiglio() { return primoFiglio; }
    public void setPrimoFiglio(NodoAlbero primoFiglio) { this.primoFiglio = primoFiglio; }

    public NodoAlbero getPrimoFratello() { return primoFratello; }
    public void setPrimoFratello(NodoAlbero primoFratello) { this.primoFratello = primoFratello; }

    @Override
    public String toString() {
        if (isZona()) return "[ZONA: " + nomeZona + "]";
        if (isContatore()) return "[" + contatore + "]";
        return "[Nodo vuoto]";
    }
}
