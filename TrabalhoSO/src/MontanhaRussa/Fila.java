package MontanhaRussa;

public class Fila {

    public static final int tempoEmbarqueDesembarque = 5;
    public static final int tempoEntradaFilaMinimo = 5;
    public static final int tempoEntradaFilaMaximo = 10;
    
    private int passageirosFila;

    public Fila() {
        passageirosFila = 0;
    }

    public boolean estaVazia() {
        if (passageirosFila == 0){
            return true;
        }
        return false;
    }

    public int getPassageirosFila() {
        synchronized (this) {
            return passageirosFila;
        }
    }

    public boolean entrar() {
        synchronized (this) {
            passageirosFila++;
            return true;
        }
    }

    public boolean sair() {
        synchronized (this) {
            passageirosFila--;
            return true;
        }
    }

}
