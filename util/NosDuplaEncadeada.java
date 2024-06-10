package util;

public class NosDuplaEncadeada <T> {

    private NosDuplaEncadeada<T> previus;
    private NosDuplaEncadeada<T> next;
    private T value;

    public NosDuplaEncadeada(T value) {
        this.value = value;
        this.next = null;
        this.previus = null;
    }

    public T getValue() {
        return value;
    }

    public NosDuplaEncadeada<T> getPrevius() {
        return previus;
    }

    public void setPrevius(NosDuplaEncadeada<T> previus) {
        this.previus = previus;
    }

    public NosDuplaEncadeada<T> getNext() {
        return next;
    }

    public void setNext(NosDuplaEncadeada<T> next) {
        this.next = next;
    }
    
    
}
