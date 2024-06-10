package util;

public class NosArvore <T> {

    private T valor;
    private NosArvore<T> esquerda;
    private NosArvore<T> direita;
    private Integer altura;

    public NosArvore() {
    }

    public NosArvore(T valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.altura = 1;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public NosArvore<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NosArvore<T> esquerda) {
        this.esquerda = esquerda;
    }

    public NosArvore<T> getDireita() {
        return direita;
    }

    public void setDireita(NosArvore<T> direita) {
        this.direita = direita;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

}
