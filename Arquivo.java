public class Arquivo implements Comparable<Arquivo> {

    private int prox;
    private String nome;
    private byte[] info;

    public Arquivo(int prox, String nome, byte[] info) {
        this.prox = prox;
        this.nome = nome;
        this.info = info;
    }

    public int getProx() {
        return this.prox;
    }

    public String getNome() {
        return this.nome;
    }

    public byte[] getInfo() {
        return this.info;
    }

    public String toString() {
        return "Próximo: " + this.getProx() + "Nome: " + this.getNome() + "Info: " + this.getInfo();
    }

    public int compareTo(Arquivo arquivo) {
        int result;
        result = this.nome.compareTo(arquivo.nome);
        return result;
    }
}