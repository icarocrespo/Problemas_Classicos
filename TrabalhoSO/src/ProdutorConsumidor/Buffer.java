package ProdutorConsumidor;

public class Buffer {

    private Integer inicio;
    private Integer fim;
    private Integer limite;
    private Integer buffer[];

    public Buffer(Integer limite) {
        this.inicio = 0;
        this.fim = 0;
        this.limite = limite;
        this.buffer = new Integer[limite];
    }

    public Buffer() {

    }

    public void add(int valor) {
        buffer[fim++ % limite] = valor;
    }

    public int remove() {
        return buffer[inicio++ % limite];
    }

    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    public Integer getFim() {
        return fim;
    }

    public void setFim(Integer fim) {
        this.fim = fim;
    }

    public Integer getLimite() {
        return limite;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    public Integer[] getBuffer() {
        return buffer;
    }

    public void setBuffer(Integer[] buffer) {
        this.buffer = buffer;
    }

}
