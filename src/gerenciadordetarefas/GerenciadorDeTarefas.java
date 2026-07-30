package gerenciadordetarefas;

import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorDeTarefas {

    public static void main(String[] args) {
        ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
        Scanner teclado = new Scanner(System.in);
        
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
                    int i = 0;
                    for (Tarefa tarefaAtual  : tarefas) {
                        System.out.println("Tarefa n°" + i + ": " + tarefaAtual.getTitulo());
                        if (tarefaAtual.isCompleta()) {
                            System.out.println("Finalizada: [X]");
                        } else {
                            System.out.println("Finalizada: [ ]");
                        }
                        i++;
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
                    System.out.println("Fechando programa...");
                    rodando = false;
                    break;
            }
        }
    }
    
}
