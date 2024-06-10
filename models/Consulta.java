package models;

import java.time.LocalDate;

public class Consulta {

    private Integer numeroConsulta;
    private LocalDate dataConsulta;
    private Pet pet;
    private Veterinario veterinario;
    private Consultorio consultorio;

    public Consulta() {
    }
    
    public Consulta(LocalDate dataConsulta, Pet pet, Veterinario veterinario, Consultorio consultorio) {
        this.dataConsulta = dataConsulta;
        this.pet = pet;
        this.veterinario = veterinario;
        this.numeroConsulta = gerarValor();
        this.consultorio = consultorio;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Integer getNumeroConsulta() {
        return numeroConsulta;
    }

    public void setNumeroConsulta(Integer numeroConsulta) {
        this.numeroConsulta = numeroConsulta;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }
    
    @Override
    public String toString() {
        return "\nNúmero da Consulta: " + this.numeroConsulta + "\nData da Consulta: " + dataConsulta + "\nPet: " + pet + "\nVeterinario: " + veterinario + "\nConsultorio: " + this.consultorio + "\n-------------------------------";
    }

    private Integer gerarValor(){
        return (int) (Math.random() * (1000 - 1 + 1) + 1);
    }
    
}
