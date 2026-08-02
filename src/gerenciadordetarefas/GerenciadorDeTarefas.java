package gerenciadordetarefas;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class GerenciadorDeTarefas {

    public static void main(String[] args) {
        ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
        Scanner teclado = new Scanner(System.in);
        
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
        
        boolean rodando = true;
        
        while (rodando) {
            System.out.println("------------ MENU ------------");
            System.out.println("[1] Adicionar nova Tarefa");
            System.out.println("[2] Listar Tarefas Existentes");
            System.out.println("[3] Concluir Tarefa");
            System.out.println("[4] Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = teclado.nextInt();
            teclado.nextLine();
            
            String nome = "teste";
            
            nome.equals("teste");
            
            switch (escolha) {
                case 1:
                    System.out.print("Digite o titulo da tarefa: ");
                    String titulo = teclado.nextLine();
                    System.out.print("Digite a descrição da tarefa: ");
                    String descricao = teclado.nextLine();
                    System.out.print("Digite uma data de conclusão: ");
                    String data = teclado.nextLine();
                    Tarefa tarefa = new Tarefa(titulo, descricao, data);
                    tarefas.add(tarefa);
                    System.out.println("Tarefa adicionada com sucesso");
                    break;
                case 2:
                    if (tarefas.size() > 0) {
                        int i = 0;
                        for (Tarefa tarefaAtual : tarefas) {
                            System.out.println("Tarefa n°" + i + ": " + tarefaAtual.getTitulo());
                        if (tarefaAtual.isCompleta()) {
                            System.out.println("Finalizada: [X]");
                        } else {
                            System.out.println("Finalizada: [ ]");
                        }
                        i++;
                        }
                    } else {
                        System.out.println("Nenhuma tarefa disponível.");
                    }
                    break;
                case 3:
                    int c = 0;
                    for (Tarefa tarefaAtual : tarefas) {
                        System.out.println("Tarefa n°" + c + ": " + tarefaAtual.getTitulo());
                        c++;
                    }
                    System.out.print("Que tarefa deseja concluir? ");
                    int tarefaConcluir = teclado.nextInt();
                    teclado.nextLine();
                    
                    if(tarefas.size() > tarefaConcluir && tarefaConcluir >=0) {
                        tarefas.get(tarefaConcluir).setCompleta(true);
                        System.out.println("Tarefa concluída com sucesso. Parabéns pelo esforço!");
                    } else {
                        System.out.println("Número inválido. Retornando ao menu principal.");
                    }
                    break;
                case 4:
                    try {
                        FileWriter escritor = new FileWriter("tarefa.txt");
                        for (Tarefa tarefaEscrita : tarefas) {
                            escritor.write(tarefaEscrita.toString() + "\n");
                        }
                        escritor.close();
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
                    }
                    
                    System.out.println("Fechando programa...");
                    rodando = false;
                    break;
            }
        }
    }
    
}
