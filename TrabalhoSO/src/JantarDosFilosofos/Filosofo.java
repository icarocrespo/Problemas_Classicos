package JantarDosFilosofos;

public class Filosofo {
    private Garfo garfoEsquerda;
    private Garfo garfoDireita;
    private String nome;
    private int tempoExecucao;
    private boolean removeFlag;

    public Filosofo() {

    }

    public Garfo getGarfoEsquerda() {
        return garfoEsquerda;
    }

    public void setGarfoEsquerda(Garfo garfoEsquerda) {
        this.garfoEsquerda = garfoEsquerda;
    }

    public Garfo getGarfoDireita() {
        return garfoDireita;
    }

    public void setGarfoDireita(Garfo garfoDireita) {
        this.garfoDireita = garfoDireita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(int tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    public boolean getRemoveFlag() {
        return removeFlag;
    }

    public void setRemoveFlag(boolean removeFlag) {
        this.removeFlag = removeFlag;
    }


}