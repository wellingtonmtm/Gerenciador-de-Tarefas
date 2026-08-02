package gerenciadordetarefas;

public class Tarefa {
    private String titulo;
    private String descricao;
    private String data;
    private boolean completa;
    
    public Tarefa(String tit, String descr, String data) {
        this.titulo = tit;
        this.descricao = descr;
        this.data = data;
        this.completa = false;
    }
    
    @Override
    public String toString() {
        return this.titulo + ";" + this.descricao + ";" + this.data + ";" + this.completa;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getTitulo() {
        return this.titulo;
    }
    
    public void setDescricao(String descri) {
        this.descricao = descri;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public String getData() {
        return this.data;
    }
    
    public void setCompleta(boolean compl) {
        this.completa = compl;
    }
    
    public boolean isCompleta() {
        return this.completa;
    }
}
