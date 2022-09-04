import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    private static byte[] stringToByte(String info) throws IOException {
        Object obj = (Object) info;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private static String byteToString(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        String info = (String) object;
        return info;
    }

}