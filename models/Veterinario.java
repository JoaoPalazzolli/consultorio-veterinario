package models;

public class Veterinario {
    
    private Integer id;
    private String nome;
    
    public Veterinario() {
    }

    public Veterinario(String nome) {
        this.id = gerarValor();
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "\nID: " + id + ", Nome: " + nome;
    }

    private Integer gerarValor(){
        return (int) (Math.random() * (1000 - 1 + 1) + 1);
    }
}
