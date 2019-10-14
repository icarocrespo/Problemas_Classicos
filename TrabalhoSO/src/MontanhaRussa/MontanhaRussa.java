package MontanhaRussa;

public class MontanhaRussa implements Runnable {

    public static final Integer tempoVoltaMontanha = 10;

    public Carrinho carrinho;

    public MontanhaRussa(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public void run() {
        synchronized (this) {
            try {
                Thread.sleep(tempoVoltaMontanha * 1000);  
                Thread.sleep(Fila.tempoEmbarqueDesembarque * 1000 * Carrinho.numeroLugaresCarrinho);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Finalizada thread montanha russa");
    }

    public void init() {
        new Thread(this, "MontanhaRussa").start();
    }

}
