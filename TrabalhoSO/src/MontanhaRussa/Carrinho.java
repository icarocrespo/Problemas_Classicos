package MontanhaRussa;

public class Carrinho {

    public static final Integer numeroPassageiros = 12;
    public static final Integer numeroLugaresCarrinho = 4;
    public static final Integer numeroCarros = 1;
    
    private Integer passageiros;
    private Integer acentos;

    public Carrinho(Integer passageiros) {
        this.passageiros = passageiros;
    }

    public Integer getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(Integer passageiros) {
        this.passageiros = passageiros;
    }

    public Integer getAcentos() {
        return acentos;
    }

    public void setAcentos(Integer acentos) {
        this.acentos = acentos;
    }

    public boolean estaCheio() {
        if (this.passageiros == this.acentos) {
            return true;
        }
        return false;
    }

    public boolean add() {
        synchronized (this) {
            if (this.passageiros < this.acentos) {
                this.passageiros++;
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean remove() {
        synchronized (this) {
            this.passageiros = 0;
            return true;
        }
    }

}
