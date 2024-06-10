package util;

import models.Consultorio;

public class ListaDuplamenteEncadeada <T> {

    private NosDuplaEncadeada<T> head;
    private Integer length;

    public ListaDuplamenteEncadeada() {
        this.length = 0;
        this.head = null;
    }

    public Integer getLength() {
        return length;
    }
    
    public void adicionarInicio(T value){
        var newNos = new NosDuplaEncadeada<>(value);

        if(this.head != null){
            newNos.setNext(this.head);
            this.head.setPrevius(newNos);
            this.head = newNos;
        } else{
            this.head = newNos;
        }

        this.length++;
    }

    public void adicionarFinal(T value){
        var current = this.head;
        var newNos = new NosDuplaEncadeada<>(value);

        if(current != null){
            while(current != null){
                if(current.getNext() == null){
                    current.setNext(newNos);
                    newNos.setPrevius(current);
                    break;
                }
                current = current.getNext();
            }
        } else{
            this.head = newNos;
        }

        this.length++;
    }

    public T getConsultorioLivre(){
        var current = this.head;

        if(current == null){
            return null;
        }

        if(!(current.getValue() instanceof Consultorio)){
            return null;
        }

        while(current != null && !(((Consultorio) current.getValue()).getLivre() == true)){
            current = current.getNext();
        }

        if(current != null){
            return current.getValue();
        }

        return null;
    }

    public String[] buscarConsultoriosLivres(){
        var current = this.head;
        String[] consultoriosLivres = new String[this.length];

        if(current == null){
            return null;
        }

        if(!(current.getValue() instanceof Consultorio)){
            return null;
        }

        for(int i = 0; i < this.length; i++){
            if(((Consultorio) current.getValue()).getLivre() == true){
                consultoriosLivres[i] = ((Consultorio) current.getValue()).getPorta();
            }
            current = current.getNext();
        }

        return consultoriosLivres;
    }

    public T removerValor(T value){
        var current = this.head;
        
        if(current != null){
            while(current != null && !(current.getValue() == value)){
                current = current.getNext();
            }

            if(current.getPrevius() == null){
                this.head = current.getNext();
            }

            if(current != null && current.getPrevius() != null){
                current.getPrevius().setNext(current.getNext());
            }
        }

        return current.getValue();
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        var current = this.head;

        while(current != null){
            sb.append(current.getValue());
            current = current.getNext();
        }

        return sb.toString();
    }

    
}
