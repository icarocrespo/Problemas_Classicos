package JantarDosFilosofos;

public class Garfo {

    private String nome;
    private boolean emUso = false;

    public Garfo(String nome) {
        this.nome = nome;
    }

    public Garfo() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmUso(boolean emUso) {
        this.emUso = emUso;
    }

    public boolean getEmUso() {
        return emUso;
    }
}
