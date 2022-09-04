public class InfoParticao implements Comparable<InfoParticao> {

    private int inicio;
    private String nome;
    private int tamanho;

    public InfoParticao(int inicio, String nome, int tamanho) {
        this.inicio = inicio;
        this.nome = nome;
        this.tamanho = tamanho;
    }

    public int getInicio() {
        return this.inicio;
    }

    public String getNome() {
        return this.nome;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public String toString() {
        return "Inicio: " + this.getInicio() + "Nome: " + this.getNome();
    }

    public int compareTo(InfoParticao infoParticao) {
        int result;
        result = this.nome.compareTo(infoParticao.nome);
        return result;
    }
}