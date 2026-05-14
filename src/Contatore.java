/**
 * Rappresenta un contatore elettrico installato in una zona della città.
 */
public class Contatore {
    private String id;           // codice univoco del contatore (es. "C001")
    private String indirizzo;    // indirizzo fisico dove è installato
    private double consumoKwh;   // consumo registrato in kWh
    private boolean attivo;      // stato del contatore

    public Contatore(String id, String indirizzo, double consumoKwh) {
        this.id = id;
        this.indirizzo = indirizzo;
        this.consumoKwh = consumoKwh;
        this.attivo = true;
    }

    // --- Getters e Setters ---

    public String getId() { return id; }

    public String getIndirizzo() { return indirizzo; }

    public double getConsumoKwh() { return consumoKwh; }
    public void setConsumoKwh(double consumoKwh) { this.consumoKwh = consumoKwh; }

    public boolean isAttivo() { return attivo; }
    public void setAttivo(boolean attivo) { this.attivo = attivo; }

    @Override
    public String toString() {
        return String.format("Contatore[%s | %s | %.2f kWh | %s]",
                id, indirizzo, consumoKwh, attivo ? "ATTIVO" : "INATTIVO");
    }
}
