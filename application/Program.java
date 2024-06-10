package application;

import java.time.LocalDate;
import java.util.Arrays;

import javax.swing.JOptionPane;

import models.Consulta;
import models.Consultorio;
import models.Pet;
import models.Veterinario;
import util.ArvoreAVL;
import util.Fila;
import util.ListaDuplamenteEncadeada;
import util.ListaEncadeada;

public class Program {

    public static void main(String[] args) {
        final ListaDuplamenteEncadeada<Consultorio> listaDupla = new ListaDuplamenteEncadeada<>();
        final ListaEncadeada<Veterinario> lista = new ListaEncadeada<>();
        final Fila<Consulta> fila = new Fila<>();
        final Fila<Consulta> historico = new Fila<>();
        final ArvoreAVL<Pet> arvore = new ArvoreAVL<>();
        final String[] menu = {
            "Cadastrar Pet",
            "Cadastrar Veterinario",
            "Histórico de Consulta",
            "Agendar Pet",
            "Fila de Espera",
            "Atendimento",
            "Gerenciamento de Consultas",
            "Sair"
        };

        

        int op = 0;

        listaDupla.adicionarFinal(new Consultorio("A1"));
        listaDupla.adicionarFinal(new Consultorio("A2"));
        listaDupla.adicionarFinal(new Consultorio("A3"));
        listaDupla.adicionarFinal(new Consultorio("A4"));
        listaDupla.adicionarFinal(new Consultorio("A5"));

        do{
            try{
                op = JOptionPane.showOptionDialog(null, "Clínica Veterinária", "MENU", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, menu, 0);

                switch (op) {
                    case 0:{
                        String nome = JOptionPane.showInputDialog(null, "Digite o Nome do Pet", "Pet", JOptionPane.QUESTION_MESSAGE);
                        String raca = JOptionPane.showInputDialog(null, "Digite a Raça do Pet", "Pet", JOptionPane.QUESTION_MESSAGE);
                        String idade = JOptionPane.showInputDialog(null, "Digite a Idade do Pet", "Pet", JOptionPane.QUESTION_MESSAGE);

                        arvore.adicionarValor(new Pet(nome, raca, Integer.parseInt(idade)));
                        break;
                    }
                    case 1:{
                        String nome = JOptionPane.showInputDialog(null, "Digite o Nome do Veterinario", "Veterinario", JOptionPane.QUESTION_MESSAGE);

                        lista.adicionarFinal(new Veterinario(nome));
                        break;
                    }
                    case 2:{
                        JOptionPane.showMessageDialog(null, "CONSULTAS: " + historico, "Histórico de Consulta", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case 3:{
                        String nomePet = JOptionPane.showInputDialog(null, "Digite o Nome do Pet para a Consulta", "Agendar Consulta", JOptionPane.QUESTION_MESSAGE);
                        var pet = arvore.buscarValor(nomePet);
                        String[] dataConsultaString = JOptionPane.showInputDialog(null, "Digite a data da Consulta (dd/mm/yyyy)", "Agendar Consulta", JOptionPane.QUESTION_MESSAGE).split("/");
                        var dataConsulta = LocalDate.of(Integer.parseInt(dataConsultaString[2]), Integer.parseInt(dataConsultaString[1]), Integer.parseInt(dataConsultaString[0]));

                        String idSelecionado = JOptionPane.showInputDialog(null, "Selecione o Veterinario" + lista.toString()
                            .replace("[", "").replace("]", ""), "Agendar Consulta", JOptionPane.QUESTION_MESSAGE);
                        var veterinario = lista.buscarValorId(Integer.parseInt(idSelecionado));

                        var consultorio = listaDupla.getConsultorioLivre();

                        if(consultorio == null){
                            throw new Exception("Não temos consultórios livre para essa data no momento");
                        }

                        consultorio.setLivre(false);

                        var consulta = new Consulta(dataConsulta, pet, veterinario, consultorio);

                        fila.adicionar(consulta);
                        historico.adicionar(consulta);
                        break;
                    }
                    case 4:{
                        JOptionPane.showMessageDialog(null, "Datas de Espera:\n" + fila.ordenarData(), 
                            "Fila de Espera", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case 5:{
                        if(fila.buscarPrimeiroValor() == null || fila.buscarPrimeiroValor().getPet() == null){
                            throw new Exception("Não tem nenhum pet sendo atendido no momento");
                        }
                        JOptionPane.showMessageDialog(null, "O paciente: " +  fila.buscarPrimeiroValor().getPet().getNome() + " está sendo atendido", "Atendimento", JOptionPane.OK_OPTION);
                        
                        fila.buscarPrimeiroValor().getConsultorio().setLivre(true);
                        fila.remover();
                        break;
                    }
                    case 6:{
                        JOptionPane.showMessageDialog(null, "Consultórios Livres:\n" + Arrays.toString(listaDupla.buscarConsultoriosLivres())
                            .replace("[", "").replace("]", ""), "Gerenciamento de Consultórios", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case 7:{
                        break;
                    }
                
                    default:{
                        throw new Exception("Valor Inválido");
                    }
                }
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }while(op != 7);
    }
}