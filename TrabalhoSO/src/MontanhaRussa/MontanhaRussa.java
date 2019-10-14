package MontanhaRussa;

public class MontanhaRussa implements Runnable {

    public static final Integer tempoVoltaMontanha = 10;

    public Carrinho carrinho;

    public MontanhaRussa(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
    public MontanhaRussa() {
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                Thread.sleep(tempoVoltaMontanha * 1000);
                Thread.sleep(Fila.tempoEmbarqueDesembarque * 1000 * Carrinho.numeroLugaresCarrinho);
                // utilizamos 1000 como visto em uma vídeo aula e fora um bom número
                // aqui é possível alterar o valor a ser multiplicado para ter diferentes saídas
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fim MontanhaRussa");
    }

    public void init() {
        new Thread(this, "MontanhaRussa").start();
    }

}
