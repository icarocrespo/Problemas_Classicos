package MontanhaRussa;

public class Main {

    public static void main(String[] args) {

        // instanciação e chamada da execução
        
        Carrinho carro = new Carrinho(Carrinho.numeroLugaresCarrinho);
        MontanhaRussa montanha = new MontanhaRussa(carro);
        Fila fila = new Fila();
        NewFila newFila = new NewFila(fila);
        Maquinista maquinista = new Maquinista(fila, carro, montanha);

        newFila.init();
        maquinista.init();
    }
}
