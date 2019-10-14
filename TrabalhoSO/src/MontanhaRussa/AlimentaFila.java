package MontanhaRussa;

import java.util.Random;

public class AlimentaFila implements Runnable {

    public Fila fila;
    Integer n_passageiros = Carrinho.numeroPassageiros;
    public AlimentaFila(Fila fila) {
        this.fila = fila;
    }

    public void run() {
        System.out.println("Thread AlimentaFila Iniciada!");
        while (n_passageiros > 0) {
            int tempoAleatorio = (int) (Math.random() * (Fila.tempoEntradaFilaMaximo - Fila.tempoEntradaFilaMinimo) + Fila.tempoEntradaFilaMinimo);
            System.out.println("Tempo para o proximo entra na fila: " + tempoAleatorio);
            try {
                Thread.sleep(tempoAleatorio * 1000);
                fila.entrar();
                n_passageiros--;

                System.out.println("Entrou na fila");
                System.out.println("Fila com: " + fila.getPassageirosFila());
                System.out.println("Numero de passageiros: " + n_passageiros);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread Finalizada!");
        System.out.println("Numero de pessoas na fila: " + fila.getPassageirosFila());
    }

    public void init() {
        new Thread(this, "AlimentaFila").start();

    }
}
