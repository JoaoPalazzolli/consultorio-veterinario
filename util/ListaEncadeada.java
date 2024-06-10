package util;

import models.Veterinario;

public class ListaEncadeada <T> {
    
    private Nos<T> head;
    private Integer length;

    public ListaEncadeada() {
        this.head = null;
        this.length = 0;
    }

    public ListaEncadeada(Integer length) {
        this.length = length;
    }

    public void adicionarInicio(T valor){
        var newNos = new Nos<>(valor);
        newNos.setNext(this.head);
        this.head = newNos;

        this.length++;
    }

    public void adicionarFinal(T valor){
        var current = this.head;
        var newNos = new Nos<>(valor);

        if(current != null){
            while(current != null){
                if(current.getNext() == null){
                    current.setNext(newNos);
                    break;
                }
                current = current.getNext();
            }
        } else {
            this.head = newNos;
        }

        this.length++;
    }

    public T remover(Integer id){
        var current = this.head;
        Nos<T> previus = null;

        if(current == null || !(current.getValue() instanceof Veterinario)){
            return null;
        }

        while(current != null && !((Veterinario) current.getValue()).getId().equals(id)){
            previus = current;
            current = current.getNext();
        }

        if(previus == null){
            this.head = current.getNext();
        }

        if(current != null){
            previus.setNext(current.getNext());
        }

        this.length--;

        return current.getValue();
    }

    public T buscarValorId(Integer id){
        var current = this.head;

        if(current == null || !(current.getValue() instanceof Veterinario)){
            return null;
        }

        while(current != null && !((Veterinario) current.getValue()).getId().equals(id)){
            current = current.getNext();
        }

        return current.getValue();
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        var current = this.head;

        while(current != null){
            sb.append("\n" + current.getValue());
            current = current.getNext();
        }
        
        return sb.toString();
    }

    
}
