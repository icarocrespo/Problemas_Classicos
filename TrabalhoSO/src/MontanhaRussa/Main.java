package MontanhaRussa;

public class Main {

    public static void main(String[] args) {

        Carrinho carro = new Carrinho(Carrinho.numeroLugaresCarrinho);
        MontanhaRussa montanha = new MontanhaRussa(carro);
        Fila fila = new Fila();
        AlimentaFila alimentaFila = new AlimentaFila(fila);
        Maquinista maquinista = new Maquinista(fila, carro, montanha);

        alimentaFila.init();
        maquinista.init();
    }
}
