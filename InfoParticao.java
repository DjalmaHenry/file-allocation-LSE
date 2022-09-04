public class InfoParticao implements Comparable<InfoParticao> {

    private int inicio;
    private String nome;

    public InfoParticao(int inicio, String nome) {
        this.inicio = inicio;
        this.nome = nome;
    }

    public int getInicio() {
        return this.inicio;
    }

    public String getNome() {
        return this.nome;
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