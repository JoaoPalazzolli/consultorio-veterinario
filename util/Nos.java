package util;

public class Nos <T> {

    private T value;
    private Nos<T> next;

    public Nos(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Nos<T> getNext() {
        return next;
    }

    public void setNext(Nos<T> next) {
        this.next = next;
    }
    
    
}
