public class Arquivo implements Comparable<Arquivo> {

    private String info;

    public Arquivo(String info) {
        this.info = info;
    }

    private String getInfo() {
        return this.info;
    }

    private void setTInfo(String novaInfo) {
        this.info = novaInfo;
    }

    public void atualizaInfo(String info) {
        this.setTInfo(info);
    }

    public String toString() {
        return "Info: " + this.getInfo();
    }

    public int compareTo(Arquivo particao) {
        int result;
        result = this.info.compareTo(particao.info);
        return result;
    }
}