package gerenciadordetarefas;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorDeTarefas {

    public static void main(String[] args) {
        ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
        
        try {
            File arquivo = new File("tarefa.txt");
            Scanner leitorArquivo = new Scanner(arquivo);
            
            while (leitorArquivo.hasNextLine()) {
                String linha = leitorArquivo.nextLine();
                String[] partes = linha.split(";");
                Tarefa tarefa = new Tarefa(partes[0], partes[1], partes[2]);
                boolean bool = Boolean.parseBoolean(partes[3]);
                tarefa.setCompleta(bool);
                tarefas.add(tarefa);
            }
            leitorArquivo.close();
        } catch (Exception e) {
            System.out.println("Nenhum arquivo encontrado. Iniciando lista vazia.");
        }
        
        TelaPrincipal tela = new TelaPrincipal();
        tela.abrirTela(tarefas);
    }
    
}
