package models;

public class Pet implements Comparable<Pet>{

    private String nome;
    private String raca;
    private Integer idade;
    
    public Pet() {
    }

    public Pet(String nome, String raca, Integer idade) {
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "\nNome: " + nome + ", Raça: " + raca + ", Idade: " + idade;
    }

    @Override
    public int compareTo(Pet o) {
        return o.getNome().compareTo(o.getNome());
    }

    
}
