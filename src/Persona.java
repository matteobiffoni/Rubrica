public class Persona {
    private String ID;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private int eta;

    public Persona(String id, String nome, String cognome, String indirizzo, String telefono, int eta) {
        this.ID = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.eta = eta;
    }

    public String getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public int getEta() {
        return eta;
    }
    public void setEta(int eta) {
        this.eta = eta;
    }
    @Override
    public String toString() {
        return this.nome + ";" + this.cognome + ";" + this.indirizzo + ";" + this.telefono + ";" + this.eta;
    }
}