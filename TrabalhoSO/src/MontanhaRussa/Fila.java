package MontanhaRussa;

public class Fila {

    static final int tempoEmbarqueDesembarque = 5;
    static final int tempoEntradaFilaMinimo = 5;
    static final int tempoEntradaFilaMaximo = 10;
    
    private int numeroPassageirosNaFila = 0;

    public Fila() {
    }

    public boolean filaVazia() {
        return this.numeroPassageirosNaFila == 0;
    }

    public int getNumeroPassageirosNaFila() {
        synchronized (this) {
            return this.numeroPassageirosNaFila;
        }
    }

    void entraNaFila() {
        synchronized (this) {
            this.numeroPassageirosNaFila++;
        }
    }

    void saiDaFila() {
        synchronized (this) {
            this.numeroPassageirosNaFila--;
        }

    }

}
