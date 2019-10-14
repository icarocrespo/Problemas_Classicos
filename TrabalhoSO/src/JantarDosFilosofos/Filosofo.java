package JantarDosFilosofos;

public class Filosofo {

    private Garfo g_esquerda;
    private Garfo g_direita;
    private String nome;
    private Integer tempo;
    private boolean flag;

    public Filosofo() {

    }

    public Garfo getG_esquerda() {
        return g_esquerda;
    }

    public void setG_esquerda(Garfo g_esquerda) {
        this.g_esquerda = g_esquerda;
    }

    public Garfo getG_direita() {
        return g_direita;
    }

    public void setG_direita(Garfo g_direita) {
        this.g_direita = g_direita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
