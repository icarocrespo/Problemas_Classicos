package MontanhaRussa;

public class Maquinista implements Runnable {

    static final int delayMaquinista = 10;

    public Fila fila;
    public Carrinho carrinho;
    public MontanhaRussa montanha;

    public Maquinista(Fila fila, Carrinho carrinho, MontanhaRussa montanha) {
        this.fila = fila;
        this.carrinho = carrinho;
        this.montanha = montanha;
    }

    public void run() {
        System.out.println("Thread Maquinista inciado!");
        while (true) {
            try {
                while (!fila.filaVazia() || carrinho.estaCheio()) {
                    boolean entrouNoCarrinho = carrinho.add();
                    if (entrouNoCarrinho) {
                        System.out.println("Entrou no carrinho");
                        System.out.println("Qtd de pessoas no carrinho: " + carrinho.getPassageiros());
                        fila.saiDaFila();
                    } else {
                        montanha.run();
                        carrinho.remove();
                    }
                }
                Thread.sleep(this.delayMaquinista * 1000);
                System.out.println("Maquinista verifica fila...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void init() {
        new Thread(this, "Maquinista").start();
    }
}
