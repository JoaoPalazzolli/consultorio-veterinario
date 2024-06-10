package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import models.Consulta;

@SuppressWarnings("unchecked")
public class Fila <T> {

    private T[] value;
    private Integer length;

    public Fila() {
        this.value = (T[]) new Object[10];
        this.length = 0;
    }

    public Fila(int capacidade) {
        this.value = (T[]) new Object[capacidade];
        this.length = 0;
    }

    public T buscarPrimeiroValor(){
        return this.value[0];
    }
    
    public void adicionar(T valor){
        verificarAumentarCapacidade();

        this.value[length] = valor;
        this.length++;
    }

    public T remover(){
        var valorRemovido = this.value[0];

        for(int i = 0; i < this.length; i++){
            this.value[i] = this.value[i+1];
        }

        this.length--;
        return valorRemovido;
    }

    public String ordenarData(){
        final var sb = new StringBuilder();
        final var dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate[] dataFiltrada = new LocalDate[this.length];
        var tamanho = this.length;

        for(int i = 0; i < this.length; i++){
            dataFiltrada[i] = ((Consulta) this.value[i]).getDataConsulta();
        }

        while(tamanho > 0){
            var continuar = false;
            LocalDate valor1 = null;
            LocalDate valor2 = null;

            for(int i = 0; i < tamanho - 1; i++){
                valor1 = dataFiltrada[i];
                valor2 = dataFiltrada[i + 1];

                if (valor1.isAfter(valor2)){
                    continuar = true;
                    dataFiltrada[i + 1] = valor1;
                    dataFiltrada[i] = valor2;
                }
            }

            if(!continuar){
                break;
            }
        }

        for(int i = 0; i < dataFiltrada.length; i++){
            sb.append(dtf.format(dataFiltrada[i]) + "\n");
        }

        return sb.toString();
    }

    private void verificarAumentarCapacidade(){
        if(this.length == this.value.length){
            var novaArray = (T[]) new Object[value.length * 2];

            for(int i = 0; i < this.length; i++){
                novaArray[i] = this.value[i];
            }

            this.value = novaArray;
        }
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();

        for(int i = 0; i < this.length; i++){
            sb.append(this.value[i]);
        }
        
        return sb.toString();
    }

    
}
