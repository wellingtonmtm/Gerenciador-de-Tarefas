package gerenciadordetarefas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class TelaPrincipal {
        public JPanel criarCartaoTarefa(Tarefa tarefa,ArrayList<Tarefa> tarefas, JPanel panelTarefas) {
        JPanel cartao = new JPanel();
        Border linhaPreta = BorderFactory.createLineBorder(Color.BLACK);
        Border respiro = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border bordaFinal = BorderFactory.createCompoundBorder(linhaPreta, respiro);
        cartao.setBorder(bordaFinal);
        cartao.setBackground(Color.GRAY);
        cartao.setLayout(new BoxLayout(cartao,BoxLayout.Y_AXIS));
        JLabel lblTitulo = new JLabel(tarefa.getTitulo());
        JLabel lblDescricao = new JLabel(tarefa.getDescricao());
        JLabel lblData = new JLabel(tarefa.getData());
        JCheckBox check = new JCheckBox();
        check.addActionListener(e -> {
            tarefa.setCompleta(check.isSelected());
            reescreverArquivo(tarefas);
        });
        check.setSelected(tarefa.isCompleta());
        JButton btnDeletar = new JButton("X");
        btnDeletar.addActionListener(e -> {
            int resposta  = JOptionPane.showConfirmDialog(cartao, "Tem certeza que deseja excluir esta tarefa?", 
                    "Confirmar Exclusão",JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                tarefas.remove(tarefa);
                panelTarefas.remove(cartao);
                panelTarefas.revalidate();
                panelTarefas.repaint();
                reescreverArquivo(tarefas);
            }
        });
        cartao.add(lblTitulo);
        cartao.add(lblDescricao);
        cartao.add(lblData);
        cartao.add(check);
        cartao.add(btnDeletar);
        return cartao;
    }
    
    public void abrirTela(ArrayList<Tarefa> tarefas) {
        SwingUtilities.invokeLater(() -> {
        JFrame tela = new JFrame("Gerenciador de Tarefas");
        tela.setSize(800, 600);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLocationRelativeTo(null);
        
        // Painel Principal
        JPanel panelMenuPrincipal = new JPanel();
        panelMenuPrincipal.setBackground(Color.LIGHT_GRAY);
        panelMenuPrincipal.setLayout(new BorderLayout());
        
        JLabel txtMenuTarefa = new JLabel("Lista de Tarefas");
        txtMenuTarefa.setHorizontalAlignment(SwingConstants.CENTER);
        JButton addTarefa = new JButton("+");
        JButton infoTarefa = new JButton("i");
        
        infoTarefa.addActionListener(e -> {
            JPanel tarefasOverview = new JPanel();
            tarefasOverview.setLayout(new BoxLayout(tarefasOverview,BoxLayout.Y_AXIS));
            
            int totConcluido = 0;
            
            for (Tarefa totalTarefas : tarefas) {
                if (totalTarefas.isCompleta()) {
                    totConcluido++;
                }
            }
            JLabel tarefasConcluidas = new JLabel("Tarefas concluidas: " + Integer.toString(totConcluido) + "/" + Integer.toString(tarefas.size()));
            JLabel infoSalvamento = new JLabel("Todas as tarefas são salvas automaticamente ao fechar o programa!");
            
            tarefasOverview.add(tarefasConcluidas);
            tarefasOverview.add(infoSalvamento);
            
            JOptionPane.showMessageDialog(infoTarefa, tarefasOverview);            
        });
        
        panelMenuPrincipal.add(txtMenuTarefa, BorderLayout.CENTER);
        panelMenuPrincipal.add(addTarefa, BorderLayout.WEST);
        panelMenuPrincipal.add(infoTarefa, BorderLayout.EAST);
        
        tela.add(panelMenuPrincipal, BorderLayout.NORTH);
        
        // Painel da lista de Tarefas
        JPanel panelTarefas = new JPanel();
        panelTarefas.setLayout(new BoxLayout(panelTarefas, BoxLayout.X_AXIS));
        
        for (Tarefa tarefaAtual : tarefas) {
            JPanel cartaoPronto = criarCartaoTarefa(tarefaAtual,tarefas,panelTarefas);
            panelTarefas.add(cartaoPronto);
            panelTarefas.add(Box.createRigidArea(new Dimension(15, 0)));
        }
        
        JScrollPane scrollTarefas = new JScrollPane(panelTarefas); 
        tela.add(scrollTarefas, BorderLayout.CENTER);
        
        // Botões
        addTarefa.addActionListener(e -> {
            abrirDialogoNovaTarefa(tela,tarefas, panelTarefas);
        });
        
        tela.setVisible(true);
        });
    }
    
    public void abrirDialogoNovaTarefa(JFrame tela, ArrayList<Tarefa> tarefas, JPanel panelTarefas) {
        JDialog dialogo = new JDialog(tela, "Adicionando uma Tarefa", true);
        dialogo.setSize(500,400);
        dialogo.setLocationRelativeTo(tela);
        
        JPanel campos = new JPanel();
        
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new BoxLayout(painelFormulario,BoxLayout.Y_AXIS));
        
        JLabel txtTitulo = new JLabel("Titulo*: ");
        JTextField fieldTitulo = new JTextField(20);
        JLabel txtDescricao = new JLabel("Descrição*: ");
        JTextArea areaDescricao = new JTextArea(5,20);
        JLabel txtData = new JLabel("Data*: ");
        JTextField fieldData = new JTextField(10);
        
        JPanel linhaTitulo = new JPanel();
        linhaTitulo.add(txtTitulo);
        linhaTitulo.add(fieldTitulo);
        
        JPanel linhaDescricao = new JPanel();
        linhaDescricao.add(txtDescricao);
        linhaDescricao.add(areaDescricao);
        
        JPanel linhaData = new JPanel();
        linhaData.add(txtData);
        linhaData.add(fieldData);
        
        painelFormulario.add(linhaTitulo);
        painelFormulario.add(linhaDescricao);
        painelFormulario.add(linhaData);
        
        dialogo.add(painelFormulario);
        
        JPanel botoesFormulario = new JPanel();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");
        
        botoesFormulario.add(btnSalvar);
        botoesFormulario.add(btnCancelar);
        
        painelFormulario.add(botoesFormulario);
        
        btnSalvar.addActionListener(e -> {
            String tituloNovo = fieldTitulo.getText();
            String descricaoNova = areaDescricao.getText();
            String dataNova = fieldData.getText();
            Tarefa tarefa = new Tarefa(tituloNovo, descricaoNova, dataNova);
            tarefas.add(tarefa);
            JPanel cartaoPronto = criarCartaoTarefa(tarefa,tarefas,panelTarefas);
            panelTarefas.add(cartaoPronto);
            panelTarefas.add(Box.createRigidArea(new Dimension(0, 10)));
            panelTarefas.revalidate();
            panelTarefas.repaint();
            dialogo.dispose();
            reescreverArquivo(tarefas);
        });
        
        btnCancelar.addActionListener(e -> {
            dialogo.dispose();
        });
        
        dialogo.setVisible(true);
    }
    
    public void telaErro(Exception error) {
        JDialog erro = new JDialog();
        JPanel panelErro = new JPanel();
        JLabel txtErro = new JLabel("Erro ao salvar o arquivo" + error.getMessage());
        JButton closeBtn = new JButton("Fechar");
        
        closeBtn.addActionListener(e -> {
            erro.dispose();
        });
        
        panelErro.add(txtErro);
        panelErro.add(closeBtn);
        
        erro.add(panelErro);
        erro.setSize(250,150);
        erro.setVisible(true);
    }
    
    public void reescreverArquivo(ArrayList<Tarefa> tarefas) {
        try {
                FileWriter escritor = new FileWriter("tarefa.txt");
                for (Tarefa tarefaEscrita : tarefas) {
                escritor.write(tarefaEscrita.toString() + "\n");
                }
                escritor.close();
            } catch (Exception error) {
                telaErro(error);
        }
    }
    
}
