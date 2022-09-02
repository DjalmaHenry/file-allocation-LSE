public class Particao {

    private LSE<Arquivo> arquivos;

    public Particao() {
        arquivos = new LSE();
    }

    public void inserirValor(String info) {
        boolean result;
        Arquivo arqA = new Arquivo(info);
        result = arquivos.buscaNum(arqA);
        arquivos.inserirValorSemV(arqA);
    }

    public void exibirValor(String info) {
        Arquivo aux;
        Arquivo arq = new Arquivo(info);
        aux = arquivos.buscarObjeto(arq);
        if (aux == null) {
            System.err.println("Informação NÃO existe na partição!");
        } else {
            System.out.println("Achou na partição!");
            System.out.println(aux);
        }
    }

    public void removerValor(String info) {
        Arquivo arq = new Arquivo(info);
        arquivos.removeRepetido(arq);
    }

    public void exibirValores() {
        arquivos.exibirValores();
    }

}